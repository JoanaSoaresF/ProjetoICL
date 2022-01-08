package AST;


import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import types.TypeInt;
import values.IValue;
import values.VInteger;

public class ASTUMinus implements ASTNode {

    ASTNode lhs;

    public ASTUMinus(ASTNode l) {
        lhs = l;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = lhs.eval(e);
        if (v instanceof VInteger) {
            return new VInteger(-((VInteger) v).getValue());
        }
        throw new InterpreterError("Illegal types to UMinus operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        lhs.compile(c, e, t);
        c.emit("ineg");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            return v1;
        }
        throw new TypeErrorException("Illegal arguments to UMinus operator");
    }


}
