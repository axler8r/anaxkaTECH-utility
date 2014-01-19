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

import java.util.Set;
import tech.anaxka.common.utility.functor.Builder;

/**
 * <p>SetBuilder class.</p>
 *
 * @author Adolf.Mattheus
 * @param <T>
 * @version $Id: $Id
 * @since 0.08.000.0000
 */
public class SetBuilder<T> implements Builder<Set<T>> {

    private final Set<T> __;

    private SetBuilder(final Set<T> set) {
        if (set == null) {
            throw new IllegalArgumentException();
        }
        __ = set;
    }

    /**
     * <p>setBuilder.</p>
     *
     * @param set a {@link java.util.Set} object.
     * @param <T> a T object.
     * @return a {@link tech.anaxka.common.utility.builder.SetBuilder} object.
     */
    public static final <T> SetBuilder<T> setBuilder(final Set<T> set) {
        return new SetBuilder<>(set);
    }

    /**
     * <p>append.</p>
     *
     * @param element a T object.
     * @return a {@link tech.anaxka.common.utility.builder.SetBuilder} object.
     */
    public final SetBuilder append(final T element) {
        __.add(element);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Set<T> build() {
        return __;
    }
}