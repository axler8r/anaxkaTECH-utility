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
import java.util.StringTokenizer;


public final class ToString {
    private static final String INDENTATION = System.getProperty(
            "_4axka.util.lang.ToString.INDENTATION",
            "    ");
    private static final String NEW_LINE = System.getProperty("line.separator");

    public ToString() {}

    public static String unroll(final Collection<?> collection, final Integer depth) {
        final StringBuilder builder_ = new StringBuilder();
        if (null != collection) {
            builder_.append("[");
            int collectionIndex_ = 0;
            final Iterator<?> items_ = collection.iterator();
            while (items_.hasNext() && collectionIndex_++ < depth) {
                builder_.append(items_.next().toString());
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

    public static String unroll(final Collection<?> collection) {
        return unroll(collection, 10);
    }

    public static Object wrap(final Object object) {
        Object result_;

        if (null == object) {
            result_ = "null";
        } else {
            result_ = object;
        }

         return result_;
    }

    public static String prettyPrint(final String string) {
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

    private static String indentation(final int indentation) {
        final StringBuilder builder_ = new StringBuilder();

        for (int i_ = 0; i_ < indentation; i_++) {
            builder_.append(INDENTATION);
        }

        return builder_.toString();
    }
}
