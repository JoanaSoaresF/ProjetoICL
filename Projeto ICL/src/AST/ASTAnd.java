package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
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
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        //TODO

    }

    @Override
    public IType typecheck(Environment<IType> e) {
        //TODO
        return null;
    }
}
