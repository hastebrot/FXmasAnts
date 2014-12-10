package de.jug_h.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Memory {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private final Map<String, Object> memory = new LinkedHashMap<>();

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public void put(String thing,
                    Object value) {
        memory.put(thing, value);
    }

    public void putDouble(String thing,
                          Function<Double, Double> callback) {
        memory.put(thing, callback.apply(getDouble(thing)));
    }

    public Object get(String thing) {
        return memory.getOrDefault(thing, null);
    }

    public double getDouble(String thing) {
        return (double) memory.getOrDefault(thing, 0.0);
    }

    public boolean getBoolean(String thing) {
        return (boolean) memory.getOrDefault(thing, false);
    }

    public boolean has(String thing) {
        return memory.containsKey(thing);
    }

    public void remove(String thing) {
        memory.remove(thing);
    }

    public void clear() {
        memory.clear();
    }

}
