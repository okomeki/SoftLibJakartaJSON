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

import net.siisise.bind.TypeUnbind;
import net.siisise.bind.UnbindList;

/**
 * Jakarta EE JSON用.
 * Maven にあるJSON APIがJDK 11以降なので JDK 11以降用
 * @since JDK 11
 */
public class JakartaUnbind implements UnbindList {
    
    static final TypeUnbind[] unbind = new TypeUnbind[] {
            new UnbindJakarta(),
            new UnbindJakartaNumber(),
            new UnbindJakartaString()
    };

    @Override
    public TypeUnbind[] getList() {
        return unbind;
    }
    
}
