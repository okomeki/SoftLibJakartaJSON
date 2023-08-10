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
package net.siisise.json.jakarta.bind;

import jakarta.json.JsonValue;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import jakarta.json.spi.JsonProvider;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import net.siisise.bind.Rebind;
import net.siisise.json.jakarta.JakartaConvert;

/**
 * Provider使用版.
 */
public class JSONProviderB extends JSONB {
    
    private final JsonProvider provider;
    
    public JSONProviderB(JsonProvider pro, JsonbConfig conf) {
        super(conf);
        provider = pro;
    }
    
    @Override
    public <T> T fromJson(String string, Type type) throws JsonbException {
        return fromJson(new StringReader(string), type);
    }

    @Override
    public <T> T fromJson(Reader reader, Type type) throws JsonbException {
        return Rebind.valueOf(provider.createReader(reader).readValue(), type);
    }

    @Override
    public <T> T fromJson(InputStream in, Type type) throws JsonbException {
        return Rebind.valueOf(provider.createReader(in).readValue(), type);
    }

    /**
     * 
     * @param o ソース
     * @return JSON 出力
     * @throws JsonbException 
     */
    @Override
    public String toJson(Object o) throws JsonbException {
        StringWriter sw = new StringWriter();
        toJson(o, sw);
        return sw.toString();
    }

    /**
     * 
     * @param o
     * @param type 未使用
     * @return
     * @throws JsonbException 
     */
    @Override
    public String toJson(Object o, Type type) throws JsonbException {
        return toJson(o);
    }

    @Override
    public void toJson(Object o, Writer writer) throws JsonbException {
        JakartaConvert jc = new JakartaConvert();
        JsonValue v = Rebind.valueOf(o, jc);
        provider.createWriter(writer).write(v);
    }

    @Override
    public void toJson(Object o, Type type, Writer writer) throws JsonbException {
        toJson(o, writer);
    }

    @Override
    public void toJson(Object o, OutputStream out) throws JsonbException {
        JakartaConvert jc = new JakartaConvert();
        JsonValue v = Rebind.valueOf(o, jc);
        provider.createWriter(out).write(v);
    }

    @Override
    public void toJson(Object o, Type type, OutputStream out) throws JsonbException {
        toJson(o, out);
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
