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
package tech.anaxka.common.utility.lang;


import tech.anaxka.common.utility.functor.Builder;


/**
 * A {@link Builder builder} to help construct concise {@link Object#hashCode() hash code}
 * implementations.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 * @see Object#hashCode()
 */
public class HashCode {

    private HashCode() {
    }

    /**
     * Returns an instance of a {@link Builder builder} to calculate the hash code of an
     * {@link Object}.
     *
     * @return An {@link HashCodeBuilder}.
     */
    public static HashCodeBuilder hashCodeBuilder() {
        return new HashCodeBuilderImpl();
    }

    /**
     * Contract of a {@linkplain Object#hashCode() hash code} {@link Builder builder}.
     */
    public static interface HashCodeBuilder
            extends Builder<Integer, HashCodeBuildException> {

        /**
         * Appends object state to compute the {@linkplain Object#hashCode() hash code} of an
         * {@link Object}.
         *
         * @param <S>     the type of the object state.
         * @param subject the subject needed to help calculate a hash code.
         *
         * @return A {@link HashCodeBuilder builder} to continue constructing the {@code hashCode()}
         *         operation.
         */
        <S> HashCodeBuilder append(S subject);

        @Override
        Integer build();
    }

    private static class HashCodeBuilderImpl
            implements HashCodeBuilder {

        private static final int PRIME = 31;
        private int __result = 0;

        private HashCodeBuilderImpl() {
        }

        @Override
        public <S> HashCodeBuilder append(final S subject) {
            __result = PRIME * __result + ((subject == null) ? 0 : subject.hashCode());
            return this;
        }

        @Override
        public Integer build() {
            return __result;
        }
    }
}
