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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import tech.anaxka.common.utility.functor.Builder;


/**
 * A {@link Builder builder} to help construct concise {@link Object#toString() toString}
 * implementations.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 */
public class ToString {

    private ToString() {
    }

    /**
     * Creates a {@link ToStringBuilder}.
     *
     * @param subject the subject for which the toString operation will be created.
     *
     * @return a {@link Builder builder} to implement {@link Object#toString()} operations.
     */
    public static final ToStringBuilder toStringBuilder(final Object subject) {
        return new ToStringBuilderImpl(subject);
    }

    /**
     * Contract of a {@linkplain Object#toString() to-string} {@link Builder builder}.
     */
    public static interface ToStringBuilder
            extends Builder<String, ToStringBuildException> {

        /**
         * Appends the label and subject to construct a string representation of an object.
         *
         * @param <S>     the type of the subject &mdash; inferred by Javac.
         * @param label   the label of the subject.
         * @param subject the subject to append to the string representation of the object.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        <S> ToStringBuilder append(final String label, final S subject);

        /**
         * Appends the label and a {@link Collection} to construct a string representation of an
         * object.
         *
         * @param <C>        the type of the element of a collection.
         * @param label      the label of the collection.
         * @param collection the collection.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        <C extends Collection<?>> ToStringBuilder append(final String label, final C collection);

        /**
         * Appends a label and an array to construct a string representation of an object.
         *
         * @param label the label of the array.
         * @param array the content of the array.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        ToStringBuilder append(final String label, final Object[] array);

        /**
         * Appends a label and a map to construct a string representation of an object.
         *
         * @param <M>   map.
         * @param label label.
         * @param map   the map to unroll.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        <M extends Map<?, ?>> ToStringBuilder append(final String label, final M map);

        /**
         * How many elements in a collection should be displayed?
         *
         * @param depth the number of elements that should be displayed.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        ToStringBuilder setUnrollDepth(final int depth);

        /**
         * Should the {@link Object#toString()} operation pretty print the string?
         *
         * @param pretty {@code true} to pretty print the string. The default is {@code false}.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        ToStringBuilder setPrettyPrint(final boolean pretty);

        /**
         * Should the operation display where the class was loaded from?
         *
         * @param display default is {@code true}.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        ToStringBuilder setDisplayLoadLocation(final boolean display);

        /**
         * Utility operation to turn the output of {@link Object#toString()} into a pretty string.
         *
         * @param string the string to pretty print.
         *
         * @return A {@link ToStringBuilder} to continue construction of the string.
         */
        String prettyPrint(final String string);

        /** {@inheritDoc} */
        @Override
        String build();
    }

    private static class ToStringBuilderImpl
            implements ToStringBuilder {

        private static final String NAME_HASH_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.NAME_HASH_DELIMITER",
                "@");
        private static final String NAME_VALUE_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.NAME_VALUE_DELIMITER",
                ", ");
        private static final String NAME_VALUE_SEPARATOR = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.NAME_VALUE_SEPARATOR",
                "=");
        private static final String MAP_NAME_VALUE_SEPARATOR = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.MAP_NAME_VALUE_SEPARATOR",
                " => ");
        private static final String COLLECTION_START_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.COLLECTION_START_DELIMITER",
                "[");
        private static final String COLLECTION_STOP_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.COLLECTION_STOP_DELIMITER",
                "]");
        private static final String OMITTED_START_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.OMITTED_START_DELIMITER",
                "... ");
        private static final String OMITTED_STOP_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.OMITTED_STOP_DELIMITER",
                " omitted ...");
        private static final String NULL = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.NULL",
                "null");
        private static final String STATE_STOP_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.STATE_STOP_DELIMITER",
                "}");
        private static final String STATE_START_DELIMITER = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.STATE_START_DELIMITER",
                "{");
        private static final String BYTECODE_LOCATION_LABEL = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.BYTECODE_LOCATION_LABEL",
                "Bytecode Location");
        private static final String INDENTATION = System.getProperty(
                "tech.anaxka.common.utility.lang.ToStringBuilder.INDENTATION",
                "    ");
        private static final String NEW_LINE = System.lineSeparator();
        private final Object __subject;
        private final Map<String, String> __state = new LinkedHashMap<>();
        private int __unrollDepth = 10;
        private boolean __displayLoadLocation = true;
        private boolean __pertyPrint = false;

        private ToStringBuilderImpl(final Object subject) {
            if (subject == null) {
                throw new IllegalArgumentException();
            }
            __subject = subject;
        }

        @Override
        public <S> ToStringBuilder append(final String label, final S subject) {
            if (label == null) {
                throw new IllegalArgumentException();
            }
            __state.put(label, subject == null ? NULL : subject.toString());
            return this;
        }

        @Override
        public <C extends Collection<?>> ToStringBuilder append(
                final String label,
                final C collection) {
            return append(label, unroll(collection));
        }

        @Override
        public ToStringBuilder append(final String label, final Object[] array) {
            return append(label, unroll(array));
        }

        @Override
        public <M extends Map<?, ?>> ToStringBuilder append(final String label, final M map) {
            return append(label, unroll(map));
        }

        @Override
        public ToStringBuilder setUnrollDepth(final int depth) {
            __unrollDepth = depth;
            return this;
        }

        @Override
        public ToStringBuilder setPrettyPrint(final boolean pretty) {
            __pertyPrint = pretty;
            return this;
        }

        @Override
        public ToStringBuilder setDisplayLoadLocation(final boolean display) {
            __displayLoadLocation = display;
            return this;
        }

        @Override
        public String prettyPrint(final String string) {
            if (null == string) {
                return NULL;
            }

            final StringBuilder builder_ = new StringBuilder();
            final StringTokenizer tokenizer_ = new StringTokenizer(string, "{,[]}", true);
            int indentation_ = 0;

            while (tokenizer_.hasMoreElements()) {
                final String token_ = String.class.cast(tokenizer_.nextElement()).trim();
                switch (token_) {
                    case "{":
                    case "[":
                        indentation_++;
                        builder_
                                .append(token_)
                                .append(NEW_LINE);
                        break;
                    case "]":
                    case "}":
                        indentation_--;
                        builder_
                                .append(NEW_LINE)
                                .append(indentation(indentation_))
                                .append(token_);
                        break;
                    case ",":
                        builder_
                                .append(token_)
                                .append(NEW_LINE);
                        break;
                    default:
                        builder_
                                .append(indentation(indentation_))
                                .append(token_);
                }
            }

            return builder_.toString();
        }

        @Override
        public String build() {
            final StringBuilder result_ = new StringBuilder();

            result_
                    .append(__subject.getClass().getSimpleName())
                    .append(NAME_HASH_DELIMITER)
                    .append(__subject.hashCode())
                    .append(STATE_START_DELIMITER);

            for (final String key_ : __state.keySet()) {
                result_
                        .append(key_)
                        .append(NAME_VALUE_SEPARATOR)
                        .append(__state.get(key_))
                        .append(NAME_VALUE_DELIMITER);
            }

            if (__displayLoadLocation) {
                result_
                        .append(BYTECODE_LOCATION_LABEL)
                        .append(NAME_VALUE_SEPARATOR)
                        .append(loadedFrom(__subject));
            }

            result_
                    .append(STATE_STOP_DELIMITER);

            if (__pertyPrint) {
                return prettyPrint(result_.toString());
            } else {
                return result_.toString();
            }
        }

        private String loadedFrom(final Object subject) {
            return subject
                    .getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toString();
        }

        private String unroll(final Collection<?> collection, final Integer depth) {
            final StringBuilder builder_ = new StringBuilder();
            if (null != collection) {
                builder_.append(COLLECTION_START_DELIMITER);
                int collectionIndex_ = 0;
                final Iterator<?> items_ = collection.iterator();
                while (items_.hasNext() && collectionIndex_++ < depth) {
                    builder_.append(wrap(items_.next()).toString());
                    if (collectionIndex_ > 0 && items_.hasNext()) {
                        builder_.append(NAME_VALUE_DELIMITER);
                    }
                }

                final int collectionSize_ = collection.size();
                if (depth < collectionSize_) {
                    final int omitted_ = collectionSize_ - (depth + 1);
                    builder_.append(OMITTED_START_DELIMITER)
                            .append(omitted_)
                            .append(OMITTED_STOP_DELIMITER)
                            .append(NAME_VALUE_DELIMITER)
                            .append(collection.toArray()[collectionSize_ - 1]);
                }
                builder_.append(COLLECTION_STOP_DELIMITER);
            } else {
                builder_.append(NULL);
            }

            return builder_.toString();
        }

        private String unroll(final Collection<?> collection) {
            return unroll(collection, __unrollDepth);
        }

        private String unroll(final Object[] array, final Integer depth) {
            final StringBuilder builder_ = new StringBuilder();
            if (null != array) {
                builder_.append(COLLECTION_START_DELIMITER);
                int collectionIndex_ = 0;
                final Iterator<?> items_ = Arrays.asList(array).iterator();
                while (items_.hasNext() && collectionIndex_++ < depth) {
                    builder_.append(wrap(items_.next()).toString());
                    if (collectionIndex_ > 0 && items_.hasNext()) {
                        builder_.append(NAME_VALUE_DELIMITER);
                    }
                }

                final int collectionSize_ = array.length;
                if (depth < collectionSize_) {
                    final int omitted_ = collectionSize_ - (depth + 1);
                    builder_.append(OMITTED_START_DELIMITER)
                            .append(omitted_)
                            .append(OMITTED_STOP_DELIMITER)
                            .append(NAME_VALUE_DELIMITER)
                            .append(array[collectionSize_ - 1]);
                }
                builder_.append(COLLECTION_STOP_DELIMITER);
            } else {
                builder_.append(NULL);
            }

            return builder_.toString();
        }

        private String unroll(final Object[] array) {
            return unroll(array, __unrollDepth);
        }

        private <K, V> String unroll(final Map<K, V> map, final Integer depth) {
            final StringBuilder builder_ = new StringBuilder();
            if (null != map) {
                builder_.append(COLLECTION_START_DELIMITER);
                int collectionIndex_ = 0;
                final Iterator<Map.Entry<K, V>> entries_ = map.entrySet().iterator();
                while (entries_.hasNext() && collectionIndex_++ < depth) {
                    Map.Entry<K, V> entry_ = entries_.next();
                    builder_.append(wrap(entry_.getKey()).toString())
                            .append(MAP_NAME_VALUE_SEPARATOR)
                            .append(wrap(entry_.getValue()).toString());
                    if (collectionIndex_ > 0 && entries_.hasNext()) {
                        builder_.append(NAME_VALUE_DELIMITER);
                    }
                }

                final int collectionSize_ = map.size();
                if (depth < collectionSize_) {
                    final int omitted_ = collectionSize_ - (depth + 1);
                    builder_.append(OMITTED_START_DELIMITER)
                            .append(omitted_)
                            .append(OMITTED_STOP_DELIMITER)
                            .append(NAME_VALUE_DELIMITER)
                            .append(map.keySet().toArray()[collectionSize_ - 1]);
                }
                builder_.append(COLLECTION_STOP_DELIMITER);
            } else {
                builder_.append(NULL);
            }

            return builder_.toString();
        }

        private String unroll(final Map<?, ?> map) {
            return unroll(map, __unrollDepth);
        }

        private Object wrap(final Object object) {
            Object result_;

            if (null == object) {
                result_ = NULL;
            } else {
                result_ = object;
            }

            return result_;
        }

        private String indentation(final int indentation) {
            final StringBuilder builder_ = new StringBuilder();

            for (int i_ = 0; i_ < indentation; i_++) {
                builder_.append(INDENTATION);
            }

            return builder_.toString();
        }
    }
}
