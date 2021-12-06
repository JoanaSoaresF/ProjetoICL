package values;

public class VBoolean implements IValue {
    boolean v;
    //TODO
    public VBoolean(boolean v) {
        this.v = v;
    }

    @Override
    public void show() {

    }

    public boolean getValue(){
        return v;
    }


}
