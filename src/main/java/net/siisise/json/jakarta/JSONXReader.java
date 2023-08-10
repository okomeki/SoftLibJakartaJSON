package net.siisise.json.jakarta;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import net.siisise.bind.Rebind;
import net.siisise.io.FrontPacket;
import net.siisise.json.JSON;

/**
 * リーダー
 */
public class JSONXReader implements JsonReader {

    private final FrontPacket fp;

    JSONXReader(FrontPacket front) {
        fp = front;
    }

    @Override
    public JsonStructure read() {
        return (JsonStructure) Rebind.valueOf(JSON.parse(fp), JsonStructure.class);
    }

    @Override
    public JsonValue readValue() {
        return Rebind.valueOf(JSON.parse(fp), JsonValue.class);
    }

    @Override
    public JsonObject readObject() {
        return (JsonObject) Rebind.valueOf(JSON.parse(fp), JsonObject.class);
    }

    @Override
    public JsonArray readArray() {
        return (JsonArray) Rebind.valueOf(JSON.parse(fp), JsonArray.class);
    }

    @Override
    public void close() {
        try {
            fp.getInputStream().close();
        } catch (IOException ex) {
            Logger.getLogger(JSONXReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
