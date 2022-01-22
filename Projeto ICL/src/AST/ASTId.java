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
        /**
         * aload 4
         * getfield frame_1/sl Lframe_0;
         * getfield frame_0/v0 I
         */
        Coordinates coords = e.find(id);
        c.emit("aload 4 ");
        int depth = coords.getDepth();
        for (int i = e.depth() - 1; i >= depth; i--) {
            c.emit(getFieldParent(i));
        }
//        if(depth<e.depth())
//            c.emit(getFieldParent(depth));
        IType type = t.find(id);
        String field = getField(coords, type.show());
        c.emit(field);
    }

    private String getFieldParent(int depth) {
        String frame = String.format(FRAME_NAME, depth + 1);
        String parent = String.format(FRAME_NAME, depth);
        return String.format("getfield %s/sl L%s;", frame, parent);
    }

    private String getField(Coordinates coords, String type) {

        String frame = String.format(FRAME_NAME, coords.getDepth());
        String slot = String.format(FIELD_NAME, coords.getSlot());
        String t = type.equals("I") ? "I" : String.format("L%s;", type);
        return String.format("getfield %s/%s %s", frame, slot, t);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        return e.find(id);
    }
}
