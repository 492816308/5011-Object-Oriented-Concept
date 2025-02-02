package system;

import enums.*;
import exception.CourseNotFoundException;
import exception.DuplicateCourseException;
import exception.DuplicatePersonException;
import exception.DuplicateSubjectException;
import exception.PersonNotFoundException;
import exception.DuplicateSectionException;
import registration.Course;
import registration.Section;
import person.Faculty;
import person.Student;
import java.util.*;

/**
 * The RegistrationSystem class stores information about the school, including
 * the ability to add students, add faculty, add courses, and add prerequisite(s).
 * 
 * @author Xiaoyu Wang
 */
public class RegistrationSystem {

	// Create a random object (for later assigning an advisor to a student)
	Random random = new Random();

	/**
	 * Constructor.
	 */
	public RegistrationSystem() { 
		
		studentList = new ArrayList<>();
		facultyList = new ArrayList<>();
		subjectList = new HashMap<>();
		courseList = new ArrayList<>();
		sectionList = new ArrayList<>();
		nextSUID = 100000;
	}

	// student list, faculty list, subject list, course list, section list
	// note that there is not list for prerequisites - these should be included
	// as part of the course list
	private final List<Student> studentList;
	private final List<Faculty> facultyList;
	private final Map<SubjectCode, String> subjectList;
	private final List<Course> courseList;
	private final List<Section> sectionList;
	private int nextSUID;
	
	/**
	 * Add a student to the student list collection.
	 * 
	 * @param firstName	The first name of the student
	 * @param lastName	The last name of the student
	 * @param type		The student type
	 * @param program	The student program	
	 * @param quarter	The start quarter of the student
	 * @param year		The start year of the student
	 * @throws DuplicatePersonException The person is already in the system
	 */
	public void addStudent(String firstName, String lastName, 
							StudentType type, StudentProgram program,
							Quarter quarter, int year) 
							throws DuplicatePersonException {
		
		for (Student s:studentList) {
			// Check if the input student is already on the list. Throw an exception if so.
			if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)) {
				throw new DuplicatePersonException();
			}
		}

		// Create a student object
		Student student = new Student(firstName, lastName);
		student.setStudentType(type);  // Set student type
		student.setStudentProgram(program);  // Set student program
		student.setTerm(quarter, year);  // Set student term
		student.setSuid(nextSUID);  // assign SUID
		student.setEmail(firstName + " " + lastName);  // Set student email

		// Assign a random advisor for a student from the faculty list
		Faculty faculty = facultyList.get(random.nextInt(facultyList.size()));
		student.setFacultyAdvisor(faculty);
		// Add the new student to student list
		studentList.add(student);
		nextSUID++;  // Increment SUID
	}
	
	/**
	 * Add a faculty to the faculty list collection.
	 * 
	 * @param firstName	The first name of the faculty
	 * @param lastName	The last name of the faculty
	 * @param type		The faculty type
	 * @param bldg		The building of the faculty office
	 * @param room		The (building) room of the faculty office
	 * @param email		The email of the faculty
	 * @throws DuplicatePersonException The person is already in the system
	 */
	public void addFaculty(String firstName, String lastName,
							FacultyType type, Building bldg, String room, String email)
							throws DuplicatePersonException {	
		
		for (Faculty f:facultyList) {
			// Check if the faculty member is already on the list. If so throw an exception.
			if (f.getFirstName().equals(firstName) && f.getLastName().equals(lastName)) {
				throw new DuplicatePersonException();
			}
		}

		// Create a faculty object
		Faculty faculty = new Faculty(firstName, lastName);
		faculty.setFacultyType(type);  // Set faculty member type
		faculty.setOffice(bldg, room);  // Set faculty member's office
		faculty.setEmail(email);  // Set faculty member email
		faculty.setSuid(nextSUID);  // Set suid for a faculty member
		facultyList.add(faculty);  // Add the new faculty member to the faculty list
		nextSUID++;  // Increment SUID
	}
	
	/**
	 * Adds a subject to the subject list collection.
	 * (hint: use a Pair instead of creating a class)
	 * 
	 * @param code	The subject code
	 * @param desc	The subject description
	 * 
	 * @throws DuplicateSubjectException The subject is already in the system
	 */
	public void addSubject(SubjectCode code, String desc) 
							throws DuplicateSubjectException {
		
		// Check if the subject code exists on the list, if so throw an exception
		if (subjectList.containsKey(code)) {
			throw new DuplicateSubjectException();
		}
		// Add the subject to the subject list
		subjectList.put(code, desc);
	}
	
	/**
	 * Adds a course to the course list collection.
	 * 
	 * @param code		The subject code of the course
	 * @param num		The course number of the course
	 * @param name		The course name
	 * @param creditNum	The number of the credits of the course
	 * @throws DuplicateCourseException	The course is already in the system 
	 */
	public void addCourse(SubjectCode code, int num, String name, 
							int creditNum) throws DuplicateCourseException {
		
		for (Course c:courseList) {
			// Check if the course exists on the list, if so throw an exception
			if (c.getSubjectCode().equals(code) && c.getCourseNumber() == num) {
				throw new DuplicateCourseException();
			}
		}
		// Create a course object
		Course course = new Course(code, num, name, creditNum);
		courseList.add(course);  // Add the course to the course list
	}
	
	/**
	 * Adds a prerequisite to an existing course in the course
	 * list collection.
	 * 
	 * @param code			The subject code of the course
	 * @param num			The course number of the course
	 * @param prereqCode	The subject code of the prerequisite
	 * 						to add to the course
	 * @param prereqNum		The course number of the prerequisite
	 * 						to add to the course
	 * @throws CourseNotFoundException The course was not found in the system
	 */
	public void addPrerequisite(SubjectCode code, int num, 
							SubjectCode prereqCode, int prereqNum) 
							throws CourseNotFoundException {
		
		// Find the course and its prerequisite from the course list
		Course course = findCourse(code, num);
		Course prerequisite = findCourse(prereqCode, prereqNum);
		course.addPrerequisite(prerequisite);  // Add the prerequisite to the course
	}

	
	/**
	 * Adds a section to the section list collection.
	 * 
	 * @param code		 The subject code of the course
	 * @param courseNum	 The course number of the course
	 * @param sectionNum The section number for the course
	 * @param firstName	 The first name for the faculty teaching the course
	 * @param lastName	 The last name for the faculty teaching the course
	 * @param quarter	 The quarter that the course section is held 
	 * @param year		 The year that the course section is held
	 * @param cap		 The capacity of the course section
	 * @param bldg		 The building that the course section is held
	 * @param room		 The room that the course section is held
	 * @throws CourseNotFoundException The course was not found in the system
	 * @throws PersonNotFoundException The person was not found in the system
	 * @throws DuplicateSectionException The section is already in the system
	 */
	public void addSection(SubjectCode code, int courseNum, int sectionNum,
							String firstName, String lastName, Quarter quarter, int year, 
							int cap, Building bldg, String room)
							throws CourseNotFoundException, PersonNotFoundException, DuplicateSectionException {


		// Find course and its instructor from the course list and faculty list
		Course course = findCourse(code, courseNum);
		Faculty instructor = findFaculty(firstName, lastName);

		// Check if the section exists on the list. If so throw an exception
		for (Section s: course.getSections()) {
			if (s.getSectionNum() == sectionNum) {
				throw new DuplicateSectionException();
			}
		}
		// Create a section object and pass in the parameters
		Section section = new Section(course, sectionNum, instructor, quarter, year, cap, bldg, room);
		sectionList.add(section);  // Add the section to the section list
	}

	/**
	 * Helper method to find if the course is on the list.
	 * @param code The subject code
	 * @param courseNum The course number
	 * @return The course object if found.
	 * @throws CourseNotFoundException The course is not found.
	 */
	private Course findCourse(SubjectCode code, int courseNum) throws CourseNotFoundException {
		for (Course c:courseList) {
			if (c.getSubjectCode() == code && c.getCourseNumber() == courseNum) {
				return c;
			}
		}
		throw new CourseNotFoundException();
	}

	/**
	 * Helper method to find if the faculty member is on the list.
	 * @param firstName Firstname of the faculty member.
	 * @param lastName Lastname of the faculty member.
	 * @return The faculty object if found.
	 * @throws PersonNotFoundException The person is not found on the list.
	 */
	private Faculty findFaculty(String firstName, String lastName) throws PersonNotFoundException {
		for (Faculty f:facultyList) {
			if (f.getFirstName().equals(firstName) && f.getLastName().equals(lastName)) {
				return f;
			}
		}
		throw new PersonNotFoundException();
	}

	/**
	 * Method to print the faculty list.
	 */
	public void printFacultyList() {
		System.out.println("-- FACULTY LIST --");
		for (Faculty f:facultyList) {
			System.out.println(f);
		}
	}

	/**
	 * Method to print the student list.
	 */
	public void printStudentList() {
		System.out.println("\n\n-- STUDENT LIST --");
		for (Student s:studentList) {
			System.out.println(s);
		}
	}

	/**
	 * Method to print the subject list.
	 */
	public void printSubjectList() {
		System.out.println("\n\n-- SUBJECT LIST --");
		for (Map.Entry<SubjectCode,String> e:subjectList.entrySet()) {
			System.out.printf("Subject: %s (%s)\n", e.getValue(), e.getKey());
		}
	}

	/**
	 * Method to print the course list.
	 */
	public void printCourseList() {
		System.out.println("\n\n-- COURSE LIST --");
		for (Course c:courseList) {
			System.out.println(c);
		}
	}

	/**
	 * Method to print the section list.
	 */
	public void printSectionList() {
		System.out.println("\n\n-- SECTION LIST --");
		for (Section s:sectionList) {
			System.out.println(s);
		}
	}
}
