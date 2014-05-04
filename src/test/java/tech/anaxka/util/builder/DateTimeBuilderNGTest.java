/*
 * Copyright (c) 2014, 4axka (Pty) Ltd
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package tech.anaxka.util.builder;


/**
 *
 * @author <a href="mailto:info@anaxka.tech?Subject=RFI">anaxkaTECH (Pty) Ltd</a>
 */
public class DateTimeBuilderNGTest {
//
//    public DateTimeBuilderNGTest() {
//    }
//
//    /**
//     * Test of setDateTime method, of class DateTimeBuilder.
//     */
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void testSetDateTimeNull() {
//        final Date result_ = dateTimeBuilder().setDateTime(null).build();
//        assertNull(result_);
//    }
//
//    /**
//     * Test of addYears method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testAddYearsMaximum() {
//        final int maxYear_ = 292_278_994;
//        
//        final Date latest_ = dateTimeBuilder().reset().setYear(maxYear_).build();
//
//        final int maxYearOverflow_ = maxYear_ + 1;
//        final Date overflow_ = dateTimeBuilder().reset().setYear(maxYearOverflow_).build();
//        assertNotEquals(new GregorianCalendar(maxYearOverflow_, 1, 1).get(Calendar.YEAR), maxYearOverflow_);
//    }
//
//    /**
//     * Test of addMonth method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testAddMonth() {
//        System.out.println("addMonth");
//        int months = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.addMonth(months);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addDays method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testAddDays() {
//        System.out.println("addDays");
//        int days = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.addDays(days);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addHours method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testAddHours() {
//        System.out.println("addHours");
//        int hours = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.addHours(hours);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addMinutes method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testAddMinutes() {
//        System.out.println("addMinutes");
//        int minutes = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.addMinutes(minutes);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addSeconds method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testAddSeconds() {
//        System.out.println("addSeconds");
//        int seconds = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.addSeconds(seconds);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addMilliseconds method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testAddMilliseconds() {
//        System.out.println("addSecondFraction");
//        int fractions = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.addMilliseconds(fractions);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setYear method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testSetYear() {
//        System.out.println("setYear");
//        int year = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.setYear(year);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMonth method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testSetMonth() {
//        System.out.println("setMonth");
//        int month = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.setMonth(month);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDay method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testSetDay() {
//        System.out.println("setDay");
//        int day = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.setDay(day);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setHour method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testSetHour() {
//        System.out.println("setHour");
//        int hour = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.setHour(hour);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMinute method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testSetMinute() {
//        System.out.println("setMinute");
//        int minute = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.setMinute(minute);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSecond method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testSetSecond() {
//        System.out.println("setSecond");
//        int second = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.setSecond(second);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSecondFraction method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testSetSecondFraction() {
//        System.out.println("setSecondFraction");
//        int fraction = 0;
//        DateTimeBuilder instance = null;
//        DateTimeBuilder expResult = null;
//        DateTimeBuilder result = instance.setSecondFraction(fraction);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of build method, of class DateTimeBuilder.
//     */
//    @Test
//    public void testBuild() {
//        System.out.println("build");
//        DateTimeBuilder instance = null;
//        Date expResult = null;
//        Date result = instance.build();
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
}
