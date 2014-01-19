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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import tech.anaxka.common.utility.functor.Builder;

/**
 * Facilitates implementation of {@link java.lang.Comparable}.
 * <p>
 *
 * @author Axl Mattheus
 * @version $Id: $Id
 */
public class CompareTo {
    /** Constant <code>LESS_THAN=-1</code> */
    public static final int LESS_THAN = -1;
    /** Constant <code>EQUAL=0</code> */
    public static final int EQUAL = 0;
    /** Constant <code>GREATER_THAN=1</code> */
    public static final int GREATER_THAN = 1;

    private CompareTo() {}

    /**
     * Constructs an implementation of {@link tech.anaxka.common.utility.lang.CompareTo.CompareToBuilder}.
     * <p>
     *
     * @return A newly constructed {@linkplain CompareToBuilder}.
     */
    public static CompareToBuilder compareToBuilder() {
        return new CompareToBuilderImpl();
    }

    /**
     * <p>isComparable.</p>
     *
     * @param that a T object.
     * @param <T> a T object.
     * @return a boolean.
     */
    public static <T> boolean isComparable(final T that) {
        return that != null;
    }

    /**
     * An implementation of the <i><a href="http://goo.gl/6kIIaI">Builder Pattern</a></i>.
     */
    public static interface CompareToBuilder extends Builder<Integer> {

        /**
         * Populate the {@linkplain CompareToBuilder builder}'s state space to compare
         * {@linkplain Comparable comparable}s.
         * <p/>
         * @param <T> type parameter.
         * @param lhs the left hand side of the &quot;compare to expression&quot;.
         * @param rhs the right hand side of the &quot;compare to expression&quot;.
         * <p/>
         * @return An instance of {@code this} to continue constructing the builder.
         * <p/>
         * @see Comparable#compareTo(java.lang.Object).
         */
        <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs);

        /**
         * Populate the {@linkplain CompareToBuilder builder}'s state space to compare
         * {@linkplain Collection collection}s.
         * <p/>
         * <b>Rules</b><br/>
         * <i>Pre-condition &mdash; Collections are iterated in step and in the same order.</i><br/>
         * <i>Pre-condition &mdash; Elements must implement {@link Comparable}.</i><br/>
         * <i>Pre-condition &mdash; Elements of both collections must have the same type.</i>
         * <ol>
         * <li/>Two collections are equal if all elements, are equal and the collections have equal
         * length.
         * <li/>A collection is greater than another as soon as an element in the first collection
         * is greater than the corresponding element in the second.
         * <li/>A collection is less than another as soon as an element in the first collection is
         * less than the corresponding element in the second.
         * <li/>A collection is greater than another if the first one is not null and the second one
         * is null.
         * <li/>A collection is less than another if the first one is null and the second one is not
         * null.
         * <li/>A collection is greater than another if the first one is not empty and the second
         * one is empty.
         * <li/>A collection is less than another if the first one is empty and the second one is
         * not empty.
         * </ol>
         * <p/>
         * @param <T> Type parameter.
         * @param lhs Source {@linkplain Collection collection}.
         * @param rhs Target {@linkplain Collection collection}.
         * <p/>
         * @return 0 if
         * <p/>
         * @see Comparable#compareTo(java.lang.Object).
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
         * Populate the {@linkplain CompareToBuilder builder}'s state space to compare
         * {@link Array}s.
         * <p/>
         * @param <T>
         * @param lhs
         * @param rhs <p/>
         * @return <p/>
         * @see #append(java.util.Collection, java.util.Collection)
         */
        <T extends Comparable<T>> CompareToBuilder append(final T[] lhs, final T[] rhs);

        /**
         * Populate the {@linkplain CompareToBuilder builder}'s state space to compare
         * {@linkplain Map map}s.
         * <p/>
         * @param <K>
         * @param <V>
         * @param lhs
         * @param rhs <p/>
         * @return
         */
        <K extends Comparable<K>, V extends Comparable<V>> CompareToBuilder append(
                final Map<K, V> lhs,
                final Map<K, V> rhs);

        /**
         * Determines if one {@code object} is less than, equal to or greater than another.
         * <p/>
         * @return A result consistent with the contract specified by {@link java.lang.Comparable}.
         * <p>
         * @see Comparable.
         */
        @Override
        Integer build();
    }

    private static class CompareToBuilderImpl implements CompareToBuilder {
        private int __result = EQUAL;

        private CompareToBuilderImpl() {
        }

        @Override
        public <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs) {
            // short circuit - only continue when last evaluation of objects were equal
            if (__result == EQUAL) {
                __result = compare(lhs, rhs);
            }

            return this;
        }

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
