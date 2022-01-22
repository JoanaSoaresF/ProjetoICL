package AST;

import dataStrucrures.CodeBlock;
import dataStrucrures.Coordinates;
import dataStrucrures.Environment;
import exceptions.InterpreterError;
import exceptions.TypeErrorException;
import types.IType;
import types.TypeBool;
import types.TypeInt;
import values.IValue;
import values.VBoolean;
import values.VInteger;


public class ASTRelationOperands implements ASTNode {

    private final ASTNode left, right;
    private final Operation operation;

    public enum Operation {
        EQUAL("==") {
            @Override
            public boolean apply(int a, int b) {
                return a == b;
            }

            @Override
            public String compile() {
                return "ifeq";
            }
        },
        GREATER_EQUAL_THAN(">=") {
            @Override
            public boolean apply(int a, int b) {
                return a >= b;
            }

            @Override
            public String compile() {
                return "ifge";
            }
        },
        GREATER_THAN(">") {
            @Override
            public boolean apply(int a, int b) {
                return a > b;
            }

            @Override
            public String compile() {
                return "ifgt";
            }
        },
        LESS_EQUAL_THAN("<=") {
            @Override
            public boolean apply(int a, int b) {
                return a <= b;
            }

            @Override
            public String compile() {
                return "ifle";
            }
        },
        LESS_THAN("<") {
            @Override
            public boolean apply(int a, int b) {
                return a < b;
            }

            @Override
            public String compile() {
                return "iflt";
            }
        },
        DIFFERENT_THAN("~=") {
            @Override
            public boolean apply(int a, int b) {
                return a != b;
            }

            @Override
            public String compile() {
                return "ifne";
            }
        };

        private final String op;

        Operation(String s) {
            op = s;
        }

        public abstract boolean apply(int a, int b);

        public abstract String compile();

        public String operation() {
            return op;
        }
    }

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
    public void compile(CodeBlock c, Environment<Coordinates> e, Environment<IType> t) throws TypeErrorException {
        String l1 = c.newLabel();
        String l2 = c.newLabel();
        left.compile(c, e, t);
        right.compile(c, e, t);
        c.emit("isub");
        c.emit(String.format("%s %s", operation.compile(), l1));
        c.emit("sipush 0");
        c.emit(String.format("goto %s", l2));
        c.emit(String.format("%s:\n sipush 1", l1));
        c.emit(String.format("%s:", l2));
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeErrorException {
        IType v1 = left.typecheck(e);
        if (v1 instanceof TypeInt) {
            IType v2 = right.typecheck(e);
            if (v2 instanceof TypeInt) {
                return new TypeBool();
            }
        }
        throw new TypeErrorException("Illegal arguments to relational operator " + v1.show());
    }
}
