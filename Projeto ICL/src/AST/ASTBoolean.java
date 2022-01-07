package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
import types.TypeBool;
import values.IValue;
import values.VBoolean;

public class ASTBoolean implements ASTNode {

    boolean value;

    public ASTBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VBoolean(value);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        //TODO

    }

    @Override
    public IType typecheck(Environment<IType> e) {
        return new TypeBool();
    }
}
