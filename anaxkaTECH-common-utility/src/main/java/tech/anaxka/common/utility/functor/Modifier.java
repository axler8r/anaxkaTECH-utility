// $Id$

/*
 * \u00A9 2012, 4axka (Pty) Ltd.  All rights reserved.
 *
 * The content of Modifier.java is strictly CONFIDENTIAL.
 *
 * It may not be viewed as a whole, or in part by any unauthorised party unless
 * explicit permission has been granted by an authorised 4axka representative.
 *
 * It may not be reproduced as a whole, or in part by any means unless explicit
 * permission has been granted by an authorised 4axka representative.
 */
package tech.anaxka.common.utility.functor;


import java.io.Serializable;


public interface Modifier<
                R extends Serializable,
                P extends Serializable,
                T extends Throwable> extends
        Serializable {
    R modify(final P parameter) throws T;
}
