/*
 * Copyright (c) 2014, Adolf.Mattheus
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
package tech.anaxka.common.utility.lang;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static tech.anaxka.common.utility.builder.DateTimeBuilder.dateTimeBuilder;
import static tech.anaxka.common.utility.builder.MapBuilder.mapBuilder;
import static tech.anaxka.common.utility.lang.CompareTo.EQUAL;
import static tech.anaxka.common.utility.lang.CompareTo.GREATER_THAN;
import static tech.anaxka.common.utility.lang.CompareTo.compareToBuilder;
import static tech.anaxka.common.utility.lang.CompareTo.isComparable;

/**
 *
 * @author Adolf.Mattheus
 */
public class CompareToTest {

    public CompareToTest() {
    }

    @DataProvider
    Object[][] reflexiveTestData() {
        final Random random_ = new Random();

        final TestData reflexiveOldSchoolTestData_ = new OldSchoolCompareToTestData(
                mapBuilder(new HashMap<String, Date>())
                .append("Yesterday",
                        dateTimeBuilder()
                        .setCurrentDateTime(new Date())
                        .addDay(-1)
                        .build())
                .build(),
                dateTimeBuilder()
                .build(),
                new BigDecimal("258963147.369852147"),
                new UserComparable[]{
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable()
                },
                mapBuilder(new HashMap<Integer, OldSchoolUserComparable>())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .build(),
                UUID.randomUUID().toString(),
                random_.nextInt(),
                random_.nextFloat());

        final TestData reflexiveHipTestData_ = new HipCompareToTestData(
                mapBuilder(new HashMap<String, Date>())
                .append(
                        "Yesterday",
                        dateTimeBuilder()
                        .setCurrentDateTime(new Date())
                        .addDay(-1)
                        .build())
                .build(),
                dateTimeBuilder()
                .build(),
                new BigDecimal("258963147.369852147"),
                new UserComparable[]{
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable(),
                    new OldSchoolUserComparable()
                },
                mapBuilder(new HashMap<Integer, OldSchoolUserComparable>())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .build(),
                UUID.randomUUID().toString(),
                random_.nextInt(),
                random_.nextFloat());

        return new Object[][]{
            {reflexiveOldSchoolTestData_},
            {reflexiveHipTestData_}
        };
    }

    /**
     *
     * @param data
     */
    @Test(dataProvider = "reflexiveTestData")
    public void testRefexivity(final TestData data) {
        assertTrue(data.compareTo(data) == 0);
    }

    @DataProvider
    Object[][] symmetricTestData() {
        final Random random_ = new Random();
        final Date NOW = dateTimeBuilder().build();
        final Date YESTERDAY = dateTimeBuilder().setCurrentDateTime(NOW).addDay(-1).build();
        final Date TOMORROW = dateTimeBuilder().setCurrentDateTime(NOW).addDay(+1).build();
        final String STRING = UUID.randomUUID().toString();
        final int INT = random_.nextInt();
        final float FLOAT = random_.nextFloat();
        final BigDecimal BIG_DECIMAL = new BigDecimal("258963147.369852147456987123");
        final UserComparable[] USER_COMPARABLE_ARRAY = new UserComparable[]{
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable()
        };
        final Map INT_USER_COMPARABLE_MAP = mapBuilder(new HashMap<Integer, OldSchoolUserComparable>())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .build();
        final Map STRING_DATE_MAP = mapBuilder(new HashMap<String, Date>())
                .append("Yesterday", YESTERDAY)
                .append("Today", NOW)
                .append("Tomorrow", TOMORROW)
                .build();

        final TestData sostd0_ = new OldSchoolCompareToTestData(
                STRING_DATE_MAP,
                NOW,
                BIG_DECIMAL,
                USER_COMPARABLE_ARRAY,
                INT_USER_COMPARABLE_MAP,
                STRING,
                INT,
                FLOAT);

        final TestData sostd1_ = new OldSchoolCompareToTestData(
                STRING_DATE_MAP,
                NOW,
                BIG_DECIMAL,
                USER_COMPARABLE_ARRAY,
                INT_USER_COMPARABLE_MAP,
                STRING,
                INT,
                FLOAT);

        final TestData shtd0_ = new HipCompareToTestData(
                STRING_DATE_MAP,
                NOW,
                BIG_DECIMAL,
                USER_COMPARABLE_ARRAY,
                INT_USER_COMPARABLE_MAP,
                STRING,
                INT,
                FLOAT);

        final TestData shtd1_ = new HipCompareToTestData(
                STRING_DATE_MAP,
                NOW,
                BIG_DECIMAL,
                USER_COMPARABLE_ARRAY,
                INT_USER_COMPARABLE_MAP,
                STRING,
                INT,
                FLOAT);

        return new Object[][]{
            {sostd0_, sostd1_},
            {shtd0_, shtd1_}
        };
    }

    /**
     *
     * @param lhs
     * @param rhs
     */
    @Test(dataProvider = "symmetricTestData")
    public void testSymmetry(final TestData lhs, final TestData rhs) {
        assertTrue(lhs.compareTo(rhs) == rhs.compareTo(lhs));
    }

    @DataProvider
    Object[][] transitiveTestData() {
        final Random random_ = new Random();
        final Date NOW = dateTimeBuilder().build();
        final Date YESTERDAY = dateTimeBuilder().setCurrentDateTime(NOW).addDay(-1).build();
        final Date TOMORROW = dateTimeBuilder().setCurrentDateTime(NOW).addDay(+1).build();
        final String STRING = UUID.randomUUID().toString();
        final int INT = random_.nextInt();
        final float FLOAT = random_.nextFloat();
        final BigDecimal BIG_DECIMAL = new BigDecimal("258963147.369852147456987123");
        final UserComparable[] USER_COMPARABLE_ARRAY = new UserComparable[]{
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable(),
            new OldSchoolUserComparable()
        };
        final Map INT_USER_COMPARABLE_MAP = mapBuilder(new HashMap<Integer, OldSchoolUserComparable>())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .append(random_.nextInt(), new OldSchoolUserComparable())
                .build();
        final Map STRING_DATE_MAP = mapBuilder(new HashMap<String, Date>())
                .append("Yesterday", YESTERDAY)
                .append("Today", NOW)
                .append("Tomorrow", TOMORROW)
                .build();

        final TestData td0_ = new HipCompareToTestData(
                STRING_DATE_MAP,
                NOW,
                BIG_DECIMAL,
                USER_COMPARABLE_ARRAY,
                INT_USER_COMPARABLE_MAP,
                STRING,
                INT,
                FLOAT);

        final TestData td1_ = new HipCompareToTestData(
                STRING_DATE_MAP,
                NOW,
                BIG_DECIMAL,
                USER_COMPARABLE_ARRAY,
                INT_USER_COMPARABLE_MAP,
                STRING,
                INT,
                FLOAT);

        final TestData td2_ = new HipCompareToTestData(
                STRING_DATE_MAP,
                NOW,
                BIG_DECIMAL,
                USER_COMPARABLE_ARRAY,
                INT_USER_COMPARABLE_MAP,
                STRING,
                INT,
                FLOAT);

        return new Object[][]{
            {td0_, td1_, td2_}
        };
    }

    @Test(dataProvider = "transitiveTestData")
    public void testTransitivity(final TestData a, final TestData b, final TestData c) {
        final int ab_ = a.compareTo(b);
        final int bc_ = b.compareTo(c);
        final int ac_ = a.compareTo(c);
        assertTrue((ab_ == bc_) && (ab_ == ac_));
    }

    @Test(dataProvider = "reflexiveTestData")
    public void testConsistency(final TestData data) {
        assertTrue(data.compareTo(null) > 0);
    }

    @Test
    public void testNullComparison() {
        assertTrue(true);
    }

    public abstract static class TestData implements Comparable<TestData> {

        private final Map<String, Date> __sdMap;
        private final Date __date;
        private final BigDecimal __bd;
        private final UserComparable[] __cis;
        private final Map<Integer, UserComparable> __icMap;
        private final String __string;
        private final int __int;
        private final float __float;

        @SuppressWarnings("RedundantStringConstructorCall")
        public TestData(
                final Map<String, Date> p0,
                final Date p1,
                final BigDecimal p2,
                final UserComparable[] p3,
                final Map<Integer, UserComparable> p4,
                final String p5,
                final int p6,
                final float p7) {
            __sdMap = new HashMap<>(p0);
            __date = new Date(p1.getTime());
            __bd = new BigDecimal(p2.toString());
            __cis = Arrays.copyOf(p3, p3.length);
            __icMap = new HashMap<>(p4);
            __string = new String(p5);
            __int = p6;
            __float = p7;
        }

        public Map<String, Date> getStringDateMap() {
            return __sdMap;
        }

        public Date getDate() {
            return __date;
        }

        public BigDecimal getBigDecimal() {
            return __bd;
        }

        public UserComparable[] getComparableElementArray() {
            return __cis;
        }

        public Map<Integer, UserComparable> getIcMap() {
            return __icMap;
        }

        public String getString() {
            return __string;
        }

        public int getInt() {
            return __int;
        }

        public float getFloat() {
            return __float;
        }
    }

    public abstract static class UserComparable implements Comparable<UserComparable> {

        private final UUID __key = UUID.randomUUID();

        public UserComparable() {
        }

        public UUID getKey() {
            return __key;
        }
    }

    private final static class OldSchoolCompareToTestData extends TestData implements Comparable<TestData> {

        OldSchoolCompareToTestData(
                Map<String, Date> p0,
                Date p1,
                BigDecimal p2,
                UserComparable[] p3,
                Map<Integer, UserComparable> p4,
                String p5,
                int p6,
                float p7) {
            super(p0, p1, p2, p3, p4, p5, p6, p7);
        }

        @Override
        public int compareTo(final TestData that) {
            int result_ = 0;

            if (this == that) {
                return result_;
            }
            if (that == null) {
                return 1;
            }

            if (null == getStringDateMap()) {
                if (null != that.getStringDateMap()) {
                    result_ = -1;
                }
            } else if (null != that.getStringDateMap()) {
                if (getStringDateMap().size() == that.getStringDateMap().size()) {
                    final Iterator<String> lhski_ = getStringDateMap().keySet().iterator();
                    final Iterator<String> rhski_ = that.getStringDateMap().keySet().iterator();
                    while (lhski_.hasNext() && result_ == 0) {
                        final String lhsk_ = lhski_.next();
                        final String rhsk_ = rhski_.next();
                        result_ = lhsk_.compareTo(rhsk_);
                        if (result_ == 0) {
                            result_ = getStringDateMap().get(lhsk_).compareTo(that.getStringDateMap().get(rhsk_));
                        }
                    }

                    return result_;
                } else {
                    Iterator<String> ski_;
                    Iterator<String> lki_;
                    if (getStringDateMap().size() < that.getStringDateMap().size()) {
                        ski_ = getStringDateMap().keySet().iterator();
                        lki_ = that.getStringDateMap().keySet().iterator();
                    } else {
                        ski_ = getStringDateMap().keySet().iterator();
                        lki_ = that.getStringDateMap().keySet().iterator();
                    }
                    while (ski_.hasNext() && result_ == 0) {
                        final String lhsk_ = ski_.next();
                        final String rhsk_ = lki_.next();
                        result_ = lhsk_.compareTo(rhsk_);
                        if (result_ == 0) {
                            result_ = getStringDateMap().get(lhsk_).compareTo(getStringDateMap().get(rhsk_));
                        }
                    }
                }
            }
            if (result_ != 0) {
                return result_;
            }

            if (null == getDate()) {
                if (null != that.getDate()) {
                    result_ = -1;
                }
            } else if (null != that.getDate()) {
                result_ = getDate().compareTo(that.getDate());
            }
            if (result_ != 0) {
                return result_;
            }

            if (null == getBigDecimal()) {
                if (null != that.getBigDecimal()) {
                    result_ = -1;
                }
            } else if (null != that.getBigDecimal()) {
                result_ = getBigDecimal().compareTo(that.getBigDecimal());
            }
            if (result_ != 0) {
                return result_;
            }

            if (null == getComparableElementArray()) {
                if (null != that.getComparableElementArray()) {
                    result_ = -1;
                }
            } else if (null != that.getComparableElementArray()) {
                if (getComparableElementArray().length < that.getComparableElementArray().length) {
                    for (int i_ = 0; i_ <= getComparableElementArray().length; i_++) {
                        final UserComparable lhuc_ = getComparableElementArray()[i_];
                        final UserComparable rhuc_ = that.getComparableElementArray()[i_];
                        if (null == lhuc_) {
                            if (null != rhuc_) {
                                result_ = -1;
                            }
                        } else if (null != rhuc_) {
                            result_ = lhuc_.compareTo(rhuc_);
                        }
                        if (result_ != 0) {
                            return result_;
                        }
                    }
                } else {
                    for (int i_ = 0; i_ < getComparableElementArray().length; i_++) {
                        final UserComparable lhuc_ = that.getComparableElementArray()[i_];
                        final UserComparable rhuc_ = getComparableElementArray()[i_];
                        if (null == lhuc_) {
                            if (null != rhuc_) {
                                result_ = -1;
                            }
                        } else if (null != rhuc_) {
                            result_ = lhuc_.compareTo(rhuc_);
                        }
                        if (result_ != 0) {
                            return result_;
                        }
                    }
                }
            }
            if (result_ != 0) {
                return result_;
            }

            if (null == getIcMap()) {
                if (null != that.getIcMap()) {
                    result_ = -1;
                }
            } else if (null != that.getIcMap()) {
                if (getIcMap().size() == that.getIcMap().size()) {
                    final Iterator<Integer> lhski_ = getIcMap().keySet().iterator();
                    final Iterator<Integer> rhski_ = that.getIcMap().keySet().iterator();
                    while (lhski_.hasNext() && result_ == 0) {
                        final Integer lhsk_ = lhski_.next();
                        final Integer rhsk_ = rhski_.next();
                        result_ = lhsk_.compareTo(rhsk_);
                        if (result_ == 0) {
                            result_ = getIcMap().get(lhsk_).compareTo(that.getIcMap().get(rhsk_));
                        }
                    }

                    return result_;
                } else {
                    Iterator<Integer> ski_;
                    Iterator<Integer> lki_;
                    if (getIcMap().size() < that.getIcMap().size()) {
                        ski_ = getIcMap().keySet().iterator();
                        lki_ = that.getIcMap().keySet().iterator();
                    } else {
                        ski_ = getIcMap().keySet().iterator();
                        lki_ = that.getIcMap().keySet().iterator();
                    }
                    while (ski_.hasNext() && result_ == 0) {
                        final Integer lhsk_ = ski_.next();
                        final Integer rhsk_ = lki_.next();
                        result_ = lhsk_.compareTo(rhsk_);
                        if (result_ == 0) {
                            result_ = getIcMap().get(lhsk_).compareTo(getIcMap().get(rhsk_));
                        }
                    }
                }
            }
            if (result_ != 0) {
                return result_;
            }

            if (null == getString()) {
                if (null != that.getString()) {
                    result_ = -1;
                }
            } else if (null != that.getString()) {
                result_ = getString().compareTo(that.getString());
            }
            if (result_ != 0) {
                return result_;
            }

            final int lhsi_ = getInt();
            final int rhsi_ = that.getInt();
            result_ = lhsi_ < rhsi_ ? -1 : (lhsi_ == rhsi_ ? 0 : 1);
            if (result_ != 0) {
                return result_;
            }

            final float lhsf_ = getFloat();
            final float rhsf_ = that.getFloat();
            result_ = lhsf_ < rhsf_ ? -1 : (lhsf_ == rhsf_ ? 0 : 1);
            if (result_ != 0) {
                return result_;
            }

            return result_;
        }
    }

    private final static class HipCompareToTestData extends TestData implements Comparable<TestData> {

        HipCompareToTestData(
                Map<String, Date> p0,
                Date p1,
                BigDecimal p2,
                UserComparable[] p3,
                Map<Integer, UserComparable> p4,
                String p5,
                int p6,
                float p7) {
            super(p0, p1, p2, p3, p4, p5, p6, p7);
        }

        @Override
        public int compareTo(final TestData that) {
            if (isComparable(that)) {
                return GREATER_THAN;
            } else {
                return compareToBuilder()
                        .append(getStringDateMap(), that.getStringDateMap())
                        .append(getDate(), that.getDate())
                        .append(getBigDecimal(), that.getBigDecimal())
                        .append(getComparableElementArray(), that.getComparableElementArray())
                        .append(getIcMap(), that.getIcMap())
                        .append(getString(), that.getString())
                        .append(getInt(), that.getInt())
                        .append(getFloat(), that.getFloat())
                        .build();
            }
        }
    }

    private final static class OldSchoolUserComparable extends UserComparable implements Comparable<UserComparable> {

        @Override
        public int compareTo(final UserComparable that) {
            int result_ = 0;

            if (this == that) {
                return result_;
            }
            if (that == null) {
                return 1;
            }

            if (null == getKey()) {
                if (null != that.getKey()) {
                    result_ = -1;
                }
            } else if (null != that.getKey()) {
                result_ = getKey().compareTo(that.getKey());
            }
            if (result_ != 0) {
                return result_;
            }

            return result_;
        }
    }

    private final static class HipUserComparable extends UserComparable implements Comparable<UserComparable> {

        @Override
        public int compareTo(final UserComparable that) {
            if (isComparable(that)) {
                return compareToBuilder()
                        .append(getKey(), that.getKey())
                        .build();
            } else {
                return EQUAL;
            }
        }
    }
}
