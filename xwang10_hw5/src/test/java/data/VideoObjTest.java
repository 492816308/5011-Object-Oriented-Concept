package data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VideoObjTest {

    @Test
    public void testConstructorAndAttributes() {
        String title1 = "XX";
        String director1 = "XY";
        String title2 = " XX ";
        String director2 = " XY ";
        int year = 2002;

        VideoObj v1 = new VideoObj(title1, year, director1);
        assertSame(title1, v1.title());
        assertEquals(year, v1.year());
        assertSame(director1, v1.director());

        VideoObj v2 = new VideoObj(title2, year, director2);
        assertEquals(title1, v2.title());
        assertEquals(director1, v2.director());
    }

    @Test
    public void testConstructorExceptionYear() {
        try {
            new VideoObj("X", 1800, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("X", 5000, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("X", 1801, "Y");
            new VideoObj("X", 4999, "Y");
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testConstructorExceptionTitle() {
        try {
            new VideoObj(null, 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj(" ", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testConstructorExceptionDirector() {
        try {
            new VideoObj("A", 2002, null);
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("A", 2002, "");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("A", 2002, " ");
            fail();
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testHashCode() {
        assertEquals
                (-1869216300, new VideoObj("None", 2009, "Zebra").hashCode());
        assertEquals
                (2057695967, new VideoObj("Blah", 1954, "Cante").hashCode());
    }

    @Test
    public void testEquals() {
        VideoObj v1 = new VideoObj("Blah", 1954, "Cante");
        VideoObj v2 = new VideoObj("Blah", 1954, "Cante");
        VideoObj v3 = new VideoObj("Blah", 1954, "Cante");
        VideoObj v4 = new VideoObj("Blah", 1955, "Cante");  // Different year
        VideoObj v5 = new VideoObj("Blahhh", 1954, "Cante");  // Different title
        VideoObj v6 = new VideoObj("Blah", 1954, "Canteee");  // Different director
        // test reflexivity
        assertTrue(v1.equals(v1));

        // test symmetry
        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v1));

        // test transitivity
        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v3));
        assertTrue(v1.equals(v3));

        // null check
        assertFalse(v1.equals(null));

        // Inequality checks
        assertFalse(v1.equals(v4));
        assertFalse(v1.equals(v5));
        assertFalse(v1.equals(v6));
    }

    @Test
    public void testCompareTo() {
        VideoObj v1 = new VideoObj("Blah", 1954, "Cante");
        VideoObj v2 = new VideoObj("Blah", 1954, "Cante");
        VideoObj v3 = new VideoObj("Blah", 1955, "Cante");  // Higher year
        VideoObj v4 = new VideoObj("Alpha", 1954, "Cante"); // Lower title
        VideoObj v5 = new VideoObj("Blah", 1954, "Beta");   // Lower director
        VideoObj v6 = new VideoObj("blah", 1954, "Cante");  // Lowercase title (case-sensitive)
        VideoObj v7 = new VideoObj("Blah", 1954, "cante");  // Lowercase director

        // Equality
        assertEquals(0, v1.compareTo(v1)); // Reflexivity
        assertEquals(0, v1.compareTo(v2)); // Same fields

        // Year comparisons
        assertTrue(v1.compareTo(v3) < 0);  // 1954 < 1955 → negative
        assertTrue(v3.compareTo(v1) > 0);   // Symmetry check

        // Title comparisons
        assertTrue(v1.compareTo(v4) > 0);  // "Blah" > "Alpha" → positive
        assertTrue(v4.compareTo(v1) < 0);  // Symmetry check

        // Director comparisons
        assertTrue(v1.compareTo(v5) > 0);  // "Cante" > "Beta" → positive
        assertTrue(v5.compareTo(v1) < 0);  // Symmetry check

        // Case sensitivity checks
        assertTrue(v1.compareTo(v6) < 0);  // "Blah" < "blah" (ASCII: 'B' < 'b')
        assertTrue(v1.compareTo(v7) < 0);  // "Cante" < "cante" (ASCII: 'C' < 'c')

        // Consistency with equals
        assertTrue(v1.equals(v2) && v1.compareTo(v2) == 0); // Equal objects → compareTo 0

        // Null check
        assertThrows(NullPointerException.class, () -> v1.compareTo(null));

    }

    @Test
    public void testToString() {
        String s = new VideoObj("A", 2000, "B").toString();
        assertEquals( "A (2000) : B", s );
        s = new VideoObj(" A ", 2000, " B ").toString();
        assertEquals( "A (2000) : B", s );
    }

}