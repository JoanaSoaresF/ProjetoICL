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

import java.util.List;

public class ASTApply implements ASTNode {
    List<ASTNode> args;
    ASTNode function;

    public ASTApply(List<ASTNode> args, ASTNode function) {
        this.args = args;
        this.function = function;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue f = function.eval(e);
        if (f instanceof VClosure) {
            Environment<IValue> functionEnv = ((VClosure) f).getEnv();
            Environment<IValue> newEnv = functionEnv.beginScope();
            List<String> parameters = ((VClosure) f).getParams();
            for (int i = 0; i < args.size(); i++) {
                IValue argValue = args.get(i).eval(e);
                String argName = parameters.get(i);
                newEnv.assoc(argName, argValue);
            }
            IValue result = ((VClosure) f).getBody().eval(newEnv);
            newEnv.endScope();
            return result;
        }
        throw new InterpreterError("Method apply can only be applied to functions");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> envTypes) throws TypeErrorException {

        c.emit(";Apply compile");
        TypeClosure type = (TypeClosure) function.typecheck(envTypes);
        String interfaceName = type.getInterfaceName();
        String applySignature = type.getApplySignature();
        ;
        function.compile(c, e, envTypes);
        c.emit("checkcast " + interfaceName);
        for (ASTNode argument : args) {
            argument.compile(c, e, envTypes);
        }

        c.emit(String.format("invokeinterface %s/apply%s %d", interfaceName, applySignature,
                args.size() + 1));
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType t = function.typecheck(e);
        if (t instanceof TypeClosure) {
            for (int i = 0; i < args.size(); i++) {
                IType t2 = args.get(i).typecheck(e);
                IType argType = ((TypeClosure) t).getParametersTypes().get(i);
                if (!t2.getClass().equals(argType.getClass())) {
                    throw new TypeErrorException("Argument type is invalid");
                }
            }
            return ((TypeClosure) t).getReturnType();
        }
        throw new TypeErrorException("Apply method can only be applied to functions not " + t.showType());
    }
}
