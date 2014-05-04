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
package tech.anaxka.common.utility.exception;


/**
 * An exception that cannot be recovered from.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 */
public class FatalException
        extends Exception {

    private static final long serialVersionUID = -7494141881121987469L;

    /**
     *
     */
    public FatalException() {
    }

    /**
     * @param message            see {@link Exception#Exception(String message, Throwable cause,
     *                           boolean enableSuppression, boolean writableStackTrace)}.
     * @param cause              see {@link Exception#Exception(String message, Throwable cause,
     *                           boolean enableSuppression, boolean writableStackTrace)}.
     * @param enableSuppression  see {@link Exception#Exception(String message, Throwable cause,
     *                           boolean enableSuppression, boolean writableStackTrace)}.
     * @param writableStackTrace see {@link Exception#Exception(String message, Throwable cause,
     *                           boolean enableSuppression, boolean writableStackTrace)}.
     */
    public FatalException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message see {@link Exception#Exception(String message, Throwable cause)}.
     * @param cause   see {@link Exception#Exception(String message, Throwable cause)}.
     */
    public FatalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message see {@link Exception#Exception(String message)}.
     */
    public FatalException(final String message) {
        super(message);
    }

    /**
     * @param cause see {@link Exception#Exception(Throwable cause)}.
     */
    public FatalException(Throwable cause) {
        super(cause);
    }
}
