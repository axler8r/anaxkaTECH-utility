package tech.anaxka.common.utility.builder;

import tech.anaxka.common.utility.functor.Builder;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.util.Calendar.getInstance;

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

    public static final DateTimeBuilder dateTimeBuilder() {
        return new DateTimeBuilder();
    }
    
    public static final DateTimeBuilder dateTimeBuilder(final Locale locale) {
        return new DateTimeBuilder(locale);
    }
    
    public static final DateTimeBuilder dateTimeBuilder(final TimeZone zone) {
        return new DateTimeBuilder(zone);
    }
    
    public static final DateTimeBuilder dateTimeBuilder(final TimeZone zone, final Locale locale) {
        return new DateTimeBuilder(zone, locale);
    }

    public DateTimeBuilder setYear(final int year) {
        __.set(Calendar.YEAR, year);
        return this;
    }

    public DateTimeBuilder setMonth(final int month) {
        __.set(Calendar.MONTH, month);
        return this;
    }

    public DateTimeBuilder setDay(final int day) {
        __.set(Calendar.DATE, day);
        return this;
    }

    public DateTimeBuilder setHour(final int hour) {
        __.set(Calendar.HOUR, hour);
        return this;
    }

    public DateTimeBuilder setMinute(final int minute) {
        __.set(Calendar.MINUTE, minute);
        return this;
    }

    public DateTimeBuilder setSecond(final int second) {
        __.set(Calendar.SECOND, second);
        return this;
    }

    public DateTimeBuilder setSecondFraction(final int fraction) {
        __.set(Calendar.MILLISECOND, fraction);
        return this;
    }

    @Override
    public Date build() {
        return __.getTime();
    }
}
