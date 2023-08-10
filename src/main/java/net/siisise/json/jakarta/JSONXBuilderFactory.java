package net.siisise.json.jakarta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import jakarta.json.JsonArray;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import net.siisise.bind.Rebind;

/**
 *
 */
public class JSONXBuilderFactory implements JsonBuilderFactory {

    public JSONXBuilderFactory(Map<String, ?> map) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JSONXObjectBuilder createObjectBuilder() {
        return new JSONXObjectBuilder();
    }

    @Override
    public JSONXObjectBuilder createObjectBuilder(JsonObject object) {
        JSONXObjectBuilder ob = new JSONXObjectBuilder(object);
        return ob;
    }

    @Override
    public JSONXObjectBuilder createObjectBuilder(Map<String, Object> object) {
        JSONXObjectBuilder ob = createObjectBuilder();
        object.forEach((k,v) -> {ob.add(k, (JsonValue)Rebind.valueOf(v, JsonValue.class));});
        return ob;
    }

    @Override
    public JSONXArrayBuilder createArrayBuilder() {
        return new JSONXArrayBuilder();
    }

    @Override
    public JSONXArrayBuilder createArrayBuilder(JsonArray array) {
        JSONXArrayBuilder ab = createArrayBuilder();
        array.forEach(v -> ab.add((JsonValue)Rebind.valueOf(v, JsonValue.class)));
        return ab;
    }

    @Override
    public JSONXArrayBuilder createArrayBuilder(Collection<?> collection) {
        JSONXArrayBuilder ab = createArrayBuilder();
        collection.forEach(v -> ab.add((JsonValue)Rebind.valueOf(v, JsonValue.class)));
        return ab;
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return new HashMap<>();
    }

}
