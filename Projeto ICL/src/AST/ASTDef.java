package AST;


import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import types.TypeClosure;
import values.IValue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class ASTDef implements ASTNode {
    public static final String FRAME_NAME = "f%d";
    public static final String FIELD_NAME = "x%d";

    ASTNode body;
    Map<String, ASTNode> bindings; //Strings to expressions
    Map<String, IType> types;

    public ASTDef(ASTNode body, Map<String, ASTNode> init, Map<String, IType> types) {
        this.body = body;
        this.bindings = init;
        this.types = types;

    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        Environment<IValue> newEnv = e.beginScope();
        for (Map.Entry<String, ASTNode> entry : bindings.entrySet()) {
            String newID = entry.getKey();
            //To use the associations define previously in the same definition
            IValue value = entry.getValue().eval(newEnv);
            newEnv.assoc(newID, value);
        }
        IValue value = body.eval(newEnv);
        newEnv.endScope();
        return value;
    }


    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {

        Environment<Coordinates> newEnv = e.beginScope();
        Environment<IType> typesEnv = t.beginScope();
        int slot = 0;
        PrintStream currentFrame = createFrameFile(newEnv.depth());
        constructCodeBlock(c, newEnv.depth());
        System.out.println("Compile def " + bindings.size());
        for (Map.Entry<String, ASTNode> entry : bindings.entrySet()) {
            /**
             * aload 4
             * sipush 2
             * putfield frame_0/v0 I
             */
            String frame = String.format(FRAME_NAME, newEnv.depth());
            String field = String.format(FIELD_NAME, slot);
            String newID = entry.getKey();
            //To use the associations define previously in the same definition
            c.emit("aload 4");
            //add coordinates to env
            System.out.println("Novo id: " + newID);
            Coordinates coords = new Coordinates(newEnv.depth(), slot);
            //add types to env
            IType type = types.get(entry.getKey());
            if (type == null) {
                type = entry.getValue().typecheck(typesEnv);
            }
            System.out.println("Def type: " + type);


            if (type instanceof TypeClosure) {
                newEnv.assoc(newID, coords);
                typesEnv.assoc(newID, type);

                entry.getValue().compile(c, newEnv, typesEnv);
            } else {

                entry.getValue().compile(c, newEnv, typesEnv);
                newEnv.assoc(newID, coords);
                typesEnv.assoc(newID, type);

            }

            newSlot(slot, currentFrame, c, type.show());
            String typeL = type.show().equals("I") ? "I" : String.format("L%s;", type.show());
            c.emit(String.format("putfield %s/%s %s", frame, field, typeL));
            slot++;

        }
        body.compile(c, newEnv, typesEnv);
        newEnv.endScope();
        typesEnv.endScope();
        endFrame(currentFrame);
        /**
         * aload 4
         * getfield frame_1/sl Lframe_0;
         * astore 4
         */
        c.emit("aload 4");

        int depth = newEnv.depth();
        c.emit(getFieldParent(depth));
        c.emit("astore 4");


    }

    /**
     * .class f0
     * .super java/lang/Object
     * .field public sl Ljava/lang/Object;
     * .field public x0 I
     */
    private PrintStream createFrameFile(int frame) {
        String frameName = String.format(FRAME_NAME, frame);
        PrintStream currentFrame = null;
        try {
            FileOutputStream fout = new FileOutputStream(String.format("..\\files\\%s.j", frameName), false);
            currentFrame = new PrintStream(fout);
            currentFrame.println(".class " + frameName);
            currentFrame.println(".super java/lang/Object");
            if (frame == 0) {
                currentFrame.println(".field public sl Ljava/lang/Object;");
            } else {
                String parentName = String.format(FRAME_NAME, frame - 1);
                currentFrame.println(String.format(".field public sl L%s;", parentName));
            }

        } catch (FileNotFoundException t) {
            t.printStackTrace();
        }

        return currentFrame;

    }

    /**
     * new frame_0
     * dup
     * invokespecial frame_0/<init>()V
     * dup
     * aload 4
     * putfield frame_0/sl Ljava/lang/Object;
     * astore 4
     */
    private void constructCodeBlock(CodeBlock c, int frameDepth) {
        String frame = String.format(FRAME_NAME, frameDepth);
        String parent = String.format(FRAME_NAME, frameDepth - 1);
        c.emit("new " + frame);
        c.emit("dup");
        c.emit(String.format("invokespecial %s/<init>()V", frame));
        c.emit("dup");
        c.emit("aload 4");
        if (frameDepth == 0) {
            c.emit(String.format("putfield %s/sl L%s;", frame, "java/lang/Object"));
        } else {
            c.emit(String.format("putfield %s/sl L%s;", frame, parent));
        }
        c.emit("astore 4");

    }

    public void newSlot(int slot, PrintStream currentFrame, CodeBlock c, String type) {
        if (currentFrame != null) {
            String field = String.format(FIELD_NAME, slot);
            String t = type.equals("I") ? "I" : String.format("L%s;", type);
            currentFrame.println(String.format(".field public %s %s", field, t));
        }
    }

    public void endFrame(PrintStream currentFrame) {
        if (currentFrame != null) {
            currentFrame.println(".method public <init>()V\n" + "  aload_0\n" + "  invokenonvirtual java/lang/Object/<init>()V\n" + "  return\n" + ".end method\n");
            currentFrame.close();
        }
    }

    private String getFieldParent(int frameDepth) {
        String frame = String.format(FRAME_NAME, frameDepth);
        String other = (frameDepth != 0) ? String.format(FRAME_NAME, frameDepth - 1) : "java" + "/lang" +
                "/Object";
        return String.format("getfield %s/sl L%s;", frame, other);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {

        Environment<IType> envLocal = e.beginScope();
        for (Map.Entry<String, IType> entry : types.entrySet()) {
            IType t1 = entry.getValue();
            envLocal.assoc(entry.getKey(), t1);
        }


        for (Map.Entry<String, IType> entry : types.entrySet()) {
            String name = entry.getKey();
            IType declaredType = entry.getValue();
            IType type = bindings.get(name).typecheck(envLocal);
            if (!(type.getClass().equals(declaredType.getClass()))) {
                throw new TypeErrorException("Error type in def: declared type is different");
            }
        }

        if (types.size() <= 0) {
            for (Map.Entry<String, ASTNode> entry : bindings.entrySet()) {
                IType t1 = entry.getValue().typecheck(envLocal);
                envLocal.assoc(entry.getKey(), t1);
            }
        }


        IType t2 = body.typecheck(envLocal);
        e.endScope();
        return t2;
    }
}
