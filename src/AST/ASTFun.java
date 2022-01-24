package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import types.TypeClosure;
import values.IValue;
import values.VClosure;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class ASTFun implements ASTNode {

    List<String> parametersNames;
    List<IType> parametersTypes;
    ASTNode body;
    TypeClosure functionType;

    public ASTFun(List<String> parameters, List<IType> types, ASTNode body) {
        this.parametersNames = parameters;
        this.parametersTypes = types;
        this.body = body;
        functionType = null;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new VClosure(parametersNames, body, e);
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> envTypes) throws TypeErrorException {

        c.emit(";-----Fun compile-----");
        int nClosure = c.newClosure();
        String functionName = "closure_" + nClosure;
        String currentEnvironment = "f" + e.getFrameId();
        Environment<Coordinates> bodyEnv = e.beginScope();
        bodyEnv.setFrameId();
        String functionFrame = "f" + (bodyEnv.getFrameId());
        Environment<IType> bodyTypesEnv = envTypes.beginScope();
        // add parameters
        for (int i = 0; i < parametersTypes.size(); i++) {
            bodyTypesEnv.assoc(parametersNames.get(i), parametersTypes.get(i));
            Coordinates coordinates = new Coordinates(bodyEnv.depth(), i);
            bodyEnv.assoc(parametersNames.get(i), coordinates);
        }
        createInterface();
        createFunctionFrame(functionFrame, currentEnvironment);
        createClosure(functionName, functionFrame, currentEnvironment, bodyEnv, bodyTypesEnv);

        //create function
        c.emit("new " + functionName);
        c.emit("dup");
        c.emit(String.format("invokespecial %s/<init>()V", functionName));
        c.emit("dup");
        c.emit("aload 4");
        c.emit(String.format("putfield %s/sl L%s;", functionName, currentEnvironment));

    }

    private void createInterface() {
        PrintStream interfaceFile = null;
        String interfaceName = functionType.getInterfaceName();
        try {
            FileOutputStream fout = new FileOutputStream(String.format("../generated_files/%s.j"
                    , interfaceName), false);
            interfaceFile = new PrintStream(fout);
            interfaceFile.println(".interface public " + interfaceName);
            interfaceFile.println(".super java/lang/Object");
            interfaceFile.printf(".method public abstract apply%s%n",
                    functionType.getApplySignature());
            interfaceFile.println(".end method");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createFunctionFrame(String frameName, String currentFrame) {
        PrintStream functionFrameFile = null;
        try {
            FileOutputStream fout = new FileOutputStream(String.format("../generated_files/%s.j",
                    frameName), false);
            functionFrameFile = new PrintStream(fout);
            functionFrameFile.println(".class " + frameName);
            functionFrameFile.println(".super java/lang/Object");
            functionFrameFile.printf(".field public sl L%s;%n", currentFrame);
            // add parameters
            for (int i = 0; i < parametersTypes.size(); i++) {
                IType argType = parametersTypes.get(i);
                String typeL = argType.show().equals("I") ? "I" : String.format("L" + "%s;", argType.show());
                functionFrameFile.printf(".field public x%d %s%n", i, typeL);
            }

            functionFrameFile.println(".method public <init>()V");
            functionFrameFile.println("aload_0");
            functionFrameFile.println("invokenonvirtual java/lang/Object/<init>()V");
            functionFrameFile.println("return");
            functionFrameFile.println(".end method");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void createClosure(String closureName, String functionFrame,
                               String currentEnvironmentName,
                               Environment<Coordinates> currentEnvironment,
                               Environment<IType> functionEnvTypes) throws TypeErrorException {
        PrintStream closureFile = null;
        int locals = Math.max(5, parametersNames.size() + 2);
        try {
            FileOutputStream fout = new FileOutputStream(String.format("../generated_files/%s.j",
                    closureName), false);
            closureFile = new PrintStream(fout);
            closureFile.println(".class " + closureName);
            closureFile.println(".super java/lang/Object");
            closureFile.printf(".implements %s%n", functionType.getInterfaceName());
            closureFile.printf(".field public sl L%s;%n", currentEnvironmentName);

            closureFile.println(".method public <init>()V");
            closureFile.println("\t aload_0");
            closureFile.println("\t invokenonvirtual java/lang/Object/<init>()V");
            closureFile.println("\t return");
            closureFile.println(".end method");

            closureFile.printf(".method public apply%s%n", functionType.getApplySignature());
            closureFile.println("\t.limit locals " + locals);
            closureFile.println("\t.limit stack 256");

            closureFile.println("\tnew " + functionFrame);
            closureFile.println("\tdup");
            closureFile.println("\tinvokespecial " + functionFrame + "/<init>()V");
            closureFile.println("\tdup");
            closureFile.println("\taload_0");
            closureFile.printf("\tgetfield %s/sl L%s;%n", closureName, currentEnvironmentName);
            //save current environment in the function environment
            closureFile.printf("\tputfield %s/sl L%s;%n", functionFrame, currentEnvironmentName);
            // save arguments in the function environment
            for (int i = 0; i < parametersNames.size(); i++) {
                closureFile.println("\tdup");
                IType paramType = parametersTypes.get(i);
                String typeL = paramType.show().equals("I") ? "I" : String.format("L%s;", paramType.show());
                closureFile.printf("\t%sload %d%n", paramType.loadPrefix(), i + 1);
                closureFile.printf("\tputfield %s/x%d %s%n", functionFrame, i, typeL);
            }
            //QUESTION
            closureFile.println("\tastore 4");

            //Generate code for body function
            CodeBlock functionCode = new CodeBlock();
            body.compile(functionCode, currentEnvironment, functionEnvTypes);
            for (String line : functionCode.getCode()) {
                closureFile.println("\t" + line);
            }

            closureFile.printf("\t%sreturn%n", functionType.getReturnType().loadPrefix());
            closureFile.println(".end method");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        Environment<IType> env = e.beginScope();
        for (int i = 0; i < parametersNames.size(); i++) {
            IType parameterType = parametersTypes.get(i);
            String parameterName = parametersNames.get(i);
            env.assoc(parameterName, parameterType);
        }
        IType type = body.typecheck(env);
        e.endScope();
        functionType = new TypeClosure(parametersTypes, type);
        return functionType;
    }
}
