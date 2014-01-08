package tech.anaxka.common.utility.lang;

import tech.anaxka.common.utility.functor.Builder;

public class Equals {

    public static final <T> boolean isEquatable(final T lhs, final T rhs) {
        return !isSameObject(lhs, rhs) && !isNull(rhs) && isAssignmentCompatible(lhs, rhs);
    }

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

    public static interface EqualsBuilder {

        <T> EqualsBuilder append(final T lhs, final T rhs);

        boolean isEqual();
    }

    private static final class EqualsBuilderImpl implements EqualsBuilder {

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
        public boolean isEqual() {
            return __isEqual;
        }
    }
}
