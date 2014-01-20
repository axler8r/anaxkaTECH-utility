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
 *
 * @author Axl Mattheus
 */
public class Equals {
    private Equals() {}

    /**
     * <p>isEquatable.</p>
     *
     * @param lhs a T object.
     * @param rhs a T object.
     * @param <T> a T object.
     * @return a boolean.
     */
    public static final <T> boolean isEquatable(final T lhs, final T rhs) {
        return !isSameObject(lhs, rhs) && !isNull(rhs) && isAssignmentCompatible(lhs, rhs);
    }

    /**
     * <p>equalsBuilder.</p>
     *
     * @return a {@link tech.anaxka.common.utility.lang.Equals.EqualsBuilder} object.
     */
    public static EqualsBuilder equalsBuilder() {
        return new EqualsBuilderImpl();
    }

    private static <T> boolean isSameObject(final T lhs, final T rhs) {
        return lhs == rhs;
    }

    private static <T> boolean isNull(final T subject) {
        return subject == null;
    }

    private static <T> boolean isAssignmentCompatible(final T lhs, final T rhs) {
        // TODO: make this bulletproof...
        return (lhs == null && rhs == null)
                || (lhs != null && rhs != null && rhs.getClass().isAssignableFrom(lhs.getClass()));
    }

    /**
     *
     */
    public static interface EqualsBuilder extends Builder<Boolean> {

        /**
         *
         * @param <T>
         * @param lhs
         * @param rhs
         * @return
         */
        <T> EqualsBuilder append(final T lhs, final T rhs);

        /**
         *
         * @return
         */
        @Override
        Boolean build();
    }

    private static class EqualsBuilderImpl implements EqualsBuilder {

        private boolean __isEqual = true;

        private EqualsBuilderImpl() {
        }

        @Override
        public <T> EqualsBuilder append(T lhs, T rhs) {
            if (__isEqual) {
                __isEqual = (lhs != null) && (rhs != null) && lhs.equals(rhs);
            }
            return this;
        }

        @Override
        public Boolean build() {
            return __isEqual;
        }
    }
}
