package types;

public class TypeInt implements IType {
    @Override
    public String show() {
        return "I";
    }

    @Override
    public String showType() {
        return "int";
    }

    @Override
    public String loadPrefix() {
        return "i";
    }
}
