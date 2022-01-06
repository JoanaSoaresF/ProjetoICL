package values;

public class VBoolean implements IValue {
    boolean v;
    //TODO
    public VBoolean(boolean v) {
        this.v = v;
    }

    @Override
    public void show() {
        System.out.println(v);
    }

    public boolean getValue(){
        return v;
    }


}
