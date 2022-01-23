package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import values.IValue;

public class ASTPrint implements ASTNode {
    ASTNode arg;

    public ASTPrint(ASTNode arg) {
        this.arg = arg;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = arg.eval(e);
        v.show();
        return v;
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        arg.compile(c, e, t);
        c.emit("dup");
        c.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
        c.emit("swap");
        c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        arg.typecheck(e);

        return null;
    }
}
