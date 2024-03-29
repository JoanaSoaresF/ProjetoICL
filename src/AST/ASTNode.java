package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import values.IValue;

public interface ASTNode {

    IValue eval(Environment<IValue> e) throws InterpreterError;

    void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> envTypes) throws TypeErrorException;

    IType typecheck(Environment<IType> e) throws TypeErrorException;


}

