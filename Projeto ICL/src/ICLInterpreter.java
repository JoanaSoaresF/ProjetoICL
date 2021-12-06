import dataStrucrures.Environment;

public class ICLInterpreter {


    public static void main(String args[]) {
        Parser0 parser = new Parser0(System.in);

        while (true) {
            try {
                //TODO??
                Environment env = new Environment();
                System.out.print("> ");
                AST.ASTNode ast = parser.Start();
                System.out.println(ast.eval(env));
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }
    }

}
