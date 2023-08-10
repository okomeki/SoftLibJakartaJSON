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
package net.siisise.json.jakarta.stream;

import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerator;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.siisise.json.JSON;
import net.siisise.json.JSONBoolean;
import net.siisise.json.JSONNumber;
import net.siisise.json.JSONString;
import net.siisise.json.JSONValue;
import net.siisise.json.base.JSONBaseNULL;
import net.siisise.json.bind.target.JSONFormat;
import net.siisise.json.jakarta.JSONXString;

/**
 * JSONFormat に標準機能をかぶせる形
 */
public class JSONXGenerator implements JsonGenerator {

    private final Writer out;
    private final JSONFormat format;
    
    boolean first = true;
    
    int tabsize = 0;
    
    List<String> closeCode = new ArrayList<>();
    
    JSONXGenerator(Writer writer, JSONFormat f) {
        out = writer;
        format = f;
    }
    
    @Override
    public JSONXGenerator writeStartObject() {
        try {
            writeSeparator();
            tabin("{","}");
            return this;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public JSONXGenerator writeStartObject(String name) {
        try {
            writeSeparator();
            writeKey(name);
            tabin("{", "}");
            return this;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    void writeSeparator() throws IOException {
        if (first) {
            first = false;
        } else {
            out.write(",");
            out.write(format.crlf);
        }
    }

    @Override
    public JSONXGenerator writeKey(String name) {
        try {
            JSONXString jname = new JSONXString(name);
            tab(jname.toJSON());
            out.write(": ");
            return this;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public JSONXGenerator writeStartArray() {
        try {
            writeSeparator();
            tabin("[","]");
            return this;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public JSONXGenerator writeStartArray(String name) {
        try {
            writeSeparator();
            writeKey(name);
            tabin("[","]");
            return this;
        } catch (IOException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    @Override
    public JSONXGenerator write(String name, JsonValue value) {
        write(name, JSON.valueOf(value));
        return this;
    }


    void write(String name, JSONValue value) {
        try {
            writeSeparator();
            writeKey(name);
            tab(value.rebind(format));
        } catch (IOException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    @Override
    public JSONXGenerator write(String name, String value) {
        write(name, (JSONValue) new JSONString(value));
        return this;
    }

    @Override
    public JSONXGenerator write(String name, BigInteger value) {
        write(name, (JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(String name, BigDecimal value) {
        write(name, (JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(String name, int value) {
        write(name, (JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(String name, long value) {
        write(name, (JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(String name, double value) {
        write(name, (JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(String name, boolean bin) {
        write(name, (JSONValue) JSONBoolean.valieOf(bin));
        return this;
    }

    @Override
    public JSONXGenerator writeNull(String name) {
        write(name, (JSONValue) JSONBaseNULL.NULL);
        return this;
    }
    
    private void tab(String txt) throws IOException {
        StringBuilder tabs = new StringBuilder(100);
        for ( int i = 0; i < tabsize; i++ ) {
            tabs.append(format.tab);
        }
        String src = tabs.toString() + txt.replace("\r\n", format.crlf + tabs.toString());
        out.write(src);
        
    }
    
    private void tabln(String txt) throws IOException {
        tab(txt);
        out.write(format.crlf);
    }
    
    private void tabin(String start, String end) throws IOException {
        tabln(start);
        tabsize++;
        closeCode.add(0,end);
        first = true;
    }

    private void tabout() throws IOException {
        String end = closeCode.get(0);
        closeCode.remove(0);
        tabsize--;
        tabln(end);
    }

    @Override
    public JSONXGenerator writeEnd() {
        try {
            tabout();
            return this;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public JSONXGenerator write(JsonValue value) {
        write(JSON.valueOf(value));
        return this;
    }

    void write(JSONValue value) {
        try {
            tab(value.rebind(format));
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public JSONXGenerator write(String value) {
        write((JSONValue) new JSONString(value));
        return this;
    }

    @Override
    public JSONXGenerator write(BigDecimal value) {
        write((JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(BigInteger value) {
        write((JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(int value) {
        write((JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(long value) {
        write((JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(double value) {
        write((JSONValue) new JSONNumber(value));
        return this;
    }

    @Override
    public JSONXGenerator write(boolean value) {
        write((JSONValue) JSONBoolean.valieOf(value));
        return this;
    }

    @Override
    public JSONXGenerator writeNull() {
        write((JSONValue) JSONBaseNULL.NULL);
        return this;
    }

    @Override
    public void close() {
        try {
            out.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void flush() {
        try {
            out.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
}
