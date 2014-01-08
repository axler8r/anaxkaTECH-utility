package tech.anaxka.common.utility.lang;

import tech.anaxka.common.utility.functor.Builder;

public class HashCode {

    public static HashCodeBuilder hashCodeBuilder() {
        return new HashCodeBuilderImpl();
    }

    public static interface HashCodeBuilder extends Builder<Integer> {

        <S> HashCodeBuilder append(S subject);

        @Override
        Integer build();
    }

    private static class HashCodeBuilderImpl implements HashCodeBuilder {

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
