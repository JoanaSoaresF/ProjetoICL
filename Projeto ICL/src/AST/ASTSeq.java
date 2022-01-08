package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
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
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        lhs.compile(c, e, t);
        rhs.compile(c, e, t);
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType t1 = lhs.typecheck(e);
        IType t2 = rhs.typecheck(e);
        return t2;
    }

}