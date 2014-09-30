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
package tech.anaxka.util.builder;


import java.util.List;
import tech.anaxka.util.functor.Builder;
import tech.anaxka.util.functor.FunctorException;


/**
 * A {@linkplain Builder builder} for {@linkplain List list} objects.
 *
 * @param <T> the type of the @{link List list} under construction.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 * @see Builder
 * @see List
 */
public final class ListBuilder<T>
        implements Builder<List<T>, FunctorException> {

    private final List<T> __;

    private ListBuilder(final List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }

        __ = list;
    }

    /**
     * Creates a {@link ListBuilder builder} to make population of the specified {@link List list}
     * simple.
     *
     * @param <T>  the type of the {@link List list} under construction. The Java compiler should be
     *             intelligent enough to infer &lt;T&gt;.
     * @param list the {@link List list} under construction.
     *
     * @return A {@link ListBuilder builder} to continue the construction of the
     *         {@linkplain List underlying list}.
     */
    public static final <T> ListBuilder listBuilder(final List<T> list) {
        return new ListBuilder(list);
    }

    /**
     * Adds an element to the @{link List list} under construction.
     * 
     * @param element the element to add to the list.
     *
     * @return A {@link ListBuilder builder} to continue the construction of the
     *         {@linkplain List underlying list}.
     */
    public ListBuilder append(final T element) {
        __.add(element);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public List<T> build() {
        return __;
    }
}
