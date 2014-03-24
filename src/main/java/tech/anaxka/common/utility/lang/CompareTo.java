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


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import tech.anaxka.common.utility.functor.Builder;


/**
 * A {@link Builder builder} to help construct concise
 * {@link Comparable#compareTo(java.lang.Object) compareTo} implementations.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 */
public class CompareTo {

    /** @see Comparable#compareTo(java.lang.Object) */
    public static final int LESS_THAN = -1;

    /** @see Comparable#compareTo(java.lang.Object) */
    public static final int EQUAL = 0;

    /** @see Comparable#compareTo(java.lang.Object) */
    public static final int GREATER_THAN = 1;

    private CompareTo() {
    }

    /**
     * Creates a {@link CompareToBuilder}.
     *
     * @return a {@link Builder builder} to implement {@link Comparable#compareTo(java.lang.Object)}
     *         operations.
     */
    public static CompareToBuilder compareToBuilder() {
        return new CompareToBuilderImpl();
    }

    /**
     * Evaluates if an instance can be used in a
     * {@link Comparable#compareTo(java.lang.Object) compareTo} implementation.
     *
     * @param <T>  the type to check for comparability &mdash; inferred by the Java compiler.
     * @param that the instance to check for comparability.
     *
     * @return {@code true} if the subject is comparable.
     */
    public static <T> boolean isComparable(final T that) {
        return !(that == null);
    }

    /**
     * Contract of a
     * {@linkplain Comparable#compareTo(java.lang.Object) compare-to} {@link Builder builder}.
     */
    public static interface CompareToBuilder
            extends Builder<Integer, CompareToBuildException> {

        /**
         * Appends object state of the left-hand-side and right-hand-side of the
         * {@linkplain Comparable#compareTo(java.lang.Object) compare-to} operation.
         *
         * @param <T> the type of the instances being compared &mdash; inferred by the Java
         *            compiler.
         * @param lhs the left-hand-side of the comparison.
         * @param rhs the right-hand-side of the comparison.
         *
         * @return A {@link CompareToBuilder builder} to continue constructing the compare-to
         *         operation.
         */
        <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs);

        /**
         * Appends a {@link Collection colletion} of object states of the left-hand-side and
         * right-hand-side of the {@linkplain Comparable#compareTo(java.lang.Object) compare-to}
         * operation.
         *
         * @param <T> the type of the instances in the {@link Collection} being compared &mdash;
         *            inferred by the Java compiler.
         * @param lhs the left-hand-side {@link Collection} of the comparison.
         * @param rhs the right-hand-side {@link Collection} of the comparison.
         *
         * @return A {@link CompareToBuilder builder} to continue constructing the compare-to
         *         operation.
         */
        <T extends Comparable<T>> CompareToBuilder append(
                final Collection<T> lhs,
                final Collection<T> rhs);

        // TODO: define an append that takes two Iterables and a Comparator to enforce ordering...
        // <T extends Comparable<T>> CompareToBuilder append(
        //        final Iterable<T> lhs,
        //        final Iterable<T> rhs,
        //        final Comparator<T> c);
        /**
         * Appends an {@code array} of object states of the left-hand-side and right-hand-side of
         * the {@linkplain Comparable#compareTo(java.lang.Object) compare-to} operation.
         *
         * @param <T> the type of the instances in the {@code array} being compared &mdash; inferred
         *            by the Java compiler.
         * @param lhs the left-hand-side {@code array} of the comparison.
         * @param rhs the right-hand-side {@code array} of the comparison.
         *
         * @return A {@link CompareToBuilder builder} to continue constructing the compare-to
         *         operation.
         */
        <T extends Comparable<T>> CompareToBuilder append(final T[] lhs, final T[] rhs);

        /**
         * Appends a {@link Map map} of object states of the left-hand-side and right-hand-side of
         * the {@linkplain Comparable#compareTo(java.lang.Object) compare-to} operation.
         *
         * @param <K> the type of the <b>keys</b> in the {@link Map} being compared &mdash; inferred
         *            by the Java compiler.
         * @param <V> the type of the <b>values</b> in the {@link Map} being compared &mdash;
         *            inferred by the Java compiler.
         * @param lhs the left-hand-side {@link Map} of the comparison.
         * @param rhs the right-hand-side {@link Map} of the comparison.
         *
         * @return A {@link CompareToBuilder builder} to continue constructing the compare-to
         *         operation.
         */
        <K extends Comparable<K>, V extends Comparable<V>> CompareToBuilder append(
                final Map<K, V> lhs,
                final Map<K, V> rhs);

        @Override
        Integer build();
    }

    private static class CompareToBuilderImpl
            implements CompareToBuilder {

        private int __result = EQUAL;

        private CompareToBuilderImpl() {
        }

        /** {@inheritDoc} */
        @Override
        public <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs) {
            // short circuit - only continue when last evaluation of objects were equal
            if (__result == EQUAL) {
                __result = compare(lhs, rhs);
            }

            return this;
        }

        /** {@inheritDoc} */
        @Override
        public <T extends Comparable<T>> CompareToBuilder append(
                final Collection<T> lhs,
                final Collection<T> rhs) {
            if (lhs == null && rhs != null) {
                __result = LESS_THAN;
            } else if (rhs == null && lhs != null) {
                __result = GREATER_THAN;
            } else if (lhs != null && rhs != null) {
                if (lhs.size() == rhs.size()) {
                    __result = compareSameSizeCollecitons(lhs, rhs);
                } else {
                    __result = compareDifferentSizeCollections(lhs, rhs);
                }
            }

            return this;
        }

        /** {@inheritDoc} */
        @Override
        public <T extends Comparable<T>> CompareToBuilder append(final T[] lhs, final T[] rhs) {
            if (lhs == null && rhs != null) {
                __result = LESS_THAN;
            } else if (rhs == null && lhs != null) {
                __result = GREATER_THAN;
            } else if (lhs != null && rhs != null) {
                if (lhs.length == rhs.length) {
                    __result = compareSameSizeCollecitons(
                            Arrays.asList(lhs),
                            Arrays.asList(rhs));
                } else {
                    __result = compareDifferentSizeCollections(
                            Arrays.asList(lhs),
                            Arrays.asList(rhs));
                }
            }

            return this;
        }

        /** {@inheritDoc} */
        @Override
        public <K extends Comparable<K>, V extends Comparable<V>> CompareToBuilder append(
                final Map<K, V> lhs,
                final Map<K, V> rhs) {
            if (lhs == null && rhs != null) {
                __result = LESS_THAN;
            } else if (rhs == null && lhs != null) {
                __result = GREATER_THAN;
            } else if (lhs != null && rhs != null) {
                if (lhs.size() == rhs.size()) {
                    __result = compareSameSizeMaps(lhs, rhs);
                } else {
                    __result = compareDifferentSizeMaps(lhs, rhs);
                }
            }

            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Integer build() {
            return __result;
        }

        private <T extends Comparable<T>> int compare(final T lhs, final T rhs) {
            if (lhs == rhs) {
                return EQUAL;
            } else if (rhs == null) {
                return GREATER_THAN;
            } else if (lhs == null) {
                return LESS_THAN;
            } else {
                return lhs.compareTo(rhs);
            }
        }

        private <T extends Comparable<T>> int compareSameSizeCollecitons(
                final Collection<T> lhs,
                final Collection<T> rhs) {
            int result_ = EQUAL;

            final Iterator<T> lhsi_ = lhs.iterator();
            final Iterator<T> rhsi_ = rhs.iterator();
            while (lhsi_.hasNext() && result_ == EQUAL) {
                result_ = compare(lhsi_.next(), rhsi_.next());
            }

            return result_;
        }

        private <T extends Comparable<T>> int compareDifferentSizeCollections(
                final Collection<T> lhs,
                final Collection<T> rhs) {
            int result_ = EQUAL;

            Iterator<T> si_;
            Iterator<T> li_;
            if (lhs.size() < rhs.size()) {
                si_ = lhs.iterator();
                li_ = rhs.iterator();
            } else {
                si_ = rhs.iterator();
                li_ = lhs.iterator();
            }
            while (si_.hasNext() && result_ == EQUAL) {
                result_ = compare(si_.next(), li_.next());
            }

            return result_;
        }

        private <K extends Comparable<K>, V extends Comparable<V>> int compareSameSizeMaps(
                final Map<K, V> lhs,
                final Map<K, V> rhs) {
            int result_ = EQUAL;

            final Iterator<Map.Entry<K, V>> lhski_ = lhs.entrySet().iterator();
            final Iterator<Map.Entry<K, V>> rhski_ = rhs.entrySet().iterator();
            while (lhski_.hasNext() && result_ == EQUAL) {
                final Map.Entry<K, V> lhse_ = lhski_.next();
                final Map.Entry<K, V> rhse_ = rhski_.next();
                result_ = compare(lhse_.getKey(), rhse_.getKey());
                if (result_ == EQUAL) {
                    result_ = compare(lhse_.getValue(), rhse_.getValue());
                }
            }

            return result_;
        }

        private <K extends Comparable<K>, V extends Comparable<V>> int compareDifferentSizeMaps(
                final Map<K, V> lhs,
                final Map<K, V> rhs) {
            int result_ = EQUAL;

            Iterator<Map.Entry<K, V>> sesi_;
            Iterator<Map.Entry<K, V>> lesi_;
            if (lhs.size() < rhs.size()) {
                sesi_ = lhs.entrySet().iterator();
                lesi_ = rhs.entrySet().iterator();
            } else {
                sesi_ = rhs.entrySet().iterator();
                lesi_ = lhs.entrySet().iterator();
            }
            while (sesi_.hasNext() && result_ == EQUAL) {
                final Map.Entry<K, V> lhse_ = sesi_.next();
                final Map.Entry<K, V> rhse_ = lesi_.next();
                result_ = compare(lhse_.getKey(), rhse_.getKey());
                if (result_ == EQUAL) {
                    result_ = compare(lhse_.getValue(), rhse_.getValue());
                }
            }

            return result_;
        }
    }
}
