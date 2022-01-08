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

    public IType getType() {
        return value;
    }
}
