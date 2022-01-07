package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import types.IType;
import values.IValue;
import values.VBoolean;
import values.VInteger;


public class ASTRelationOperands implements ASTNode {

    public enum Operation {
        EQUAL("==") {
            @Override
            public boolean apply(int a, int b) {
                return a == b;
            }
        },
        GREATER_EQUAL_THAN(">=") {
            @Override
            public boolean apply(int a, int b) {
                return a >= b;
            }
        },
        GREATER_THAN(">") {
            @Override
            public boolean apply(int a, int b) {
                return a > b;
            }
        },
        LESS_EQUAL_THAN("<=") {
            @Override
            public boolean apply(int a, int b) {
                return a <= b;
            }
        },
        LESS_THAN("<") {
            @Override
            public boolean apply(int a, int b) {
                return a < b;
            }
        },
        DIFFERENT_THAN("~=") {
            @Override
            public boolean apply(int a, int b) {
                return a != b;
            }
        };

        private final String op;

        Operation(String s) {
            op = s;
        }

        public abstract boolean apply(int a, int b);

        public String operation() {
            return op;
        }
    }

    private final ASTNode left, right;
    private final Operation operation;

    public ASTRelationOperands(ASTNode left, String operation, ASTNode right) {
        this.left = left;
        this.operation = findOperation(operation);
        this.right = right;
    }

    private Operation findOperation(String operation) {
        Operation op = null;
        for (Operation v : Operation.values()) {
            if (v.operation().equals(operation)) {
                op = v;
                break;
            }
        }
        return op;

    }

    @Override
    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue l = left.eval(e);
        if (l instanceof VInteger) {
            IValue r = right.eval(e);
            if (r instanceof VInteger) {
                int nl = ((VInteger) l).getValue();
                int nr = ((VInteger) r).getValue();
                boolean result = operation.apply(nl, nr);
                return new VBoolean(result);
            }
        }
        throw new InterpreterError("Illegal types to " + operation.operation() + " operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<Coordinates> e) {
        //TODO

    }

    @Override
    public IType typecheck(Environment<IType> e) {
        //TODO
        return null;
    }
}
