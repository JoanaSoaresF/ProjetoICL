package dataStrucrures;

import java.util.HashMap;
import java.util.Map;

public class Environment<X> {

    private final Map<String, X> currentEnv;
    private final int depth;
    public Environment<X> parent;
    private int label;

    public Environment() {
        parent = null;
        currentEnv = new HashMap<>();
        depth = -1;
        label = 0;
    }

    private Environment(Environment<X> parent) {
        this.parent = parent;
        currentEnv = new HashMap<>();
        depth = parent.depth() + 1;
    }

    /**
     * @return the depth of the 'stack'
     */
    public int depth() {
        return depth;
    }

    /**
     * Push level
     *
     * @return the lever created
     */
    public Environment<X> beginScope() {
        return new Environment<X>(this);
    }

    /**
     * pop top level
     *
     * @return the level popped
     */
    public Environment<X> endScope() {
        return parent;
    }

    /**
     * Add association id-value to the top level
     *
     * @param id  - identifier
     * @param val value to associate to the identifier
     */
    public void assoc(String id, X val) {
        if (currentEnv.containsKey(id)) {
            System.out.println("IDDeclaredTwiceException " + id);
        }
        currentEnv.put(id, val);
    }

    public boolean hasParent() {
        return parent != null;
    }

    /**
     * Find the identifier id in returning the definition more in the top of the stack
     *
     * @param id id to find
     * @return the value associated with the id
     */
    public X find(String id) {
        X value = currentEnv.get(id);

        if (value == null && hasParent()) {
            value = parent.find(id);
        }


        if (value == null) {
            System.out.println("UndeclaredIdentifierException " + id);
        }
        return value;
    }

    public Environment<X> getParent() {
        return parent;
    }

    public int newLabel(){
        return label++;
    }
}
