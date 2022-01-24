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

public class ASTAdd implements ASTNode {

    ASTNode lhs, rhs;

    public ASTAdd(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v1 = lhs.eval(e);
        if (v1 instanceof VInteger) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VInteger) {
                int result = ((VInteger) v1).getValue() + ((VInteger) v2).getValue();
                return new VInteger(result);
            }
        }
        throw new InterpreterError("Illegal types to + operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> envTypes) throws TypeErrorException {
        lhs.compile(c, e, envTypes);
        rhs.compile(c, e, envTypes);
        c.emit("iadd");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeInt) {
                return v1;
            }
        }
        throw new TypeErrorException("Illegal arguments to + operator");
    }
}

