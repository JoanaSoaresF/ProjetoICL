package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import types.TypeRef;
import values.IValue;
import values.VMemoryCell;

public class ASTAssign implements ASTNode {

    ASTNode left, right;

    public ASTAssign(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        //TODO - memória?
        IValue l = left.eval(e);
        if (l instanceof VMemoryCell) {
            IValue r = right.eval(e);
            ((VMemoryCell) l).set(r);
            return r;

        }
        throw new InterpreterError("Illegal types to := operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> envTypes) throws TypeErrorException {
        IType type = left.typecheck(envTypes);
        left.compile(c, e, envTypes);
        right.compile(c, e, envTypes);
        String typeInRef = ((TypeRef) type).getType().show();
        String t = typeInRef.equals("I") ? "I" : String.format("L%s;", typeInRef);
        c.emit(String.format("putfield %s/v %s", type.show(), t));

    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = left.typecheck(e);
        IType v2 = right.typecheck(e);
        if (v1 instanceof TypeRef) {
            if (((TypeRef) v1).getType().getClass().equals(v2.getClass())) {
                return v2;
            }
        }
        throw new TypeErrorException("Illegal arguments to := operator");
    }
}
