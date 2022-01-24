package values;

public class VBoolean implements IValue {
    boolean v;

    public VBoolean(boolean v) {
        this.v = v;
    }

    @Override
    public void show() {
        System.out.print(v);
    }

    public boolean getValue() {
        return v;
    }


}
