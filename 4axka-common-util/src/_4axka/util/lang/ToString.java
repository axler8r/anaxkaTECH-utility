// $Id$

/*
 * \u00A9 2012, 4axka (Pty) Ltd.  All rights reserved.
 *
 * The content of ToString.java is strictly CONFIDENTIAL.
 *
 * It may not be viewed as a whole, or in part by any unauthorised party unless
 * explicit permission has been granted by an authorised 4axka representative.
 *
 * It may not be reproduced as a whole, or in part by any means unless explicit
 * permission has been granted by an authorised 4axka representative.
 */
package _4axka.util.lang;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public final class ToString {

    public ToString() {
    }

    public static final ToStringBuilder toStringBuilder(final Object subject) {
        return new ToStringBuilderImpl(subject);
    }

    public static interface ToStringBuilder {

        <S> ToStringBuilder append(final String label, final S subject);

        <C extends Collection<?>> ToStringBuilder append(final String label, final C collection);

        ToStringBuilder setUnrollDepth(final int depth);
        
        String prettyPrint(final String string);

        ToStringBuilder setPrettyPrint(final boolean pretty);

        ToStringBuilder setDisplayLoadLocation(final boolean display);

        String string();
    }

    private static final class ToStringBuilderImpl implements ToStringBuilder {
        private static final String NAME_HASH_DELIMITER = System.getProperty(
                "_4axka.util.lang.ToStringBuilder.NAME_HASH_DELIMITER",
                "@");
        private static final String NAME_VALUE_DELIMITER = System.getProperty(
                "_4axka.util.lang.ToStringBuilder.NAME_VALUE_DELIMITER",
                ", ");
        private static final String NAME_VALUE_SEPARATOR = System.getProperty(
                "_4axka.util.lang.ToStringBuilder.NAME_VALUE_SEPARATOR",
                "=");
        private static final String STATE_STOP_DELIMITER = System.getProperty(
                "_4axka.util.lang.ToStringBuilder.STATE_STOP_DELIMITER",
                "}");
        private static final String STATE_START_DELIMITER = System.getProperty(
                "_4axka.util.lang.ToStringBuilder.STATE_START_DELIMITER",
                "{");
        private static final String BYTECODE_LOCATION_LABEL = System.getProperty(
                "_4axka.util.lang.ToStringBuilder.BYTECODE__LOCATION_LABEL",
                "Bytecode Location");
        private static final String INDENTATION = System.getProperty(
                "_4axka.util.lang.ToStringBuilder.INDENTATION",
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
            __state.put(label, subject == null ? "null" : subject.toString());
            return this;
        }

        @Override
        public <C extends Collection<?>> ToStringBuilder append(
                final String label,
                final C collection) {
            return append(label, unroll(collection));
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
        public String string() {
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
                builder_.append("[");
                int collectionIndex_ = 0;
                final Iterator<?> items_ = collection.iterator();
                while (items_.hasNext() && collectionIndex_++ < depth) {
                    builder_.append(wrap(items_.next()).toString());
                    if (collectionIndex_ > 0 && items_.hasNext()) {
                        builder_.append(", ");
                    }
                }

                final int collectionSize_ = collection.size();
                if (depth < collectionSize_) {
                    final int omitted_ = collectionSize_ - (depth + 1);
                    builder_.append("... ").append(omitted_).append(" omitted ...")
                            .append(", ")
                            .append(collection.toArray()[collectionSize_ - 1]);
                }
                builder_.append("]");
            } else {
                builder_.append("null");
            }

            return builder_.toString();
        }

        private String unroll(final Collection<?> collection) {
            return unroll(collection, __unrollDepth);
        }

        private Object wrap(final Object object) {
            Object result_;

            if (null == object) {
                result_ = "null";
            } else {
                result_ = object;
            }

            return result_;
        }

        @Override
        public String prettyPrint(final String string) {
            if (null == string) {
                return "null";
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

        private String indentation(final int indentation) {
            final StringBuilder builder_ = new StringBuilder();

            for (int i_ = 0; i_ < indentation; i_++) {
                builder_.append(INDENTATION);
            }

            return builder_.toString();
        }
    }
}
