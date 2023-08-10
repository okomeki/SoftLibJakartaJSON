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

import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.spi.JsonProvider;

/**
 *
 */
public class JSONBBuilder implements JsonbBuilder {

    private JsonbConfig conf;
    private JsonProvider provider;

    /**
     * SoftLibJakartaJSON な JsonB
     * @param jc
     * @return 
     */
    @Override
    public JSONBBuilder withConfig(JsonbConfig jc) {
        conf = jc;
        return this;
    }

    /**
     * 一般的なJsonB
     * @param jp
     * @return 
     */
    @Override
    public JSONBBuilder withProvider(JsonProvider jp) {
        provider = jp;
        return this;
    }

    @Override
    public JSONB build() {
        if ( provider != null ) {
            return new JSONProviderB(provider, conf);
        }
        return new JSONB(conf);
    }
    
}
