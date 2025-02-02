package registration;

import enums.Building;
import enums.Quarter;
import person.Faculty;

/**
 * The Section class holds information about a course section.
 * For example, CPSC 5011-01: Object-Oriented Concepts
 * - course: CPSC 5011
 * - section: 02
 * - instructor: Sheila Oh
 * - quarter/year: FQ18
 * - capacity: 30
 * - building/room: LEML 122
 * 
 * @author Xiaoyu Wang
 */
public class Section {	
	
	/**
	 * Constructor.
	 * @param course	 The course associated with the section
	 * @param section	 The section number for the course
	 * @param instructor The faculty instructor teaching the course
	 * @param quarter	 The quarter that the course section is held 
	 * @param year		 The year that the course section is held
	 * @param cap		 The capacity of the course section
	 * @param bldg		 The building that the course section is held
	 * @param room		 The room that the course section is held
	 */
	public Section(Course course, int section, Faculty instructor, Quarter quarter, 
					int year, int cap, Building bldg, String room) {
		
		this.course = course;
		this.section = section;
		this.instructor = instructor;
		this.quarter = quarter;
		this.year = year;
		this.cap = cap;
		this.bldg = bldg;
		this.room = room;
	}

	// course, section, instructor, quarter/year, capacity, building, room
	private final Course course;
	private final int section;
	private final Faculty instructor;
	private final Quarter quarter;
	private final int year;
	private final int cap;
	private final Building bldg;
	private final String room;

	@Override
	public String toString() {
		return String.format("Section: Course=%s-%d-%02d: %s, Faculty=%s %s, Term=%s %d, Capacity=%d, Room=%s %s",
				course.getSubjectCode(), course.getCourseNumber(), section, course.getCourseName(),
				instructor.getFirstName(), instructor.getLastName(), quarter.name(), year, cap, bldg.name(),room);
	}
	public int getSectionNum() { return section; }

}
