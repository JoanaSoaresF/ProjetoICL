package types;

public class TypeRef implements IType {
    IType value;

    public TypeRef(IType value) {
        this.value = value;
    }

    @Override
    public String show() {
        return String.format("ref_%s", value.show());
    }

    @Override
    public String showType() {
        return String.format("ref_%s", value.showType());
    }

    @Override
    public String loadPrefix() {
        return "a";
    }

    public IType getType() {
        return value;
    }
}
