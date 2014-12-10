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
        put(thing, callback.apply(getDouble(thing)));
    }

    public Object get(String thing) {
        return memory.get(thing);
    }

    public double getDouble(String thing) {
        Object value = get(thing);
        return value == null ? 0.0 : (double) value;
    }

    public boolean getBoolean(String thing) {
        Object value = get(thing);
        return value == null ? false : (boolean) value;
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
