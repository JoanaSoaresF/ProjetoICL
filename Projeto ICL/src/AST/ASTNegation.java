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

public class ASTNegation implements ASTNode {

    ASTNode expression;

    public ASTNegation(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue exp = expression.eval(e);
        if (exp instanceof VBoolean) {
            boolean r = !((VBoolean) exp).getValue();
            return new VBoolean(r);
        }
        throw new InterpreterError("Illegal types to ~ operator");

    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        c.emit("sipush 1");
        expression.compile(c, e, t);
        c.emit("isub");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = expression.typecheck(e);
        if (v1 instanceof TypeBool) {
            return v1;
        }
        throw new TypeErrorException("Illegal arguments to ~ operator");
    }
}
