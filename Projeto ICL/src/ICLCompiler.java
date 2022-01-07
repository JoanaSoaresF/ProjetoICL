import AST.ASTNode;
import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import types.IType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class ICLCompiler {

    /**
     * Main entry point. Compiler aula 2
     */
    public static void main(String args[]) throws FileNotFoundException {
        FileInputStream input = new FileInputStream(args[0]);
        Parser0 parser = new Parser0(input);
        String[] file = args[0].split("\\.");
        System.out.println(Arrays.toString(file));
        String filename = file[file.length-2].replace("\\","");
        System.out.println(filename);
        CodeBlock code = new CodeBlock();
        Environment<Coordinates>  env = new Environment<>();
        Environment<IType> envTypes = new Environment<>();

        try {
            System.out.printf("File %s generated\n", filename+".j");
            ASTNode ast = parser.Start();
            ast.typecheck(envTypes);
            ast.compile(code, env);
            code.dump(filename);

//            String cmd = "java -jar \"..\\..\\jasmin.jar\" "+filename+".j";
//            Runtime run = Runtime. getRuntime();
//            Process pr = run. exec(cmd);
//            pr. waitFor();

        } catch (Exception e) {
            System.out.println("Syntax Error!");
            e.printStackTrace();
            parser.ReInit(input);
        }


    }
}
