package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
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
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        //TODO

    }
}
