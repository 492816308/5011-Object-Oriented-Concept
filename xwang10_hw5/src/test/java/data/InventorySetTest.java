package data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;

public class InventorySetTest {

    InventorySet s = new InventorySet();
    final VideoObj v1 = new VideoObj( "A", 2000, "B" );
    final VideoObj v1copy = new VideoObj( "A", 2000, "B" );
    final VideoObj v2 = new VideoObj( "B", 2000, "B" );

    @Test
    public void testAddNumOwned() {
        assertEquals( 0, s.size() );
        s.addNumOwned(v1, 1);     assertEquals( v1, s.get(v1).video );
        assertEquals( 1, s.get(v1).numOwned );
        s.addNumOwned(v1, 2);     assertEquals( 3, s.get(v1).numOwned );
        s.addNumOwned(v1, -1);    assertEquals( 2, s.get(v1).numOwned );
        s.addNumOwned(v2, 1);     assertEquals( 2, s.get(v1).numOwned );
        s.addNumOwned(v1copy, 1); assertEquals( 3, s.get(v1).numOwned );
        assertEquals( 2, s.size() );
        s.addNumOwned(v1, -3);
        assertEquals( 1, s.size() );
        try { s.addNumOwned(null, 1);   fail(); } catch ( IllegalArgumentException e ) {}
    }

    @Test
    public void testSize() {
        assertEquals( 0, s.size() );
        s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
        s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
        s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
        s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
        s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
        try { s.addNumOwned(v1, -3); fail(); } catch ( IllegalArgumentException e ) {}
        assertEquals( 0, s.size() );
    }

    @Test
    public void testCheckOutCheckIn() {
        // Setup initial state
        s.addNumOwned(v1, 3);

        // Test checkOut and checkIn without exceptions
        s.checkOut(v1);
        assertEquals(1, s.get(v1).numOut);
        assertEquals(3, s.get(v1).numOwned); // numOwned unchanged

        s.checkIn(v1);
        assertEquals(0, s.get(v1).numOut);
        assertEquals(3, s.get(v1).numOwned); // numOwned unchanged

        // Test exceptions
        // Case1: check out a video that doesn't exist in inventory
        // Check out all 3 copies first
        s.checkOut(v1);
        s.checkOut(v1);
        s.checkOut(v1);
        assertEquals(3, s.get(v1).numOut);

        // Check out when all copies are out
        try {
            s.checkOut(v1);
            fail();
        } catch ( IllegalArgumentException e ) {}

        // Case2: check in a copy that hasn't been checked out
        try {
            s.checkIn(v2);
            fail();
        } catch ( IllegalArgumentException e ) {}

        // Case3: check out and check in non-existent video
        try {
            s.checkOut(new VideoObj("C", 2000, "C"));
        } catch ( IllegalArgumentException e ) {}

        try {
            s.checkIn(new VideoObj("C", 2000, "C"));
        } catch ( IllegalArgumentException e ) {}
    }

    @Test
    public void testClear() {
        s.addNumOwned(v1, 2);
        s.addNumOwned(v2, 1);
        assertEquals( 2, s.size() );
        s.clear();
        assertEquals( 0, s.size() );
        assertNull(s.get(v1));
        assertNull(s.get(v2));
    }

    @Test
    public void testGet() {
        // Get should return a COPY of the records, not the records themselves.
        s.addNumOwned(v1, 3);
        Record rec1 = s.get(v1);
        Record rec2 = s.get(v1);
        assertNotSame(rec1, rec2); // different objects

        // Modify the copy and check original remains unchanged
        rec1.numOwned = 100;
        assertEquals(3, s.get(v1).numOwned);
    }

    @Test
    public void testToCollection() {
        // Be sure to test that changing records in the returned
        // collection does not change the original records in the
        // inventory.  ToCollection should return a COPY of the records,
        // not the records themselves.
        s.addNumOwned(v1, 2);
        Collection<Record> c = s.toCollection();

        // Modify a record in the collection
        Record rec = c.iterator().next();
        rec.numOwned = 100;

        // Original record in inventory should remain unchanged
        assertEquals(2, s.get(v1).numOwned);
    }

}