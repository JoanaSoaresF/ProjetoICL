package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
import values.IValue;
import values.VMemoryCell;

public class ASTNew implements ASTNode {

    ASTNode arg;

    public ASTNew(ASTNode arg) {
        this.arg = arg;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = arg.eval(e);
        return new VMemoryCell(v);
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
