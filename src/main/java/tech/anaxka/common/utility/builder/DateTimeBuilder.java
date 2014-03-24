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
import tech.anaxka.common.utility.functor.FunctorException;

import static java.util.Calendar.getInstance;


/**
 * A {@linkplain Builder builder} for {@linkplain Date date and time} objects.
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 * @see Builder
 * @see Date
 */
public final class DateTimeBuilder
        implements Builder<Date, FunctorException> {

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
     * Creates a {@linkplain Builder builder} for {@link Date date}s.
     *
     * @return A default {@link DateTimeBuilder builder} for {@linkplain Date date} objects.
     */
    public static final DateTimeBuilder dateTimeBuilder() {
        return new DateTimeBuilder();
    }

    /**
     * Creates a {@link DateTimeBuilder#dateTimeBuilder() builder} using the specified
     * {@link Locale locale}.
     *
     * @param locale the {@link Locale locale} to use for construction of the {@link Date date}.
     *
     * @return A {@link #dateTimeBuilder() builder} for {@link Date date} objects.
     */
    public static final DateTimeBuilder dateTimeBuilder(final Locale locale) {
        return new DateTimeBuilder(locale);
    }

    /**
     * Creates a {@link DateTimeBuilder#dateTimeBuilder() builder} using the specified
     * {@link TimeZone time zone}.
     *
     * @param zone the {@link TimeZone zone} to use for the construction of the {@link Date date}.
     *
     * @return A {@link #dateTimeBuilder() builder} for {@link Date date} objects.
     */
    public static final DateTimeBuilder dateTimeBuilder(final TimeZone zone) {
        return new DateTimeBuilder(zone);
    }

    /**
     * Creates a {@link DateTimeBuilder#dateTimeBuilder() builder} using the specified
     * {@link TimeZone time zone} and {@link Locale locale}.
     *
     * @param zone   the {@link TimeZone zone} to use for the construction of the {@link Date date}.
     * @param locale the {@link Locale locale} to use for construction of the {@link Date date}.
     *
     * @return A {@link #dateTimeBuilder() builder} for {@link Date date} objects.
     */
    public static final DateTimeBuilder dateTimeBuilder(final TimeZone zone, final Locale locale) {
        return new DateTimeBuilder(zone, locale);
    }

    /**
     * Initialize the {@link DateTimeBuilder builder} to coincide with the specified
     * {@link Date date}.
     *
     * @param instant the instant in {@link Date time} to initialize the
     *                {@linkplain DateTimeBuilder builder} to.
     *
     * @return A {@link DateTimeBuilder builder} initialized to a specific instant in
     *         {@linkplain Date time}.
     */
    public DateTimeBuilder setDateTime(final Date instant) {
        if (instant == null) {
            throw new IllegalArgumentException("");
        }
        __.setTime(instant);

        return this;
    }

    /**
     * Add the specified number of year(s) to the {@link Date date} under construction.
     *
     * @param years a positive or negative number to add to the years.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder addYears(final int years) {
        __.add(Calendar.YEAR, years);
        return this;
    }

    /**
     * Add the specified number of month(s) to the {@link Date date} under construction.
     *
     * @param months a positive or negative number to add to the months.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder addMonth(final int months) {
        __.add(Calendar.MONTH, months);
        return this;
    }

    /**
     * Add the specified number of day(s) to the {@link Date date} under construction.
     *
     * @param days a positive or negative number to add to the days.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder addDays(final int days) {
        __.add(Calendar.DAY_OF_MONTH, days);
        return this;
    }

    /**
     * Add the specified number of hour(s) to the {@link Date date} under construction.
     *
     * @param hours a positive or negative number to add to the hours.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder addHours(final int hours) {
        __.add(Calendar.HOUR, hours);
        return this;
    }

    /**
     * Add the specified number of minute(s) to the {@link Date date} under construction.
     *
     * @param minutes a positive or negative number to add to the minutes.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder addMinutes(final int minutes) {
        __.add(Calendar.MINUTE, minutes);
        return this;
    }

    /**
     * Add the specified number of second(s) to the {@link Date date} under construction.
     *
     * @param seconds a positive or negative number to add to the seconds.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder addSeconds(final int seconds) {
        __.add(Calendar.SECOND, seconds);
        return this;
    }

    /**
     * Add the specified number of fraction(s) of a second to the {@link Date date} under
     * construction.
     *
     * @param millis a positive or negative number to add to the milli seconds.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder addMilliseconds(final int millis) {
        __.add(Calendar.MILLISECOND, millis);
        return this;
    }

    /**
     * Set the year for the {@link Date date} under construction.
     * 
     * @param year the year of the {@link Date date} under construction.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder setYear(final int year) {
        __.set(Calendar.YEAR, year);
        return this;
    }

    /**
     * Set the month for the {@link Date date} under construction.
     * 
     * @param month the year of the {@link Date date} under construction.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder setMonth(final int month) {
        __.set(Calendar.MONTH, month);
        return this;
    }

    /**
     * Set the day for the {@link Date date} under construction.
     * 
     * @param day the year of the {@link Date date} under construction.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder setDay(final int day) {
        __.set(Calendar.DATE, day);
        return this;
    }

    /**
     * Set the hour for the {@link Date date} under construction.
     * 
     * @param hour the year of the {@link Date date} under construction.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder setHour(final int hour) {
        __.set(Calendar.HOUR, hour);
        return this;
    }

    /**
     * Set the minute for the {@link Date date} under construction.
     * 
     * @param minute the year of the {@link Date date} under construction.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder setMinute(final int minute) {
        __.set(Calendar.MINUTE, minute);
        return this;
    }

    /**
     * Set the second for the {@link Date date} under construction.
     * 
     * @param second the year of the {@link Date date} under construction.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder setSecond(final int second) {
        __.set(Calendar.SECOND, second);
        return this;
    }

    /**
     * Set the fraction of a second for the {@link Date date} under construction.
     * 
     * @param fraction the year of the {@link Date date} under construction.
     *
     * @return A {@link DateTimeBuilder builder} to continue the construction of an instant in
     *         {@link Date time}.
     */
    public DateTimeBuilder setSecondFraction(final int fraction) {
        __.set(Calendar.MILLISECOND, fraction);
        return this;
    }

    public DateTimeBuilder reset() {
        __.clear();
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Date build() {
        return __.getTime();
    }
}
