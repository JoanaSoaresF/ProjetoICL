package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import types.TypeRef;
import values.IValue;
import values.VMemoryCell;

public class ASTDeref implements ASTNode {
    ASTNode value;

    public ASTDeref(ASTNode value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = value.eval(e);
        if (v instanceof VMemoryCell) {

            return ((VMemoryCell) v).getValue();
        }
        throw new InterpreterError("Value given is not a reference");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        c.emit(";Deref compile");
        IType ref = value.typecheck(t);
        value.compile(c, e, t);
        c.emit(String.format("getfield %s/v %s", ref.show(), ((TypeRef) ref).getType().show()));
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType t1 = value.typecheck(e);
        if (t1 instanceof TypeRef) {
            IType refType = ((TypeRef) t1).getType();
            return refType;
        }
        throw new TypeErrorException("Illegal argument to ! operator");
    }
}
