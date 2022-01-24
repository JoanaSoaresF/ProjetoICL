package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.TypeErrorException;
import types.IType;
import values.IValue;

import static AST.ASTDef.FIELD_NAME;
import static AST.ASTDef.FRAME_NAME;


public class ASTId implements ASTNode {
    String id;

    public ASTId(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        return e.find(id);
    }


    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) {
        c.emit(";-----Id compile-----");

        Coordinates coords = e.find(id);
        c.emit("aload 4 ");

        int depth = coords.getDepth();
        Environment<Coordinates> currentEnv = e;
        int currentDepth = currentEnv.depth();

        while (currentEnv.hasParent() && currentDepth > depth) {
            c.emit(getFieldParent(currentEnv));
            currentEnv = currentEnv.getParent();
            currentDepth = currentEnv.depth();
        }

        IType type = t.find(id);
        String field = getField(coords, type.show(), currentEnv);
        c.emit(field);
    }

    private String getFieldParent(Environment<Coordinates> e) {
        String frame = String.format(FRAME_NAME, e.getFrameId());
        String parent = String.format(FRAME_NAME, e.getParent().getFrameId());
        return String.format("getfield %s/sl L%s;", frame, parent);
    }

    private String getField(Coordinates coords, String type, Environment<Coordinates> e) {

        String frame = String.format(FRAME_NAME, e.getFrameId());
        String slot = String.format(FIELD_NAME, coords.getSlot());
        String t = type.equals("I") ? "I" : String.format("L%s;", type);
        return String.format("getfield %s/%s %s", frame, slot, t);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        return e.find(id);
    }
}
