package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
import values.IValue;
import values.VBoolean;

public class ASTIf implements ASTNode{
    ASTNode condition, thenBody, elseBody;

    public ASTIf(ASTNode condition, ASTNode thenBody, ASTNode elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue c = condition.eval(e);
        if(c instanceof VBoolean){
            if(((VBoolean) c).getValue()){
                return thenBody.eval(e);
            }else{
                return elseBody.eval(e);
            }
        }
        throw new InterpreterError("If statement malformed");
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
