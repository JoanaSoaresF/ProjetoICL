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

public class ASTIf implements ASTNode {
    ASTNode condition, thenBody, elseBody;

    public ASTIf(ASTNode condition, ASTNode thenBody, ASTNode elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue c = condition.eval(e);
        if (c instanceof VBoolean) {
            if (((VBoolean) c).getValue()) {
                return thenBody.eval(e);
            } else if (elseBody != null) {
                return elseBody.eval(e);
            }
        }
        throw new InterpreterError("If statement malformed");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        String l1 = c.newLabel();
        String l2 = c.newLabel();

        condition.compile(c, e, t);
        c.emit("ifeq " + l1);
        thenBody.compile(c, e, t);
        c.emit("goto " + l2);
        c.emit(l1 + ":");
        elseBody.compile(c, e, t);
        c.emit(l2 + ":");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType t1 = condition.typecheck(e);
        if (t1 instanceof TypeBool) {
            IType t2 = thenBody.typecheck(e);
            IType t3 = elseBody.typecheck(e);
            if (t2.getClass().equals(t3.getClass())) {
                return t2;
            }
        }
        throw new TypeErrorException("Illegal arguments for if operand");
    }
}
