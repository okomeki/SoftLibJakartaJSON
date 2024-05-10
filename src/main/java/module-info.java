/*
 * Copyright 2024 okome.
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

module net.siisise.json.jakarta {
    requires java.logging;
    requires jakarta.json;
    requires jakarta.json.bind;
    requires net.siisise;
    requires net.siisise.json;
    requires net.siisise.abnf;
    requires net.siisise.rebind;
    requires java.json;
    requires java.json.bind;
    
    provides jakarta.json.bind.spi.JsonbProvider with net.siisise.json.jakarta.bind.spi.JSONBProvider;
    provides jakarta.json.spi.JsonProvider with net.siisise.json.jakarta.spi.JSONXProvider;
    provides net.siisise.bind.UnbindList with net.siisise.json.jakarta.unbind.JakartaUnbind;
    provides net.siisise.bind.format.TypeFormat with net.siisise.json.jakarta.JakartaConvert;
}
