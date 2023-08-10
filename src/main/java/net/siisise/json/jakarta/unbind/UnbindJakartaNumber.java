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

import jakarta.json.JsonNumber;
import java.lang.reflect.Type;
import net.siisise.bind.TypeUnbind;
import net.siisise.bind.format.TypeFormat;

/**
 *
 */
public class UnbindJakartaNumber implements TypeUnbind {

    @Override
    public Type[] getSrcTypes() {
        return new Type[] { JsonNumber.class };
    }

    @Override
    public <T> T valueOf(Object src, TypeFormat<T> format) {
        if (  src instanceof JsonNumber ) {
            return format.numberFormat(((JsonNumber)src).numberValue());
        }
        return (T) this;
    }
    
}
