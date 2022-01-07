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

public class ASTWhile implements ASTNode {

    ASTNode condition, body;

    public ASTWhile(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue c = condition.eval(e);
        if (c instanceof VBoolean) {
            while (((VBoolean) c).getValue()) {
                body.eval(e);
                c = condition.eval(e);
            }
            return new VBoolean(false);
        }
        throw new InterpreterError("While condition malformed");

    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        //TODO
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType t1 = condition.typecheck(e);
        if (t1 instanceof TypeBool) {
            IType t2 = body.typecheck(e);
            return new TypeBool();
        }
        throw new TypeErrorException("While operand with wrog arguments types");
    }
}
