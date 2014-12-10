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

    public void learn(String thing, Object value) {
        memory.put(thing, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T recall(String thing) {
        return (T) memory.get(thing);
    }

    @SuppressWarnings("unchecked")
    public <T> T recall(String thing,
                        T defaultValue) {
        if (canRecall(thing) && defaultValue != null) {
            Object value = memory.get(thing);
            return (T) defaultValue.getClass().cast(value);
        }
        return defaultValue;
    }

    public boolean canRecall(String thing) {
        return memory.containsKey(thing);
    }

    public void forget(String thing) {
        memory.remove(thing);
    }

    public void forgetAll() {
        memory.clear();
    }

}
