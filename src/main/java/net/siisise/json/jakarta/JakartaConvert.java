package net.siisise.json.jakarta;

import java.util.Collection;
import java.util.Map;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import net.siisise.bind.format.TypeBind;

/**
 * Jakarta JSON-P 変換 (独自実装)
 */
public class JakartaConvert implements TypeBind<JsonValue> {
    
    @Override
    public Class<JsonValue> targetClass() {
        return JsonValue.class;
    }

    @Override
    public JsonValue nullFormat() {
        return JsonValue.NULL;
    }

    @Override
    public JsonValue booleanFormat(boolean bool) {
        return bool ? JsonValue.TRUE : JsonValue.FALSE;
    }

    @Override
    public JsonNumber numberFormat(Number number) {
        return new JSONXNumber(number);
    }

    @Override
    public JsonValue stringFormat(String str) {
        return new JSONXString(str);
    }

    @Override
    public JsonArray collectionFormat(Collection list) {
        if ( list.isEmpty() ) {
            return JsonValue.EMPTY_JSON_ARRAY;
        }
        return  (JsonArray) list.stream().collect(JSONXArray.collector());
    }

    @Override
    public JsonObject mapFormat(Map map) {
        if ( map.isEmpty() ) {
            return JsonValue.EMPTY_JSON_OBJECT;
        }
        return new JSONXObject(map);
    }
}
