package tech.anaxka.common.utility.lang;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import tech.anaxka.common.utility.functor.Builder;

/**
 * Facilitates implementation of {@link Comparable}.
 * <p>
 * @author Axl Mattheus
 */
public class CompareTo {

    /**
     * Constructs an implementation of {@link CompareToBuilder}.
     * <p>
     * @return A newly constructed {@linkplain CompareToBuilder}.
     */
    public static CompareToBuilder compareToBuilder() {
        return new CompareToBuilderImpl();
    }

    /**
     * An implementation of the <i><a href="http://goo.gl/6kIIaI">Builder Pattern</a></i>.
     */
    public static interface CompareToBuilder extends Builder<Integer> {

        /**
         * Populate the {@linkplain CompareToBuilder builder}'s state space.
         * <p>
         * @param <T> type parameter.
         * @param lhs the left hand side of the &quot;compare to expression&quot;.
         * @param rhs the right hand side of the &quot;compare to expression&quot;.
         * <p>
         * @return An instance of {@code this} to continue constructing the builder.
         * <p>
         * @see Comparable#compareTo(java.lang.Object).
         */
        <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs);

        /**
         * Compare {@link Iterable}s.
         * <p/>
         * <b>RULES:</b><br/>
         * <i> Let a : Iterable&lt;Comparable&lt;T&gt;&gt;, b :
         * Iterable&lt;Comparable&lt;T&gt;&gt;</i>
         * <ul>
         * <li/>a == b a.length == b.length &amp;&amp;<br/>
         * foreach (ea, eb : a, b) ea.compareTo(eb) == 0
         * <li/>a &GT; b a.length &GT; b.length &amp;&amp;<br/>
         * foreach (ea, eb : a, b) ea.compareTo(eb) == 0 until (a.length &GT; b.length) == true
         * <li/>a &GT; b when first ea in foreach (ea, eb : a, b) ea.compareTo(eb) &GT; 0
         * <li/>a &GT; b a != null && b == null
         * <li/>a &LT; b<br/>
         * a.length &LT; b.length &amp;&amp;<br/>
         * foreach (ea, eb : a, b) ea.compareTo(eb) == 0 until (a.lenght &LT; b.length) == true
         * <li/>a &LT; b when first ea in foreach (ea, eb : a, b) ea.compareTo(eb) &LT; 0
         * <li/>a &LT; b a == null && b != null
         * </ul>
         * <p/>
         * @param <T>
         * @param lhs
         * @param rhs <p>
         * @return
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
         * Compare {@link Array}s.
         * <p/>
         * <code>
         * RULES:<br/>
         * a : T[]<br/>
         * b : T[]<br/>
         * a == b<br/>
         * a.length == b.length &&<br/>
         * foreach (ea, eb : a, b) ea.compareTo(eb) == 0<br/>
         * a &GT; b<br/>
         * a.length &GT; b.length &amp;&amp;<br/>
         * foreach (ea, eb : a, b) ea.compareTo(eb) == 0 until (a.length &GT; b.length) == true<br/>
         * a &LT; b<br/>
         * a.length < b.length &amp;&amp;<br/> foreach (ea, eb : a, b) ea.compareTo(eb) == 0 until
         * (a.lenght &LT; b.length) == true<br/>
         * a &GT; b<br/>
         * when first ea in foreach (ea, eb : a, b) ea.compareTo(eb) &GT; 0<br/>
         * a &LT; b<br/>
         * when first ea in foreach (ea, eb : a, b) ea.compareTo(eb) &LT; 0<br/>
         * a &GT; b<br/>
         * a != null && b == null<br/>
         * a &LT; b<br/>
         * a == null && b != null<br/>
         * </code>
         * <p/>
         * @param <T>
         * @param lhs
         * @param rhs <p>
         * @return
         */
        <T extends Comparable<T>> CompareToBuilder append(final T[] lhs, final T[] rhs);

        /**
         * Compare {@link Map}s.
         * <p/>
         * <code>
         * RULES:<br/>
         * a and b are Map<K extends Comparable<K>, V extends Comparable <V>><br/>
         * a == b<br/>
         * a.keySet.length == b.keySet.length &amp;&amp;<br/>
         * foreach (ka, kb: a.keySet, b.keySet) a.value(ka).compareTo(b.value(kb)) == 0<br/>
         * a &GT; b<br/>
         * a.keySet.length &GT; b.keySet.length &amp;&amp;<br/>
         * foreach (ka, kb : a, b) a.value(ka).compareTo(b.value(kb)) == 0<br/>
         * until (a.length &GT; b.length) == true<br/>
         * a &LT; b<br/>
         * a.keySet.length &LT; b.keySet.length &amp;&amp;<br/>
         * foreach (ka, kb : a, b) a.value(ka).compareTo(b.value(kb)) == 0<br/>
         * until (a.length &LT; b.length) == true<br/>
         * a &GT; b<br/>
         * when first a.value(ka).compareTo(b.value(kb)) &GT; 0<br/>
         * in foreach (ka, kb : a.keySet, b.keySet)<br/>
         * a &LT; b<br/>
         * when first a.value(ka).compareTo(b.value(kb)) &LT; 0<br/>
         * in foreach (ka, kb : a.keySet, b.keySet)<br/>
         * a &GT; b<br/>
         * when first ka.compareTo(kb) &GT; 0 in foreach (ka, kb : a, b)<br/>
         * a &LT; b<br/>
         * when first ka.compareTo(kb) &LT; 0 in foreach (ka, kb : a, b)<br/>
         * a &GT; b<br/>
         * a != null &amp;&amp; b == null<br/>
         * a &LT; b<br/>
         * a == null &amp;&amp; b != null<br/>
         * </code>
         * <p/>
         * @param <K>
         * @param <V>
         * @param lhs
         * @param rhs <p>
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

        private static final int LESS_THAN = -1;
        private static final int EQUAL = 0;
        private static final int GREATER_THAN = 1;
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

            final Iterator<K> lhski_ = lhs.keySet().iterator();
            final Iterator<K> rhski_ = rhs.keySet().iterator();
            while (lhski_.hasNext() && result_ == EQUAL) {
                final K lhsk_ = lhski_.next();
                final K rhsk_ = rhski_.next();
                result_ = compare(lhsk_, rhsk_);
                if (result_ == EQUAL) {
                    result_ = compare(lhs.get(lhsk_), rhs.get(rhsk_));
                }
            }

            return result_;
        }

        private <K extends Comparable<K>, V extends Comparable<V>> int compareDifferentSizeMaps(
                final Map<K, V> lhs,
                final Map<K, V> rhs) {
            int result_ = EQUAL;

            Iterator<K> ski_;
            Iterator<K> lki_;
            if (lhs.size() < rhs.size()) {
                ski_ = lhs.keySet().iterator();
                lki_ = rhs.keySet().iterator();
            } else {
                ski_ = rhs.keySet().iterator();
                lki_ = lhs.keySet().iterator();
            }
            while (ski_.hasNext() && result_ == EQUAL) {
                final K lhsk_ = ski_.next();
                final K rhsk_ = lki_.next();
                result_ = compare(lhsk_, rhsk_);
                if (result_ == EQUAL) {
                    result_ = compare(lhs.get(lhsk_), rhs.get(rhsk_));
                }
            }

            return result_;
        }
    }
}
