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
 * A {@link Builder builder} to help construct concise
 * {@link Object#equals(java.lang.Object) equals} implementations.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 * @see Object#equals(java.lang.Object) 
 */
public class Equals {

    private Equals() {
    }

    /**
     * Evaluates if two {@link Object object}s are equitable.
     * 
     * @param <T> the type of the {@link Object object}s being evaluated.
     * @param lhs the left-hand-side.
     * @param rhs the right-hand-side.
     *
     * @return {@code true} if the two instances are equitable.
     */
    public static final <T> boolean isEquatable(final T lhs, final T rhs) {
        return !isSameObject(lhs, rhs) && !isNull(rhs) && isAssignmentCompatible(lhs, rhs);
    }

    /**
     * Returns an instance of a {@link Builder builder} to determine equality between two instances.
     * 
     * @return An {@link EqualsBuilder}.
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
     * Contract of a {@linkplain Object#equals(java.lang.Object) equals} {@link Builder builder}.
     */
    public static interface EqualsBuilder
            extends Builder<Boolean, EqualsBuildException> {

        /**
         * Appends object state of the left-hand-side and right-hand-side of the
         * {@linkplain Object#equals(java.lang.Object) equals} operation.
         *
         * @param <T> the type of the instances being compared &mdash; inferred by the Java
         *            compiler.
         * @param lhs the left-hand-side of the comparison.
         * @param rhs the right-hand-side of the comparison.
         *
         * @return A {@link EqualsBuilder builder} to continue constructing the equals
         *         operation.
         */
        <T> EqualsBuilder append(final T lhs, final T rhs);

        /** {@inheritDoc} */
        @Override
        Boolean build();
    }

    private static class EqualsBuilderImpl
            implements EqualsBuilder {

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
