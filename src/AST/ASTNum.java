package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import types.IType;
import types.TypeInt;
import values.IValue;
import values.VInteger;


public class ASTNum implements ASTNode {

    int val;

    public ASTNum(int n) {
        val = n;
    }

    @Override
    public IValue eval(Environment<IValue> e) {

        return new VInteger(val);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) {

        c.emit("sipush " + val);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        return new TypeInt();
    }


}

