package values;

import AST.ASTNode;
import dataStrucrures.Environment;

import java.util.List;

public class VClosure implements IValue {

    private List<String> params;
    private ASTNode body;
    private Environment<IValue> env;

    public VClosure(List<String> params, ASTNode body, Environment<IValue> env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    @Override
    public void show() {
        System.out.print("function");
    }


    public List<String> getParams() {
        return params;
    }

    public ASTNode getBody() {
        return body;
    }

    public Environment<IValue> getEnv() {
        return env;
    }
}
