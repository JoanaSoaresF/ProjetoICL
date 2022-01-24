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
    boolean isPrintLn;

    public ASTPrint(ASTNode arg, boolean isPrintLn) {
        this.arg = arg;
        this.isPrintLn = isPrintLn;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = arg.eval(e);
        v.show();
        if (isPrintLn) {
            System.out.println();
        }
        return v;
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        arg.compile(c, e, t);
        c.emit("dup");
        c.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
        c.emit("swap");
        c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        if (isPrintLn) {
            c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        } else {
            c.emit("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
        }

    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        return arg.typecheck(e);
    }
}
