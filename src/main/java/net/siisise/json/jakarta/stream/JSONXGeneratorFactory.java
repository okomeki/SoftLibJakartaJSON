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

import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonGeneratorFactory;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import net.siisise.json.JSON;
import net.siisise.json.bind.target.JSONFormat;

/**
 *
 */
public class JSONXGeneratorFactory implements JsonGeneratorFactory {

    private final JSONFormat format;
    
    public JSONXGeneratorFactory(Map<String, ?> config) {
        format = (config != null && config.containsKey(JsonGenerator.PRETTY_PRINTING)) ? JSON.TAB : JSON.NOBR;
    }

    /**
     * Writer用 JsonGenerator を用意する.
     * @param writer
     * @return 
     */
    @Override
    public JsonGenerator createGenerator(Writer writer) {
        return new JSONXGenerator(writer, format);
    }

    /**
     * Stream 用 に UTF-8 の JsonGenerator を用意する.
     * @param out 出力先.
     * @return 
     */
    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        return createGenerator(out, StandardCharsets.UTF_8);
    }

    /**
     * 
     * @param out
     * @param chrst
     * @return 
     */
    @Override
    public JsonGenerator createGenerator(OutputStream out, Charset chrst) {
        return new JSONXGenerator(new OutputStreamWriter(out, chrst), format);
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        Map config = new HashMap<>();
        if ( format == JSON.TAB ) {
            config.put(JsonGenerator.PRETTY_PRINTING, true);
        }
        return config;
    }
    
}
