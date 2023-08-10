/*
 * Copyright 2022 okome.
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

import jakarta.json.JsonNumber;
import net.siisise.json.base.JSONBaseNumber;

/**
 * 互換要素
 */
public class JSONXNumber extends JSONBaseNumber implements JsonNumber {

    public JSONXNumber(Number num) {
        super(num);
    }

    @Override
    public ValueType getValueType() {
        return ValueType.NUMBER;
    }
}
