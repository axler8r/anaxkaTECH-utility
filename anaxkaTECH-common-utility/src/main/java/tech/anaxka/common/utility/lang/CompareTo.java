package tech.anaxka.common.utility.lang;

import java.util.Map;
import tech.anaxka.common.utility.functor.Builder;

/**
 * Facilitates implementation of {@link Comparable}.
 *
 * @author Axl Mattheus
 */
public class CompareTo {

    /**
     * Constructs an implementation of {@link CompareToBuilder}.
     *
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
         *
         * @param <T> type parameter.
         * @param lhs the left hand side of the &quot;compare to expression&quot;.
         * @param rhs the right hand side of the &quot;compare to expression&quot;.
         * @return An instance of {@code this} to continue constructing the builder.
         * @see Comparable#compareTo(java.lang.Object).
         */
        <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs);

        // compare Iterables
        // RULES:
        // a and b are Iterables<Compareable<T>>
        // a == b
        //     if a.length == b.length &&
        //     foreach (ea, eb : a, b) ea.compareTo(eb) == 0
        // a > b
        //     if a.length > b.length &&
        //     foreach (ea, eb : a, b) ea.compareTo(eb) == 0 unti b.length is reached
        // a < b
        //     if a.length < b.length &&
        //     foreach (ea, eb : a, b) ea.compareTo(eb) == 0 until a.lenght is reached
        // a > b
        //     when first ea in foreach (ea, eb : a, b) ea.compareTo(eb) > 0
        // a < b
        //     when first ea in foreach (ea, eb : a, b) ea.compareTo(eb) < 0
        // a > b
        //     if a != null && b == null
        // a < b
        //     if a == null && b != null
        <S extends Comparable<S>, T extends Iterable<S>> CompareToBuilder append(
                final T lhs,
                final T rhs);

        // compare Maps
        <S extends Comparable<S>, T extends Comparable<T>, M extends Map<S, T>> CompareToBuilder append(
                final M lhs,
                final M rhs);

        // compare Arrays
        <T extends Comparable<T>> CompareToBuilder append(final T[] lhs, final T[] rhs);

        /**
         * Determines if one {@code object} is less than, equal to or greater than another.
         *
         * @return A result consistent with the contract specified by {@link java.lang.Comparable}.
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
        public <S extends Comparable<S>, T extends Iterable<S>> CompareToBuilder append(T lhs, T rhs) {
            return this;
        }

        @Override
        public <S extends Comparable<S>, T extends Comparable<T>, M extends Map<S, T>> CompareToBuilder append(M lhs, M rhs) {
            return this;
        }

        @Override
        public <T extends Comparable<T>> CompareToBuilder append(T[] lhs, T[] rhs) {
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
    }
}
