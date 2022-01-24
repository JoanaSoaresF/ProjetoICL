package dataStrucrures;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;


public class CodeBlock {


    private LinkedList<String> code;
    private int label;
    private int closure;


    public CodeBlock() {

        code = new LinkedList<>();
        label = 0;
        closure = 0;
    }

    public void emit(String opcode) {
        code.add(opcode);
    }

    public void dump(String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(String.format("..\\generated_files/%s.j",
                    filename), false);
            PrintStream f = new PrintStream(fout);
            start(f);
            for (String instruction : code) {
                f.println("\t" + instruction);
            }
            end(f);
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void start(PrintStream f) {
        f.println(".class public Main");
        f.println(".super java/lang/Object");
        f.println(".method public <init>()V");
        f.println("\taload_0");
        f.println("\tinvokenonvirtual java/lang/Object/<init>()V");
        f.println("\treturn");
        f.println(".end method");
        f.println(".method public static main([Ljava/lang/String;)V");
        f.println("\t;set limits used by this method");
        f.println("\t.limit locals 10");
        f.println("\t.limit stack 256");
        f.println("\taconst_null");
        f.println("\tastore 4");
        f.println("\t;START");
    }

    private void end(PrintStream f) {
        f.println("\t; END");
        f.println("\treturn");
        f.println(".end method");
    }

    public String newLabel() {
        String l = "L" + label;
        label++;
        return l;
    }

    public int newClosure() {
        return closure++;
    }

    public LinkedList<String> getCode() {
        return code;
    }

}
