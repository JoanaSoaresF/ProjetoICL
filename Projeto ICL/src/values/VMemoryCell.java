package values;

public class VMemoryCell implements IValue {

    IValue v;

    public VMemoryCell(IValue v) {
        this.v = v;
    }

    //TODO
    @Override
    public void show() {

    }

    public IValue getValue(){
        return v;
    }

    public void set(IValue v){
        this.v = v;
    }
}
