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
package net.siisise.json.jakarta.unbind;

import jakarta.json.JsonValue;
import java.lang.reflect.Type;
import net.siisise.bind.TypeUnbind;
import net.siisise.bind.format.TypeFormat;
import net.siisise.json.JSONBoolean;
import net.siisise.json.base.JSONBaseNULL;

/**
 * Jakarta JSON 固定値.
 * SoftLibJSON と同じなので省略したい.
 */
public class UnbindJakarta implements TypeUnbind {
    
    @Override
    public Type[] getSrcTypes() {
        return new Type[] { JsonValue.class, JSONBaseNULL.class, JSONBoolean.class };
    }

    @Override
    public Object valueOf(Object src, TypeFormat format) {
        if ( src == JsonValue.NULL || src instanceof JSONBaseNULL ) {
            return format.nullFormat();
        } else if ( src instanceof JSONBoolean ) {
            return format.booleanFormat(((JSONBoolean)src).map());
        } else if ( src == JsonValue.TRUE ) {
            return format.booleanFormat(true);
        } else if ( src == JsonValue.FALSE ) {
            return format.booleanFormat(false);
        }
        return this;
    }
}
