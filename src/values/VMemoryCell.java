package values;

public class VMemoryCell implements IValue {

    IValue v;

    public VMemoryCell(IValue v) {
        this.v = v;
    }

    @Override
    public void show() {
        System.out.print(v);
    }

    public IValue getValue() {
        return v;
    }

    public void set(IValue v) {
        this.v = v;
    }
}
