package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
import values.IValue;

public class ASTPrint implements ASTNode {
    ASTNode arg;

    public ASTPrint(ASTNode arg) {
        this.arg = arg;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = arg.eval(e);
        v.show();
        return v;
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        //TODO
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        return null;
    }
}
