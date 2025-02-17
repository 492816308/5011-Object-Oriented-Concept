package data;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

/**
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * <p><b>Class Type:</b> Mutable Collection of Records</p>
 * <p><b>Object Invariant:</b></p>
 *   Every key and value in the map is non-<code>null</code>.
 * <p><b>Object Invariant:</b></p>
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 */
final class InventorySet {

    /** <p><b>Invariant:</b> <code>_data != null</code> </p>*/
    private final Map<VideoObj,Record> data = new HashMap<VideoObj,Record>();

    /**
     * Default constructor.
     */
    InventorySet() { }

    /**
     * Return the number of Records.
     */
    public int size() {
        return data.size();
    }

    /**
     *  Return a copy of the record for a given Video; if not present, return <code>null</code>.
     */
    public Record get(VideoObj v) {
        if (!data.containsKey(v)) {
            return null;
        }
        Record r = data.get(v).copy();
        return r;
    }

    /**
     * Return a copy of the records as a collection.
     * Neither the underlying collection, nor the actual records are returned.
     */
    public Collection<Record> toCollection() {
        // Recall that an ArrayList is a Collection.
        // Declare a collection as an arraylist
        Collection<Record> c = new ArrayList<Record>();
        for (Record r : data.values()) {
            c.add(r.copy());
        }
        return c;
    }

    /**
     * Add or remove copies of a video from the inventory.
     * If a video record is not already present (and change is
     * positive), a record is created.
     * If a record is already present, <code>numOwned</code> is
     * modified using <code>change</code>.
     * If <code>change</code> brings the number of copies to be zero,
     * the record is removed from the inventory.
     * @param video the video to be added.
     * @param change the number of copies to add (or remove if negative).
     * @throws IllegalArgumentException if video null, change is zero,
     *  if attempting to remove more copies than are owned, or if
     *  attempting to remove copies that are checked out.
     * <p><b>Postcondition:</b> changes the record for the video</p>
     */
    public void addNumOwned(VideoObj video, int change) {
        // Validate inputs, if video is null, throw IllegalArgumentException
        if (video == null) {
            throw new IllegalArgumentException("Video cannot be null.");
        }

        // If change is zero, throw IllegalArgumentException
        if (change == 0) {
            throw new IllegalArgumentException("Change cannot be 0.");
        }

        // Create a Record object associated with video in the inventory
        Record existingRecord = data.get(video);

        // Case 1: Video doesn't exist in inventory
        if (existingRecord == null) {
            // If change is negative, throw IllegalArgumentException
            if (change < 0) {
                throw new IllegalArgumentException("Cannot remove copies of a non-existent video.");
            }
            // Else, create a new Record for video and push it into inventory
            data.put(video, new Record(video, change, 0, 0));
        }

        // Case 2: Video exists
        else {
            int newOwned = existingRecord.numOwned + change;
            // If attempting to check out more copies than are owned, throw IllegalArgumentException
            if (newOwned < existingRecord.numOut) {
                throw new IllegalArgumentException("Not enough copies of video to check out.");
            }
            // If change brings the number of copies to be zero, remove the record from the inventory
            if (newOwned == 0) {
                data.remove(video);
            // Else, update the number of copies in the inventory
            } else {
                existingRecord.numOwned = newOwned;
            }
        }
    }

    /**
     * Check out a video.
     * @param video the video to be checked out.
     * @throws IllegalArgumentException if video has no record or numOut
     * equals numOwned.
     * <p><b>Postcondition:</b> changes the record for the video</p>
     */
    public void checkOut(VideoObj video) {
        // Create a Record object associated with video from InventorySet.
        Record existingRecord = data.get(video);

        // If video doesn't exist in inventory, throw IllegalArgumentException
        if (existingRecord == null) {
            throw new IllegalArgumentException("No record of video in the inventory.");
        }

        // If numOut equals numOwned, throw IllegalArgumentException
        if (existingRecord.numOut == existingRecord.numOwned) {
            throw new IllegalArgumentException("All copies of video are checked out.");
        }

        // Otherwise, increment the numOut
        existingRecord.numOut++;
    }

    /**
     * Check in a video.
     * @param video the video to be checked in.
     * @throws IllegalArgumentException if video has no record or numOut
     * non-positive.
     * <p><b>Postcondition:</b> changes the record for the video</p>
     */
    public void checkIn(VideoObj video) {
        // Create a Record object associated with video from InventorySet.
        Record r = data.get(video);

        // If video has no record, throw IllegalArgumentException
        if (r == null) {
            throw new IllegalArgumentException("No record of video in the inventory.");
        }

        // If numOut is non-positive, throw IllegalArgumentException
        if (r.numOut <= 0) {
            throw new IllegalArgumentException("The video has not been checked out.");
        }

        // Otherwise, decrement the numOut
        r.numOut--;
    }

    /**
     * Remove all records from the inventory.
     * <p><b>Postcondition:</b> <code>size() == 0</code></p>
     */
    public void clear() {
        data.clear();
    }

    /**
     * Return the contents of the inventory as a string.
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Database:\n");
        for (Record r : data.values()) {
            buffer.append("  ");
            buffer.append(r);
            buffer.append("\n");
        }
        return buffer.toString();
    }

}