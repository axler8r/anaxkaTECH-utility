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
package tech.anaxka.common.utility.builder;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import tech.anaxka.common.utility.functor.Builder;

import static java.util.Calendar.getInstance;

/**
 *
 * @author Axl Mattheus
 */
public class DateTimeBuilder implements Builder<Date> {

    private final Calendar __;

    private DateTimeBuilder() {
        __ = getInstance();
    }

    private DateTimeBuilder(Locale locale) {
        __ = getInstance(locale);
    }

    private DateTimeBuilder(TimeZone zone) {
        __ = getInstance(zone);
    }

    private DateTimeBuilder(TimeZone zone, Locale locale) {
        __ = getInstance(zone, locale);
    }

    /**
     * <p>dateTimeBuilder.</p>
     *
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public static final DateTimeBuilder dateTimeBuilder() {
        return new DateTimeBuilder();
    }
    
    /**
     * <p>dateTimeBuilder.</p>
     *
     * @param locale a {@link java.util.Locale} object.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public static final DateTimeBuilder dateTimeBuilder(final Locale locale) {
        return new DateTimeBuilder(locale);
    }
    
    /**
     * <p>dateTimeBuilder.</p>
     *
     * @param zone a {@link java.util.TimeZone} object.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public static final DateTimeBuilder dateTimeBuilder(final TimeZone zone) {
        return new DateTimeBuilder(zone);
    }
    
    /**
     * <p>dateTimeBuilder.</p>
     *
     * @param zone a {@link java.util.TimeZone} object.
     * @param locale a {@link java.util.Locale} object.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public static final DateTimeBuilder dateTimeBuilder(final TimeZone zone, final Locale locale) {
        return new DateTimeBuilder(zone, locale);
    }
    
    /**
     * <p>setCurrentDateTime.</p>
     *
     * @param currentDateTime a {@link java.util.Date} object.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder setCurrentDateTime(final Date currentDateTime) {
        __.setTime(currentDateTime);
        return this;
    }
    
    /**
     * <p>addYear.</p>
     *
     * @param year a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder addYear(final int year) {
        __.add(Calendar.YEAR, year);
        return this;
    }
    
    /**
     * <p>addMonth.</p>
     *
     * @param month a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder addMonth(final int month) {
        __.add(Calendar.MONTH, month);
        return this;
    }
    
    /**
     * <p>addDay.</p>
     *
     * @param day a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder addDay(final int day) {
        __.add(Calendar.DAY_OF_MONTH, day);
        return this;
    }
    
    /**
     * <p>addHour.</p>
     *
     * @param hour a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder addHour(final int hour) {
        __.add(Calendar.HOUR, hour);
        return this;
    }
    
    /**
     * <p>addMinute.</p>
     *
     * @param minute a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder addMinute(final int minute) {
        __.add(Calendar.MINUTE, minute);
        return this;
    }
    
    /**
     * <p>addSecond.</p>
     *
     * @param second a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder addSecond(final int second) {
        __.add(Calendar.SECOND, second);
        return this;
    }
    
    /**
     * <p>addSecondFraction.</p>
     *
     * @param fraction a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     * @since 0.08.000.0000
     */
    public DateTimeBuilder addSecondFraction(final int fraction) {
        __.add(Calendar.MILLISECOND, fraction);
        return this;
    }

    /**
     * <p>setYear.</p>
     *
     * @param year a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public DateTimeBuilder setYear(final int year) {
        __.set(Calendar.YEAR, year);
        return this;
    }

    /**
     * <p>setMonth.</p>
     *
     * @param month a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public DateTimeBuilder setMonth(final int month) {
        __.set(Calendar.MONTH, month);
        return this;
    }

    /**
     * <p>setDay.</p>
     *
     * @param day a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public DateTimeBuilder setDay(final int day) {
        __.set(Calendar.DATE, day);
        return this;
    }

    /**
     * <p>setHour.</p>
     *
     * @param hour a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public DateTimeBuilder setHour(final int hour) {
        __.set(Calendar.HOUR, hour);
        return this;
    }

    /**
     * <p>setMinute.</p>
     *
     * @param minute a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public DateTimeBuilder setMinute(final int minute) {
        __.set(Calendar.MINUTE, minute);
        return this;
    }

    /**
     * <p>setSecond.</p>
     *
     * @param second a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public DateTimeBuilder setSecond(final int second) {
        __.set(Calendar.SECOND, second);
        return this;
    }

    /**
     * <p>setSecondFraction.</p>
     *
     * @param fraction a int.
     * @return a {@link tech.anaxka.common.utility.builder.DateTimeBuilder} object.
     */
    public DateTimeBuilder setSecondFraction(final int fraction) {
        __.set(Calendar.MILLISECOND, fraction);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Date build() {
        return __.getTime();
    }
}
