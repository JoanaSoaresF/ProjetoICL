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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ASTNew implements ASTNode {

    ASTNode arg;

    public ASTNew(ASTNode arg) {
        this.arg = arg;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = arg.eval(e);
        return new VMemoryCell(v);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        c.emit(";New compile");
        IType v = arg.typecheck(t);
        createRefClassFile(v);
        c.emit(String.format("new ref_%s", v.showType()));
        c.emit("dup");
        c.emit(String.format("invokespecial ref_%s/<init>()V", v.showType()));
        c.emit("dup");
        arg.compile(c, e, t);
        String type = v.show().equals("I") ? "I" : String.format("L%s;", v.showType());
        c.emit(String.format("putfield ref_%s/v %s", v.showType(), type));
    }

    private void createRefClassFile(IType t) {
        String classType = String.format("ref_%s", t.showType());
        PrintStream classFile;
        try {
            FileOutputStream fout = new FileOutputStream(String.format("../files/%s.j",
                    classType), false);
            String type = t.show().equals("I") ? t.show() : String.format("L%s;", t);
            classFile = new PrintStream(fout);
            classFile.printf(".class public ref_%s%n", t.showType());
            classFile.println(".super java/lang/Object");
            classFile.printf(".field public v %s%n", type);
            classFile.println(".method public <init>()V");
            classFile.println("aload_0");
            classFile.println("invokenonvirtual java/lang/Object/<init>()V");
            classFile.println("return");
            classFile.println(".end method");
            classFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType t = arg.typecheck(e);
        return new TypeRef(t);
    }
}
