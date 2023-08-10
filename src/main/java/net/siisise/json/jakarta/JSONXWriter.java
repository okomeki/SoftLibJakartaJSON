/*
 * Copyright 2023 okome.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.siisise.json.jakarta;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import net.siisise.json.JSON;

/**
 *
 */
public class JSONXWriter implements JsonWriter {

    private final Writer writer;
    
    public JSONXWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeArray(JsonArray array) {
        write(array);
    }

    @Override
    public void writeObject(JsonObject object) {
        write(object);
    }

    @Override
    public void write(JsonStructure value) {
        write((JsonValue)value);
    }

    @Override
    public void write(JsonValue value) {
        try {
            writer.write(JSON.valueOf(value).toJSON());
            writer.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException ex) {
//            throw new IllegalStateException(ex);
        }
    }
    
}
