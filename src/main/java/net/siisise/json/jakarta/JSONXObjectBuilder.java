package net.siisise.json.jakarta;

import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import net.siisise.bind.Rebind;

/**
 * びるだー
 */
public class JSONXObjectBuilder implements JsonObjectBuilder {

    JSONXObject obj = new JSONXObject();

    JSONXObjectBuilder() {
        obj = new JSONXObject();
    }

    JSONXObjectBuilder(JsonObject src) {
        obj = new JSONXObject();
        src.forEach((k,v) -> {obj.put(k, (JsonValue)Rebind.valueOf(v, JsonValue.class));});
    }

    @Override
    public JsonObjectBuilder add(String name, JsonValue value) {
        obj.put(name, value);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, String value) {
        obj.put(name, new JSONXString(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, BigInteger value) {
        obj.put(name, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, BigDecimal value) {
        obj.put(name, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, int value) {
        obj.put(name, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, long value) {
        obj.put(name, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, double value) {
        obj.put(name, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, boolean value) {
        obj.put(name, value ? JsonValue.TRUE : JsonValue.FALSE);
        return this;
    }

    @Override
    public JsonObjectBuilder addNull(String name) {
        obj.put(name, JsonValue.NULL);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, JsonObjectBuilder builder) {
        obj.put(name, builder.build());
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, JsonArrayBuilder builder) {
        obj.put(name, builder.build());
        return this;
    }

    @Override
    public JsonObjectBuilder addAll(JsonObjectBuilder builder) {
        JsonObject src = builder.build();
        src.forEach((key,val) -> {
            obj.put(key, val);
        });
        return this;
    }

    @Override
    public JsonObjectBuilder remove(String name) {
        obj.remove(name);
        return this;
    }

    /**
     * クローン? クリア?
     * @return 
     */
    @Override
    public JsonObject build() {
        return obj;
    }

}
