package registration;

import enums.SubjectCode;
import java.util.List;
import java.util.ArrayList;


/**
 * The Course class holds information about a course.
 * 
 * For example, CPSC 5011: Object-Oriented Concepts
 * - subject code: CPSC
 * - course number: 5011
 * - course name: Object-Oriented Concepts
 * - credit number: 3
 * - prerequisite(s): CPSC 5005 (can have multiple prerequisites or none)
 * 
 * @author Xiaoyu Wang
 */
public class Course {
	
	/**
	 * Constructor.
	 * @param code		The subject code of the course
	 * @param courseNum	The course number of the course
	 * @param name		The course name
	 * @param creditNum The number of the credits of the course
	 */
	public Course(SubjectCode code, int courseNum, String name, int creditNum) {
		
		subjectCode = code;
		courseNumber = courseNum;
		courseName = name;
		creditNumber = creditNum;
		this.prerequisites = new ArrayList<>();
		this.sections = new ArrayList<>();
	}

	// subject code, course number, course name, credit number,
	// and a collection of prerequisite course(s)
	private final SubjectCode subjectCode;
	private final int courseNumber;
	private final String courseName;
	private final int creditNumber;
	private final List<Course> prerequisites;
	private final List<Section> sections;

	// Getters and setters

	/**
	 * The addPrerequisite method adds prerequisite to the course.
	 * @param prerequisite The prerequisite to the course
	 */
	public void addPrerequisite(Course prerequisite) { prerequisites.add(prerequisite); }

	/**
	 * The getSubjectCode return the subject code of a course.
	 * @return The subject code.
	 */
	public SubjectCode getSubjectCode() { return subjectCode; }

	/**
	 * The getCourseNumber returns the course number.
	 * @return The course number.
	 */
	public int getCourseNumber() { return courseNumber; }

	/**
	 * The getCourseName returns the course name.
	 * @return The course name.
	 */
	public String getCourseName() { return courseName; }

	/**
	 * The getSections
	 * @return the section of the course.
	 */
	public List<Section> getSections() { return sections; }

	@Override
    public String toString() {
		List<String> prerequisiteNames = new ArrayList<>();
		for(Course prerequisite : prerequisites) {
			prerequisiteNames.add("Name=" + prerequisite.getCourseNumber() + ": " + prerequisite.getCourseName());
		}
		return String.format("Course: Name=%s-%d: %s, Credits=%d, Prerequisites=[%s]",
				subjectCode, courseNumber, courseName, creditNumber, String.join(", ", prerequisiteNames));
	}
}

