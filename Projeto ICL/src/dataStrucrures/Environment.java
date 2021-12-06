package dataStrucrures;

import java.util.HashMap;
import java.util.Map;

public class Environment<IValue> {

    public Environment<IValue> parent;
    private final int depth;

    private final Map<String, IValue> currentEnv;

    public Environment() {
        parent = null;
        currentEnv = new HashMap<>();
        depth = -1;
    }

    private Environment(Environment<IValue> parent) {
        this.parent = parent;
        currentEnv = new HashMap<>();
        depth = parent.depth() + 1;
    }

    /**
     * Push level
     *
     * @return the lever created
     */
    public Environment<IValue> beginScope() {
        return new Environment<IValue>(this);
    }

    /**
     * pop top level
     *
     * @return the level popped
     */
    public Environment<IValue> endScope() {
        return parent;
    }

    /**
     * Add association id-value to the top level
     *
     * @param id  - identifier
     * @param val value to associate to the identifier
     */
    public void assoc(String id, IValue val) {
        if (currentEnv.containsKey(id)) {
            System.out.println("IDDeclaredTwiceException "+id);
        }
        currentEnv.put(id, val);
    }

    /**
     * Find the identifier id in returning the definition more in the top of the stack
     *
     * @param id id to find
     * @return the value associated with the id
     */
    public IValue find(String id) {
        IValue value = currentEnv.get(id);

        if (value == null && hasParent()) {
            value = parent.find(id);
        }


        if (value == null) {
            System.out.println("UndeclaredIdentifierException " + id);
        }
        return value;
    }

    public boolean hasParent() {
        return parent != null;
    }

    /**
     *
     * @return the depth of the 'stack'
     */
    public int depth(){
        return depth;
    }


    public Environment<IValue> getParent() {
        return parent;
    }
}
