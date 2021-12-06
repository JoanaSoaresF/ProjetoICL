package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import values.IValue;
import values.VInteger;

public class ASTDiv implements ASTNode {


    ASTNode lhs, rhs;

    public ASTDiv(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }


    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {

        IValue v1 = lhs.eval(e);
        if (v1 instanceof VInteger) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VInteger) {
                int result = ((VInteger) v1).getValue() / ((VInteger) v2).getValue();
                return new VInteger(result);
            }
        }
        throw new InterpreterError("Illegal types to / operator");

    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("idiv");
    }


}
