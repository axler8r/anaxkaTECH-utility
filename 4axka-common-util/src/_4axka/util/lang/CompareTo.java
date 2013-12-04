package _4axka.util.lang;

public class CompareTo {

    public static CompareToBuilder compareToBuilder() {
        return new CompareToBuilderImpl();
    }

    public static interface CompareToBuilder {

        <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs);

        int compare();
    }

    private static final class CompareToBuilderImpl implements CompareToBuilder {

        private static final int LESS_THAN = -1;
        private static final int EQUAL = 0;
        private static final int GREATER_THAN = 1;
        private int __result = EQUAL;

        private CompareToBuilderImpl() {
        }

        @Override
        public <T extends Comparable<T>> CompareToBuilder append(final T lhs, final T rhs) {
            // short circuit - only continue when current evaluation of objects are equal
            if (__result == EQUAL) {
                __result = compare(lhs, rhs);
            }
            return this;
        }

        @Override
        public int compare() {
            return __result;
        }

        private <T extends Comparable<T>> int compare(final T lhs, final T rhs) {
            if (lhs == rhs) {
                return EQUAL;
            }
            if (rhs == null) {
                return GREATER_THAN;
            }
            if (lhs == null) {
                return LESS_THAN;
            }

            return lhs.compareTo(rhs);
        }
    }
}
