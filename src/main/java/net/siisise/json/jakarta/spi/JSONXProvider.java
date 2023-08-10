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
package net.siisise.json.jakarta.spi;

import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonPointer;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import net.siisise.json.jakarta.JSONXBuilderFactory;
import net.siisise.json.jakarta.JSONXPointer;
import net.siisise.json.jakarta.JSONXReaderFactory;
import net.siisise.json.jakarta.JSONXWriterFactory;
import net.siisise.json.jakarta.stream.JSONXGeneratorFactory;
import net.siisise.json.jakarta.stream.JSONXParserFactory;

/**
 * JSON Provider 集めたもの?
 */
public class JSONXProvider extends JsonProvider {

    private JSONXParserFactory pf;
    private JSONXGeneratorFactory gf;
    private JSONXWriterFactory wf;
    private JSONXReaderFactory rf;
    private JSONXBuilderFactory bf;
    
    @Override
    public JsonParser createParser(Reader reader) {
        if ( pf == null ) { createParserFactory(null); }
        return pf.createParser(reader);
    }

    @Override
    public JsonParser createParser(InputStream in) {
        if ( pf == null ) { createParserFactory(null); }
        return pf.createParser(in);
    }

    @Override
    public JSONXParserFactory createParserFactory(Map<String, ?> map) {
        return pf = new JSONXParserFactory();
    }

    @Override
    public JsonGenerator createGenerator(Writer writer) {
        if ( gf == null ) createGeneratorFactory(null);
        return gf.createGenerator(writer);
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        if ( gf == null ) createGeneratorFactory(null);
        return gf.createGenerator(out);
    }

    @Override
    public JSONXGeneratorFactory createGeneratorFactory(Map<String, ?> map) {
        return gf = new JSONXGeneratorFactory(map);
    }

    @Override
    public JsonReader createReader(Reader reader) {
        if ( rf == null ) createReaderFactory(null);
        return rf.createReader(reader);
    }

    @Override
    public JsonReader createReader(InputStream in) {
        if ( rf == null ) createReaderFactory(null);
        return rf.createReader(in);
    }

    @Override
    public JsonWriter createWriter(Writer writer) {
        if ( wf == null ) createWriterFactory(null);
        return wf.createWriter(writer);
    }

    @Override
    public JsonWriter createWriter(OutputStream out) {
        if ( wf == null ) createWriterFactory(null);
        return wf.createWriter(out);
    }

    @Override
    public JSONXWriterFactory createWriterFactory(Map<String, ?> map) {
        return wf = new JSONXWriterFactory(map);
    }

    @Override
    public JSONXReaderFactory createReaderFactory(Map<String, ?> map) {
        return rf = new JSONXReaderFactory(map);
    }

    @Override
    public JsonObjectBuilder createObjectBuilder() {
        if ( bf == null ) createBuilderFactory(null);
        return bf.createObjectBuilder();
    }
    
    @Override
    public JsonObjectBuilder createObjectBuilder(JsonObject object) {
        if ( bf == null ) createBuilderFactory(null);
        return bf.createObjectBuilder(object);
    }
    
    @Override
    public JsonObjectBuilder createObjectBuilder(Map<String,?> map) {
        if ( bf == null ) createBuilderFactory(null);
        return bf.createObjectBuilder((Map<String,Object>)map);
    }

    @Override
    public JsonArrayBuilder createArrayBuilder() {
        if ( bf == null ) createBuilderFactory(null);
        return bf.createArrayBuilder();
    }

    @Override
    public JsonArrayBuilder createArrayBuilder(JsonArray array) {
        if ( bf == null ) createBuilderFactory(null);
        return bf.createArrayBuilder(array);
    }

    @Override
    public JsonPointer createPointer(String jsonPointer) {
        return new JSONXPointer(jsonPointer);
    }
    
    @Override
    public JSONXBuilderFactory createBuilderFactory(Map<String, ?> map) {
        return bf = new JSONXBuilderFactory(map);
    }
    
}
