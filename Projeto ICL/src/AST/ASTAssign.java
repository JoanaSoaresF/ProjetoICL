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
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        //TODO

    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = left.typecheck(e);
        if (v1 instanceof TypeRef) {
            return v1;
        }
        throw new TypeErrorException("Illegal arguments to := operator");
    }
}
