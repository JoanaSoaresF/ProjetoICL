package values;

public class VInteger implements IValue {
    int v;

    public VInteger(int v) {
        this.v = v;
    }

    @Override
    public void show() {
        System.out.print(v);
    }

    public int getValue() {
        return v;
    }
}
