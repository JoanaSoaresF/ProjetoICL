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
        String filename = "Main";
        CodeBlock code = new CodeBlock();
        Environment<Coordinates> env = new Environment<>();
        Environment<IType> envTypes = new Environment<>();

        try {
            ASTNode ast = parser.Start();
            ast.typecheck(envTypes);
            ast.compile(code, env, envTypes);
            code.dump(filename);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
