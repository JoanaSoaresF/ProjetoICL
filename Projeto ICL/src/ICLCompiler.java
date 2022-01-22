import AST.ASTNode;
import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import types.IType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ICLCompiler {

    /**
     * Main entry point. Compiler aula 2
     */
    public static void main(String args[]) throws FileNotFoundException {
        FileInputStream input = new FileInputStream(args[0]);
        Parser0 parser = new Parser0(input);
        String[] file = args[0].split("\\.");
        String filename = file[file.length - 2].replace("\\", "");
        CodeBlock code = new CodeBlock();
        Environment<Coordinates> env = new Environment<>();
        Environment<IType> envTypes = new Environment<>();

        try {
            ASTNode ast = parser.Start();
            ast.typecheck(envTypes);
            System.out.println("compile:");
            ast.compile(code, env, envTypes);
            code.dump(filename);

//            String cmd = "java -jar \"..\\..\\jasmin.jar\" "+filename+".j";
//            Runtime run = Runtime. getRuntime();
//            Process pr = run. exec(cmd);
//            pr. waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
