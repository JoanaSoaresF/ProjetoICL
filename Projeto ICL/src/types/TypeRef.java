package types;

public class TypeRef implements IType{
    IType value;

    public TypeRef(IType value) {
        this.value = value;
    }

    @Override
    public void show() {

    }

    public IType getType(){
        return value;
    }
}
