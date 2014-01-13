/*
 * Copyright (c) 2014, Adolf.Mattheus
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package tech.anaxka.common.utility.builder;

import java.util.Map;
import tech.anaxka.common.utility.functor.Builder;

/**
 *
 * @author Adolf.Mattheus
 * @param <K>
 * @param <V>
 */
public class MapBuilder<K, V> implements Builder<Map<K, V>>{
    private final Map<K, V> __;

    private MapBuilder(Map<K, V> map) {
        if (map == null) {
            throw new IllegalArgumentException();
        }

        __ = map;
    }
    
    public static <K, V> MapBuilder mapBuilder(final Map<K, V> map) {
        return new MapBuilder(map);
    }

    public MapBuilder append(final K key, final V value) {
        __.put(key, value);

        return this;
    }

    @Override
    public Map<K, V> build() {
        return __;
    }
}
