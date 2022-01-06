package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
import values.IValue;
import values.VMemoryCell;

public class ASTDeref implements ASTNode {
    ASTNode value;

    public ASTDeref(ASTNode value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = value.eval(e);
        if (v instanceof VMemoryCell) {

            return ((VMemoryCell) v).getValue();
        }
        throw new InterpreterError("Value given is not a reference");
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
