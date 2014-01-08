// $Id$

/*
 * \u00A9 2012, 4axka (Pty) Ltd.  All rights reserved.
 *
 * The content of DataModificationException.java is strictly CONFIDENTIAL.
 *
 * It may not be viewed as a whole, or in part by any unauthorised party unless
 * explicit permission has been granted by an authorised 4axka representative.
 *
 * It may not be reproduced as a whole, or in part by any means unless explicit
 * permission has been granted by an authorised 4axka representative.
 */
package tech.anaxka.common.utility.data.exception;


public class DataModificationException extends Exception {
    /**
     * Determines if a de-serialised file is compatible with this class.
     * <p>
     * Maintainers <strong>MUST</strong> change this value if and only if the new version of
     * this class is not compatible with the previous version. It is not necessary to include
     * in first version of the class, but included here as a reminder of its importance.
     * 
     * @see <a href="http://bit.ly/aDUV5">Java Object Serialization Specification</a>.
     */
    private static final long serialVersionUID = -2290808652313826699L;

    public DataModificationException() {
        super();
    }

    public DataModificationException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataModificationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataModificationException(final String message) {
        super(message);
    }

    public DataModificationException(final Throwable cause) {
        super(cause);
    }
}
