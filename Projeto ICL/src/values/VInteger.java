package values;

public class VInteger implements IValue {
    int v;

    public VInteger(int v) {
        this.v = v;
    }

    @Override
    public void show() {

    }

    public int getValue() {
        return v;
    }
}
