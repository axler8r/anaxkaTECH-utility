/* 
 * Copyright © 2011, 4axka (Pty) Ltd
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */
package tech.anaxka.common.utility.builder;


import java.util.Map;
import tech.anaxka.common.utility.functor.Builder;


/**
 * A {@linkplain Builder builder} for {@linkplain Map map} objects.
 *
 * @param <K> the type of the Key for the {@link Map map} under construction.
 * @param <V> the type of the Value for the {@link Map map} under construction.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 * @see Builder
 * @see Map
 */
public class MapBuilder<K, V>
        implements Builder<Map<K, V>> {

    private final Map<K, V> __;

    private MapBuilder(final Map<K, V> map) {
        if (map == null) {
            throw new IllegalArgumentException();
        }

        __ = map;
    }

    /**
     * Creates a {@link MapBuilder builder} to make population of the specified {@link Map map}
     * simple.
     *
     * @param <K> the type of the {@link Map map} under construction's <b>key</b>. The Java compiler
     *            should be intelligent enough to infer &lt;K&gt;.
     * @param <V> the type of the {@link Map map} under construction's <b>value</b>. The Java
     *            compiler should be intelligent enough to infer &lt;V&gt;.
     * @param map the {@link Map map} under construction.
     *
     * @return A {@link MapBuilder builder} to continue the construction of the
     *         {@linkplain Map underlying map}.
     */
    public static <K, V> MapBuilder mapBuilder(final Map<K, V> map) {
        return new MapBuilder(map);
    }

    /**
     * Adds a {@link Map.Entry entry} identified by the <b>key</b> and mapped to the <b>value</b> to
     * the {@link Map map} under construction.
     *
     * @param key   key.
     * @param value value.
     *
     * @return A {@link MapBuilder builder} to continue the construction of the
     *         {@linkplain Map underlying map}.
     */
    public MapBuilder append(final K key, final V value) {
        __.put(key, value);

        return this;
    }

    /** {@inheritdoc} */
    @Override
    public Map<K, V> build() {
        return __;
    }
}
