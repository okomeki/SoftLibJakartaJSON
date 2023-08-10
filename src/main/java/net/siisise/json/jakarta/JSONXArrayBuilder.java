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

import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import net.siisise.bind.Rebind;

/**
 * 可変ではないのでこういうのが必要?
 */
public class JSONXArrayBuilder implements JsonArrayBuilder {

    private final JSONXArray array = new JSONXArray();

    @Override
    public JsonArrayBuilder add(JsonValue val) {
        array.add(val);
        return this;
    }

    @Override
    public JsonArrayBuilder add(String val) {
        array.add(new JSONXString(val));
        return this;
    }

    @Override
    public JsonArrayBuilder add(BigDecimal val) {
        array.add(new JSONXNumber(val));
        return this;
    }

    @Override
    public JsonArrayBuilder add(BigInteger val) {
        array.add(new JSONXNumber(val));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int val) {
        array.add(new JSONXNumber(val));
        return this;
    }

    @Override
    public JsonArrayBuilder add(long val) {
        array.add(new JSONXNumber(val));
        return this;
    }

    @Override
    public JsonArrayBuilder add(double val) {
        array.add(new JSONXNumber(val));
        return this;
    }

    @Override
    public JsonArrayBuilder add(boolean val) {
        array.add(Rebind.valueOf(val, JsonValue.class));
        return this;
    }

    @Override
    public JsonArrayBuilder addNull() {
        array.add(JsonValue.NULL);
        return this;
    }

    @Override
    public JsonArrayBuilder add(JsonObjectBuilder job) {
        array.add(job.build());
        return this;
    }

    @Override
    public JsonArrayBuilder add(JsonArrayBuilder jab) {
        array.add(jab.build());
        return this;
    }

    @Override
    public JsonArray build() {
        return array;
    }

    @Override
    public JsonArrayBuilder addAll(JsonArrayBuilder builder) {
        JsonArray ar = builder.build();
        array.addAll(ar);
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, JsonValue value) {
        array.add(index,value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, String value) {
        array.add(index, new JSONXString(value));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, BigDecimal value) {
        array.add(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, BigInteger value) {
        array.add(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, int value) {
        array.add(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, long value) {
        array.add(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, double value) {
        array.add(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, boolean value) {
        array.add(index, value ? JsonValue.TRUE : JsonValue.FALSE);
        return this;
    }

    @Override
    public JsonArrayBuilder addNull(int index) {
        array.add(index, JsonValue.NULL);
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, JsonObjectBuilder builder) {
        array.add(index, builder.build());
        return this;
    }

    @Override
    public JsonArrayBuilder add(int index, JsonArrayBuilder builder) {
        array.add(index, builder.build());
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, JsonValue value) {
        array.set(index, value);
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, String value) {
        array.set(index, new JSONXString(value));
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, BigDecimal value) {
        array.set(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, BigInteger value) {
        array.set(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, int value) {
        array.set(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, long value) {
        array.set(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, double value) {
        array.set(index, new JSONXNumber(value));
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, boolean value) {
        array.set(index, value ? JsonValue.TRUE : JsonValue.FALSE);
        return this;
    }

    @Override
    public JsonArrayBuilder setNull(int index) {
        array.set(index, JsonValue.NULL);
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, JsonObjectBuilder builder) {
        array.set(index,builder.build());
        return this;
    }

    @Override
    public JsonArrayBuilder set(int index, JsonArrayBuilder builder) {
        array.set(index,builder.build());
        return this;
    }

    @Override
    public JsonArrayBuilder remove(int index) {
        array.remove(index);
        return this;
    }

}
