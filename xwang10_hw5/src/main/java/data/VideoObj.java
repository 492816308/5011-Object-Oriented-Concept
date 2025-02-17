package data;

import java.util.Objects;

/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * <p><b>Class Type:</b> Immutable Data Class</p>
 * <p><b>Object Invariant:</b></p>
 *   Title is non-null, no leading or final spaces, not empty string.
 * <p><b>Object Invariant:</b></p>
 *   Year is greater than 1800, less than 5000.
 * <p><b>Object Invariant:</b></p>
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable<VideoObj> {

    /** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
    private final String title;

    /** <p><b>Invariant:</b> greater than 1800, less than 5000 </p>*/
    private final int    year;

    /** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
    private final String director;

    /**
     * Initialize all object attributes.
     * Title and director are "trimmed" to remove leading and final space.
     * @throws IllegalArgumentException if any object invariant is violated.
     */
    VideoObj(String title, int year, String director) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (year < 1801 || year > 4999) {
            throw new IllegalArgumentException("year must be between 1801 and 4999");
        }
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("director cannot be null or empty");
        }
        this.title = title.trim();
        this.year = year;
        this.director = director.trim();
    }

    /**
     * Return the value of the attribute.
     */
    public String director() {
        return director;
    }

    /**
     * Return the value of the attribute.
     */
    public String title() {
        return title;
    }

    /**
     * Return the value of the attribute.
     */
    public int year() {
        return year;
    }

    /**
     * Compare the attributes of this object with those of thatObject.
     * @param thatObject the Object to be compared.
     * @return deep equality test between this and thatObject.
     */
    @Override
    public boolean equals(Object thatObject) {
        if (this == thatObject) return true;  // == is to check if the references are the same. If so, return true.
        if (!(thatObject instanceof VideoObj)) return false;  // instanceof is to check if they are the same type.
        VideoObj other = (VideoObj) thatObject;
        return year == other.year
               && Objects.equals(title, other.title)
               && Objects.equals(director, other.director);
    }

    /**
     * Return a hash code value for this object using the algorithm from Bloch:
     * fields are added in the following order: title, year, director.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Integer.hashCode(year);
        result = 31 * result + Objects.hashCode(director);
        return result;
    }

    /**
     * Compares the attributes of this object with those of thatObject, in
     * the following order: title, year, director.
     * @param thatObject the VideoObj to be compared.
     * @return a negative integer, zero, or a positive integer as this
     *  object is less than, equal to, or greater than that object.
     */
    @Override
    public int compareTo(VideoObj thatObject) {
        if (thatObject == null) {
            throw new NullPointerException("thatObject cannot be null");
        }
        int result = Integer.compare(year, (thatObject).year);
        if (result == 0) {
            result = title.compareTo((thatObject).title);
            if (result == 0) {
            result = director.compareTo((thatObject).director);
            }
        }
        return result;
    }

    /**
     * Return a string representation of the object in the following format:
     * <code>"title (year) : director"</code>.
     */
    @Override
    public String toString() {
        return title +
               " (" +
               year +
               ") : " +
               director;
    }

}