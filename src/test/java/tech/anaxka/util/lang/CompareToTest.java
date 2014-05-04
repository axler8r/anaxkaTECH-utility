package tech.anaxka.util.lang;

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
import static tech.anaxka.common.utility.lang.Equals.equalsBuilder;
import static tech.anaxka.common.utility.lang.Equals.isEquatable;
import static tech.anaxka.common.utility.lang.HashCode.hashCodeBuilder;

/**
 *
 * @author Adolf.Mattheus
 */
@Test(singleThreaded = true)
public class CompareToTest {

    /**
     *
     */
    public CompareToTest() {
    }

    @DataProvider
    Object[][] reflexiveTestData() {
        final Random random_ = new Random();

        final TestData reflexiveHipTestData_ = new HipCompareToTestData(
                mapBuilder(new HashMap<String, Date>())
                .append(
                        "Yesterday",
                        dateTimeBuilder()
                        .setDateTime(new Date())
                        .addDays(-1)
                        .build())
                .build(),
                dateTimeBuilder()
                .build(),
                new BigDecimal("258963147.369852147"),
                new UserComparable[]{
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable(),
                    new HipUserComparable()
                },
                mapBuilder(new HashMap<Integer, HipUserComparable>())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .build(),
                UUID.randomUUID().toString(),
                random_.nextInt(),
                random_.nextFloat());

        return new Object[][]{
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
        final Date YESTERDAY = dateTimeBuilder().setDateTime(NOW).addDays(-1).build();
        final Date TOMORROW = dateTimeBuilder().setDateTime(NOW).addDays(+1).build();
        final String STRING = UUID.randomUUID().toString();
        final int INT = random_.nextInt();
        final float FLOAT = random_.nextFloat();
        final BigDecimal BIG_DECIMAL = new BigDecimal("258963147.369852147456987123");
        final UserComparable[] USER_COMPARABLE_ARRAY = new UserComparable[]{
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable()
        };
        final Map INT_USER_COMPARABLE_MAP = mapBuilder(new HashMap<Integer, HipUserComparable>())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .build();
        final Map STRING_DATE_MAP = mapBuilder(new HashMap<String, Date>())
                .append("Yesterday", YESTERDAY)
                .append("Today", NOW)
                .append("Tomorrow", TOMORROW)
                .build();

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
        final Date YESTERDAY = dateTimeBuilder().setDateTime(NOW).addDays(-1).build();
        final Date TOMORROW = dateTimeBuilder().setDateTime(NOW).addDays(+1).build();
        final String STRING = UUID.randomUUID().toString();
        final int INT = random_.nextInt();
        final float FLOAT = random_.nextFloat();
        final BigDecimal BIG_DECIMAL = new BigDecimal("258963147.369852147456987123");
        final UserComparable[] USER_COMPARABLE_ARRAY = new UserComparable[]{
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable(),
            new HipUserComparable()
        };
        final Map INT_USER_COMPARABLE_MAP = mapBuilder(new HashMap<Integer, HipUserComparable>())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
                .append(random_.nextInt(), new HipUserComparable())
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

    /**
     *
     * @param a
     * @param b
     * @param c
     */
    @Test(dataProvider = "transitiveTestData")
    public void testTransitivity(final TestData a, final TestData b, final TestData c) {
        final int ab_ = a.compareTo(b);
        final int bc_ = b.compareTo(c);
        final int ac_ = a.compareTo(c);
        assertTrue((ab_ == bc_) && (ab_ == ac_));
    }

    /**
     *
     * @param data
     */
    @Test(dataProvider = "reflexiveTestData")
    public void testConsistency(final TestData data) {
        assertTrue(data.compareTo(null) > 0);
    }

    /**
     *
     * @param data
     */
    @Test(dataProvider = "reflexiveTestData")
    public void testNullComparison(final TestData data) {
        assertTrue(data.compareTo(null) > 0);
    }

    /**
     *
     */
    public abstract static class TestData implements Comparable<TestData> {

        private final Map<String, Date> __sdMap;
        private final Date __date;
        private final BigDecimal __bd;
        private final UserComparable[] __cis;
        private final Map<Integer, UserComparable> __icMap;
        private final String __string;
        private final int __int;
        private final float __float;

        /**
         *
         * @param p0
         * @param p1
         * @param p2
         * @param p3
         * @param p4
         * @param p5
         * @param p6
         * @param p7
         */
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
            __string = p5;
            __int = p6;
            __float = p7;
        }

        /**
         *
         * @return
         */
        public Map<String, Date> getStringDateMap() {
            return __sdMap;
        }

        /**
         *
         * @return
         */
        public Date getDate() {
            return new Date(__date.getTime());
        }

        /**
         *
         * @return
         */
        public BigDecimal getBigDecimal() {
            return __bd;
        }

        /**
         *
         * @return
         */
        public UserComparable[] getComparableElementArray() {
            return Arrays.copyOf(__cis, __cis.length);
        }

        /**
         *
         * @return
         */
        public Map<Integer, UserComparable> getIcMap() {
            return __icMap;
        }

        /**
         *
         * @return
         */
        public String getString() {
            return __string;
        }

        /**
         *
         * @return
         */
        public int getInt() {
            return __int;
        }

        /**
         *
         * @return
         */
        public float getFloat() {
            return __float;
        }
    }

    /**
     *
     */
    public abstract static class UserComparable implements Comparable<UserComparable> {

        private final UUID __key = UUID.randomUUID();

        /**
         *
         */
        public UserComparable() {
        }

        /**
         *
         * @return
         */
        public UUID getKey() {
            return __key;
        }
    }

    private final static class HipCompareToTestData extends TestData {

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
        public boolean equals(final Object that) {
            boolean result_ = false;

            if (isEquatable(this, that)) {
                final HipCompareToTestData that_ = HipCompareToTestData.class.cast(that);

                result_ = equalsBuilder()
                        .append(getStringDateMap(), that_.getStringDateMap())
                        .append(getDate(), that_.getDate())
                        .append(getBigDecimal(), that_.getBigDecimal())
                        .append(getComparableElementArray(), that_.getComparableElementArray())
                        .append(getIcMap(), that_.getIcMap())
                        .append(getString(), that_.getString())
                        .append(getInt(), that_.getInt())
                        .append(getFloat(), that_.getFloat())
                        .build();
            }

            return result_;
        }

        @Override
        public int compareTo(final TestData that) {
            if (isComparable(that)) {
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
            } else {
                return GREATER_THAN;
            }
        }

        @Override
        public int hashCode() {
            return hashCodeBuilder()
                    .append(getStringDateMap())
                    .append(getDate())
                    .append(getBigDecimal())
                    .append(getComparableElementArray())
                    .append(getIcMap())
                    .append(getString())
                    .append(getInt())
                    .append(getFloat())
                    .build();
        }
    }

    private final static class HipUserComparable extends UserComparable {

        @Override
        public boolean equals(final Object that) {
            boolean result_ = false;

            if (isEquatable(this, that)) {
                final UserComparable that_ = UserComparable.class.cast(that);

                result_ = equalsBuilder()
                        .append(getKey(), that_.getKey())
                        .build();
            }

            return result_;
        }

        
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

        @Override
        public int hashCode() {
            return hashCodeBuilder().append(getKey()).build();
        }
    }
}
