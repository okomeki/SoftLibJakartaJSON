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

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Optional;
import net.siisise.bind.Rebind;
import net.siisise.io.FrontPacket;
import net.siisise.io.StreamFrontPacket;
import net.siisise.json.JSON;
import net.siisise.json.bind.target.JSONFormat;

/**
 * Jakarta EE JSON Binding 2.x.
 * Rebind の機能をJSON Binding風にしたもの.
 * 
 */
public class JSONB implements Jsonb {

    protected JsonbConfig conf;
    
    LinkedList<Reader> readers = new LinkedList<>();
    LinkedList<Writer> writers = new LinkedList<>();
    LinkedList<InputStream> ins = new LinkedList<>();
    LinkedList<OutputStream> outs = new LinkedList<>();

    public JSONB() {
    }

    /*
     * 
     * @param config 
     */
    public JSONB(JsonbConfig config) {
        conf = config;
    }

    @Override
    public <T> T fromJson(String src, Class<T> type) throws JsonbException {
        return fromJson(src, (Type)type);
    }

    /**
     * 
     * @param <T>
     * @param src
     * @param type
     * @return
     * @throws JsonbException 
     */
    @Override
    public <T> T fromJson(String src, Type type) throws JsonbException {
        Object json = JSON.parse(src);
        return Rebind.valueOf(json, type);
    }

    @Override
    public <T> T fromJson(Reader reader, Class<T> type) throws JsonbException {
        return fromJson(reader, (Type)type);
    }

    @Override
    public <T> T fromJson(Reader reader, Type type) throws JsonbException {
        FrontPacket fp = new StreamFrontPacket(reader);
        Object json = JSON.parse(fp);
        readers.add(reader);
        return Rebind.valueOf(json, type);
    }

    @Override
    public <T> T fromJson(InputStream in, Class<T> type) throws JsonbException {
        return fromJson(in, (Type)type);
    }

    @Override
    public <T> T fromJson(InputStream in, Type type) throws JsonbException {
        FrontPacket fp = new StreamFrontPacket(in);
        Object json = JSON.parse(fp);
        ins.add(in);
        return Rebind.valueOf(json, type);
    }

    @Override
    public String toJson(Object o) throws JsonbException {
        JSONFormat format = JSON.NOBR_MINESC;
        if ( conf != null ) {
            Optional<Object> formatted = conf.getProperty("jsonb.to.json.formatted");
            if ( formatted.isPresent() && formatted.get().equals(Boolean.TRUE)) {
                format = JSON.TAB_MINESC;
            }
        }
        return Rebind.valueOf(o, format);
    }
    /**
     * JSONValue系(JsonValue互換)に変換する
     * @param object ソース
     * @param runtimeType ソースの型 未使用
     * @return JSONValue なJSON
     * @throws JsonbException 未定
     */
    @Override
    public String toJson(Object object, Type runtimeType) throws JsonbException {
        return toJson(object);
    }

    @Override
    public void toJson(Object o, Writer writer) throws JsonbException {
        try {
            writer.write(toJson(o));
            writer.flush();
        } catch (IOException e) {
            throw new JsonbException(e.getLocalizedMessage(),e);
        }
    }

    @Override
    public void toJson(Object o, Type type, Writer writer) throws JsonbException {
        toJson(o, writer);
    }

    @Override
    public void toJson(Object o, OutputStream out) throws JsonbException {
        try {
            Charset charset = StandardCharsets.UTF_8;
            Optional<Object> encopt = conf.getProperty("jsonb.to.json.encoding");
            if ( encopt.isPresent() ) {
                charset = Charset.forName((String)encopt.get());
            }
            out.write(toJson(o).getBytes(charset));
            out.flush();
            outs.add(out);
        } catch (IOException e) {
            throw new JsonbException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void toJson(Object o, Type type, OutputStream out) throws JsonbException {
        toJson(o, out);
    }

    /**
     * 使ったものを閉じるかもしれない
     */
    @Override
    public void close() {
        while ( !readers.isEmpty() ) {
            try {
                readers.remove().close();
            } catch (IOException e) {
            }
        }
        while ( !writers.isEmpty() ) {
            try {
                writers.remove().close();
            } catch ( IOException e ) {
            }
        }
        while ( !ins.isEmpty() ) {
            try {
                ins.remove().close();
            } catch ( IOException e ) {
            }
        }
        while ( !outs.isEmpty() ) {
            try {
                outs.remove().close();
            } catch ( IOException e ) {
            }
        }
    }
}
