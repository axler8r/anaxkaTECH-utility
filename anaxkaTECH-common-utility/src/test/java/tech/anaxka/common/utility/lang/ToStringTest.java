package tech.anaxka.common.utility.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static tech.anaxka.common.utility.lang.ToString.toStringBuilder;

/**
 *
 * @author Axl Mattheus
 */
public class ToStringTest {

    @BeforeClass
    private void setProperties() {
        /*
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.NAME_HASH_DELIMITER",
         "@");
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.NAME_VALUE_DELIMITER",
         ", ");
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.NAME_VALUE_SEPARATOR",
         " = ");
         */
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.MAP_NAME_VALUE_SEPARATOR",
         " -> ");
         /*
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.STATE_STOP_DELIMITER",
         "}");
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.STATE_START_DELIMITER",
         "{");
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.BYTECODE__LOCATION_LABEL",
         "Bytecode Location");
         System.setProperty(
         "_4axka.util.lang.ToStringBuilder.INDENTATION",
         "    ");
         */
    }

    @DataProvider(name = "root")
    Object[][] getGraph() {
        Node root_ = new Node();
        root_.addProperty("root.property.1", 1);
        root_.addProperty("root.property.A", "A");
        root_.addProperty("root.property.now", new Date());

        Node rc0_ = new Node();
        Terminal rc0t0_ = new Terminal(
                new BigDecimal("0.123456789098765432"),
                new BigInteger("1000000000000000000000000"),
                new Date(),
                "String",
                101.4d,
                new String[]{"A", "B", "C", "D"},
                Arrays.asList(1, 2, 3, 4, 5, 6));
        rc0_.addTeminal(rc0t0_);
        Terminal rc0t1_ = new Terminal(
                new BigDecimal("0.00123456789098765432"),
                new BigInteger("1000000000000000000000999"),
                new Date(),
                "Another String",
                101.4456d,
                new String[]{"W", "X", "Y", "Z"},
                Arrays.asList(0, 9, 8, 7, 6));
        rc0_.addTeminal(rc0t1_);
        root_.addContainee(rc0_);

        Node rc1_ = new Node();
        // ** NOISE ********************************************************************************
        // THIS DOES NOT FORMAT SO WELL. WORK ON toStringBuilder A BIT MORE...
        //
        // Map<Object, Object> whoaMap_ = new HashMap<>();
        // whoaMap_.put(new Date(), Arrays.asList(1, 2, 3, 4, 5));
        // whoaMap_.put(10, "Ten");
        // whoaMap_.put(Arrays.asList("A", "B", "C"), new BigDecimal("987.908897556566734209886"));
        // rc1_.addProperty("Whoa", whoaMap_);
        //
        // ** END NOISE ****************************************************************************
        Terminal rc1t0_ = new Terminal(
                new BigDecimal("-0.123456789098765432"),
                new BigInteger("-1000000000000000000000000"),
                new Date(),
                "gnirtS",
                -101.4d,
                new String[]{"A", "B", "C", "D"},
                Arrays.asList(-1, -2, -3, -4, -5, -6));
        rc1_.addTeminal(rc1t0_);
        Terminal rc1t1_ = new Terminal(
                new BigDecimal("-0.00123456789098765432"),
                new BigInteger("-1000000000000000000000999"),
                new Date(),
                "gnirtS rehtonA",
                -101.4456d,
                new String[]{"W", "X", "Y", "Z"},
                Arrays.asList(0, -9, -8, -7, -6));
        rc1_.addTeminal(rc1t1_);
        root_.addContainee(rc1_);

        return new Object[][]{{root_}};
    }

    /**
     *
     * @param root
     */
    @Test(dataProvider = "root", enabled = false)
    public void toStringTest(final Node root) {
        System.out.println(root.toString());
    }

    /**
     *
     * @param root
     */
    @Test(dataProvider = "root", enabled = false)
    public void toStringPrettyPrintTest(final Node root) {
        System.out.println(toStringBuilder(this).prettyPrint(root.toString()));
    }

    private static class Node {

        private final Map<String, Object> __properties;
        private final List<Node> __containees;
        private final List<Terminal> __terminals;

        Node() {
            __properties = new LinkedHashMap<>();
            __containees = new LinkedList<>();
            __terminals = new LinkedList<>();
        }

        Map<String, Object> getProperties() {
            return __properties;
        }

        void addProperty(final String key, final Object value) {
            __properties.put(key, value);
        }

        List<Node> getContainees() {
            return __containees;
        }

        void addContainee(final Node containee) {
            __containees.add(containee);
        }

        void addTeminal(final Terminal terminal) {
            __terminals.add(terminal);
        }

        List<Terminal> getTerminals() {
            return __terminals;
        }

        @Override
        public String toString() {
            return toStringBuilder(this)
                    .append("Properties", getProperties())
                    .append("Containees", getContainees())
                    .append("Terminals", getTerminals())
                    .append("super", super.toString())
                    .build();
        }
    }

    private static class Terminal {

        private final BigDecimal __bigDecimal;
        private final BigInteger __bigInteger;
        private final Date __date;
        private final String __string;
        private final Number __number;
        private final Object[] __array;
        private final List<?> __list;

        public Terminal(
                final BigDecimal bigDecimal,
                final BigInteger bigInteger,
                final Date date,
                final String string,
                final Number number,
                final Object[] array,
                final List<?> list) {
            __bigDecimal = bigDecimal;
            __bigInteger = bigInteger;
            __date = date;
            __string = string;
            __number = number;
            __array = array;
            __list = list;
        }

        public BigDecimal getBigDecimal() {
            return __bigDecimal;
        }

        public BigInteger getBigInteger() {
            return __bigInteger;
        }

        public Date getDate() {
            return __date;
        }

        public String getString() {
            return __string;
        }

        public Number getNumber() {
            return __number;
        }

        public Object[] getArray() {
            return __array;
        }

        public List<?> getList() {
            return __list;
        }

        @Override
        public String toString() {
            return toStringBuilder(this)
                    .append("Big Decimal", getBigDecimal())
                    .append("Big Integer", getBigInteger())
                    .append("Date", getDate())
                    .append("String", getString())
                    .append("Number", getNumber())
                    .append("Array", getArray())
                    .append("List", getList())
                    .append("super", super.toString())
                    .build();
        }
    }
}
