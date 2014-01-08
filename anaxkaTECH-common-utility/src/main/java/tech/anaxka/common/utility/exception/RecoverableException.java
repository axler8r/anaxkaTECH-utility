// $Id$

/*
 * \u00A9 2012, 4axka (Pty) Ltd.  All rights reserved.
 *
 * The content of RecoverableException.java is strictly CONFIDENTIAL.
 *
 * It may not be viewed as a whole, or in part by any unauthorised party unless
 * explicit permission has been granted by an authorised 4axka representative.
 *
 * It may not be reproduced as a whole, or in part by any means unless explicit
 * permission has been granted by an authorised 4axka representative.
 */
package tech.anaxka.common.utility.exception;


public class RecoverableException extends RuntimeException {
    private static final long serialVersionUID = -7372462401855099104L;

    public RecoverableException() {}

    public RecoverableException(String message) {
        super(message);
    }

    public RecoverableException(Throwable cause) {
        super(cause);
    }

    public RecoverableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecoverableException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
