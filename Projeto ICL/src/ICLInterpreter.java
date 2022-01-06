import dataStrucrures.Environment;
import values.IValue;

public class ICLInterpreter {


    public static void main(String args[]) {

        Parser0 parser = new Parser0(System.in);

        while (true) {
            try {
                //TODO??
                Environment env = new Environment();
                System.out.print("> ");
                AST.ASTNode ast = parser.Start();
                IValue v = ast.eval(env);
                v.show();
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }

}
