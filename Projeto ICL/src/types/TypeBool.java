package types;

public class TypeBool implements IType {
    @Override
    public String show() {
        return "I";

    }

    @Override
    public String showType() {
        return "bool";
    }

    @Override
    public String loadPrefix() {
        return "i";
    }
}
