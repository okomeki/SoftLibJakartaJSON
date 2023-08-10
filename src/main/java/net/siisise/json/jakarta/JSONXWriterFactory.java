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

import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JSONXWriterFactory implements JsonWriterFactory {

    private final Map<String, ?> config;
    
    public JSONXWriterFactory(Map<String, ?> conf) {
        config = new HashMap<>(conf);
    }
    
    @Override
    public JsonWriter createWriter(Writer writer) {
        return new JSONXWriter(writer);
    }

    @Override
    public JsonWriter createWriter(OutputStream out) {
        return createWriter(out,StandardCharsets.UTF_8);
    }

    @Override
    public JsonWriter createWriter(OutputStream out, Charset charset) {
        return createWriter(new OutputStreamWriter(out,charset));
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return new HashMap<>(config);
    }
    
}
