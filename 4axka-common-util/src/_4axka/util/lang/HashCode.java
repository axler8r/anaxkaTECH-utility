package _4axka.util.lang;

public class HashCode {

    public static HashCodeBuilder hashCodeBuilder() {
        return new HashCodeBuilderImpl();
    }

    public static interface HashCodeBuilder {

        <S> HashCodeBuilder append(S subject);

        int hash();
    }

    private static final class HashCodeBuilderImpl implements HashCodeBuilder {

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
        public int hash() {
            return __result;
        }
    }
}
