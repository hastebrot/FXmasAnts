package de.jug_h.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Memory {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private final Map<String, Object> memory = new LinkedHashMap<>();

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public <T> void learn(String thing, T value) {
        memory.put(thing, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T recall(String thing) {
        return (T) memory.get(thing);
    }

    public void forget(String thing) {
        memory.remove(thing);
    }

    public void forgetAll() {
        memory.clear();
    }

}
