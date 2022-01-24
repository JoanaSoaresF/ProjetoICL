package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import types.TypeBool;
import values.IValue;
import values.VBoolean;

public class ASTAnd implements ASTNode {
    private final ASTNode left, right;

    public ASTAnd(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {

        IValue l = left.eval(e);
        if (l instanceof VBoolean) {
            IValue r = right.eval(e);
            if (r instanceof VBoolean) {
                boolean result = ((VBoolean) l).getValue() && ((VBoolean) r).getValue();
                return new VBoolean(result);
            }
        }
        throw new InterpreterError("Illegal types to && operator");

    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> envTypes) throws TypeErrorException {
        left.compile(c, e, envTypes);
        right.compile(c, e, envTypes);
        c.emit("iand");

    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = left.typecheck(e);
        if (v1 instanceof TypeBool) {
            IType v2 = right.typecheck(e);
            if (v2 instanceof TypeBool) {
                return v1;
            }
        }
        throw new TypeErrorException("Illegal arguments to && operator");
    }
}
