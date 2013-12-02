/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package _4axka.util.lang;

/**
 *
 * @author adolf.mattheus
 */
public class Equals {
    public static final <T> boolean isDifferentTypes(final T lhs, final T rhs) {
        return !(rhs.getClass().isAssignableFrom(lhs.getClass()));
    }
    
    public static final <T> boolean isEqual(final T lhs, final T rhs) {
        return (lhs != null) && (rhs != null) && lhs.equals(rhs);
    }
    
    public static final boolean isEqual(final byte lhs, final byte rhs) {
        return lhs == rhs;
    }

    public static final boolean isEqual(final short lhs, final short rhs) {
        return lhs == rhs;
    }

    public static final boolean isEqual(final int lhs, final int rhs) {
        return lhs == rhs;
    }

    public static final boolean isEqual(final long lhs, final long rhs) {
        return lhs == rhs;
    }

    public static final boolean isEqual(final float lhs, final float rhs) {
        return lhs == rhs;
    }

    public static final boolean isEqual(final double lhs, final double rhs) {
        return lhs == rhs;
    }

    public static final boolean isEqual(final boolean lhs, final boolean rhs) {
        return lhs == rhs;
    }

    public static final boolean isEqual(final char lhs, final char rhs) {
        return lhs == rhs;
    }
}
