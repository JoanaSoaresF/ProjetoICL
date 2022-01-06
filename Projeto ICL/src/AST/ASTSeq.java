package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
import values.IValue;

public class ASTSeq implements ASTNode {

    ASTNode lhs, rhs;

    public ASTSeq(ASTNode t1, ASTNode t2) {
        lhs = t1;
        rhs = t2;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        lhs.eval(e);
        return rhs.eval(e);
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