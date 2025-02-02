package driver;

import enums.Building;
import enums.FacultyType;
import enums.Quarter;
import enums.StudentType;
import enums.SubjectCode;
import enums.StudentProgram;
import exception.CourseNotFoundException;
import exception.DuplicateCourseException;
import exception.DuplicatePersonException;
import exception.DuplicateSubjectException;
import exception.PersonNotFoundException;
import system.RegistrationSystem;
import exception.DuplicateSectionException;
import exception.SectionNotFoundException;

/**
 * The Driver interfaces with the registration system and populates
 * faculty, students, subjects, courses, their prerequisites, and
 * course sections.
 * 
 * @author Xiaoyu Wang
 * @version 1.0
 */
public class Driver {

	/**
	 * The main method now calls print methods in the order seen in the sample output.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		RegistrationSystem system = new RegistrationSystem();
		populateSystem(system);	
		
		// this includes all the contents of: faculty, students, subjects,
		// courses (and their prerequisites), and course section; additional
		// functions may be added to the driver/System to complete this task
		system.printFacultyList();
		system.printStudentList();
		system.printSubjectList();
		system.printCourseList();
		system.printSectionList();

	}
	
	private static void populateSystem(RegistrationSystem system) {
		try {
			populateFaculty(system);
			populateStudents(system);
			populateSubjects(system);
			populateCourses(system);
			populatePrerequisites(system);
			populateSections(system);
		} catch (DuplicatePersonException e) {
			System.out.println(e.getMessage());
		} catch (DuplicateSubjectException e) {
			System.out.println(e.getMessage());
		} catch (DuplicateCourseException e) {
			System.out.println(e.getMessage());
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (PersonNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (DuplicateSectionException e) {
			System.out.println(e.getMessage());
		} catch (SectionNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void populateStudents(RegistrationSystem system) throws DuplicatePersonException {
		system.addStudent("Michael", "Bluth", StudentType.UNDERGRAD, StudentProgram.BSCS, Quarter.FQ, 2024);
		system.addStudent("Gob", "Bluth", StudentType.UNDERGRAD, StudentProgram.BACS, Quarter.SQ, 2024);
		system.addStudent("Buster", "Bluth", StudentType.UNDERGRAD, StudentProgram.BSCS, Quarter.WQ, 2024);
		system.addStudent("Lucille", "Bluth", StudentType.UNDERGRAD, StudentProgram.BSCS, Quarter.WQ, 2023);
		system.addStudent("Lindsay", "Funke", StudentType.UNDERGRAD, StudentProgram.BACS, Quarter.FQ, 2024);
		system.addStudent("Tobias", "Funke", StudentType.UNDERGRAD, StudentProgram.BACS, Quarter.FQ, 2023);
		system.addStudent("Maeby", "Funke", StudentType.UNDERGRAD, StudentProgram.BSCS, Quarter.SQ, 2023);
		system.addStudent("Kitty", "Sanchez", StudentType.NONMAT_UNDERGRAD, StudentProgram.UNDECIDED, Quarter.FQ, 2024);
		system.addStudent("Edsger", "Dijkstra", StudentType.GRAD, StudentProgram.CERT, Quarter.RQ, 2024);
		system.addStudent("Ada", "Lovelace", StudentType.GRAD, StudentProgram.CERT, Quarter.FQ, 2024);
		system.addStudent("Grace", "Hopper", StudentType.GRAD, StudentProgram.MSCS, Quarter.WQ, 2024);
		system.addStudent("Marie", "Curie", StudentType.NONMAT_GRAD, StudentProgram.UNDECIDED, Quarter.FQ, 2023);
		system.addStudent("Alan", "Turing", StudentType.GRAD, StudentProgram.CERT, Quarter.RQ, 2023);
		system.addStudent("Katherine", "Johnson", StudentType.GRAD, StudentProgram.MSCS, Quarter.FQ, 2024);
	}

	private static void populateFaculty(RegistrationSystem system) throws DuplicatePersonException {
		system.addFaculty("Wan", "Bae", FacultyType.PROF, Building.SINE, "290-01", "baew@seattleu.edu");
		system.addFaculty("Brian", "Daugherty", FacultyType.ASSTPROF, Building.SINE, "210-01", "bdaugherty@seattleu.edu");
		system.addFaculty("Beatriz", "Diaz Acosta", FacultyType.ASSTTEACHPROF, Building.SINE, "310", "bdiazacosta@seattleu.edu");
		system.addFaculty("Adair", "Dingle", FacultyType.PROF, Building.SINE, "302-11", "dingle@seattleu.edu");
		system.addFaculty("Steven", "Hanks", FacultyType.PROF, Building.SINE, "302-03", "hankssteven@seattleu.edu");
		system.addFaculty("Pejman", "Khadivi", FacultyType.ASSTPROF, Building.SINE, "290-09", "khadivip@seattleu.edu");
		system.addFaculty("Michael", "Koenig", FacultyType.ASSTTEACHPROF, Building.SINE, "210-01", "koenigm@seattleu.edu");
		system.addFaculty("Eric", "Larson", FacultyType.PROF, Building.SINE, "302-05", "elarson@seattleu.edu");
		system.addFaculty("Lin", "Li", FacultyType.ASSTPROF, Building.BANN, "306", "lil@seattleu.edu");
		system.addFaculty("Kevin", "Lundeen", FacultyType.ASSOCTEACHPROF, Building.BANN, "304", "lundeenk@seattleu.edu");
		system.addFaculty("Michael", "McKee", FacultyType.ASSTTEACHPROF, Building.SINE, "220-05", "mckeem@seattleu.edu");
		system.addFaculty("Lisa", "Milkowski", FacultyType.ASSOCTEACHPROF, Building.SINE, "210-02", "lmilkowski@seattleu.edu");
		system.addFaculty("James", "Obare", FacultyType.ASSTTEACHPROF, Building.SINE, "220-03", "obarej@seattleu.edu");
		system.addFaculty("Sheila", "Oh", FacultyType.TEACHPROF, Building.SINE, "302-07", "ohsh@seattleu.edu");
		system.addFaculty("Susan", "Reeder", FacultyType.TEACHPROF, Building.SINE, "290-05", "sreeder@seattleu.edu");
		system.addFaculty("Jason", "Wong", FacultyType.INSTRUCT, Building.SINE, "290-11", "wongja@seattleu.edu");
		system.addFaculty("Yingwu", "Zhu", FacultyType.PROF, Building.SINE, "302-09","zhuy@seattleu.edu");
	}

	private static void populateSubjects(RegistrationSystem system) throws DuplicateSubjectException {
		system.addSubject(SubjectCode.BIOL, "Biology");
		system.addSubject(SubjectCode.CHEM, "Chemistry");
		system.addSubject(SubjectCode.CPSC, "Computer Science");
		system.addSubject(SubjectCode.MATH, "Mathematics");
	}

	private static void populateCourses(RegistrationSystem system) throws DuplicateCourseException {
		system.addCourse(SubjectCode.CPSC, 1420, "Programming and Problem Solving I", 5);
		system.addCourse(SubjectCode.CPSC, 1430, "Programming and Problem Solving II", 5);
		system.addCourse(SubjectCode.CPSC, 2430, "Data Structures", 5);
		system.addCourse(SubjectCode.CPSC, 2500, "Computer Organization", 5);
		system.addCourse(SubjectCode.CPSC, 2600, "Foundations of Computer Science", 5);
		system.addCourse(SubjectCode.CPSC, 3200, "Object-Oriented Development", 5);
		system.addCourse(SubjectCode.CPSC, 3300, "Fundamentals of Databases", 5);
		system.addCourse(SubjectCode.CPSC, 3400, "Languages and Computation", 5);
		system.addCourse(SubjectCode.CPSC, 3500, "Computing Systems", 5);
		system.addCourse(SubjectCode.CPSC, 4100, "Design and Analysis of Algorithms", 5);
		system.addCourse(SubjectCode.CPSC, 5001, "Programming Boot Camp I", 3);
		system.addCourse(SubjectCode.CPSC, 5002, "Programming Boot Camp II", 3);
		system.addCourse(SubjectCode.CPSC, 5005, "Data Structures", 3);
		system.addCourse(SubjectCode.CPSC, 5011, "Object-Oriented Concepts", 3);
		system.addCourse(SubjectCode.CPSC, 5021, "Database Systems", 3);
		system.addCourse(SubjectCode.CPSC, 5031, "Algorithms", 3);
		system.addCourse(SubjectCode.CPSC, 5041, "Computing Systems Principles I", 3);
		system.addCourse(SubjectCode.CPSC, 5042, "Computing Systems Principles II", 3);
		system.addCourse(SubjectCode.CPSC, 5070, "Programming for Data Science", 3);
		system.addCourse(SubjectCode.CPSC, 5071, "Data Management for Data Sci", 3);
		system.addCourse(SubjectCode.CPSC, 5075, "Data Arch for Analytics", 3);
		system.addCourse(SubjectCode.CPSC, 5080, "AI for Business & Analytics", 3);
		system.addCourse(SubjectCode.CPSC, 5110, "Fundamentals of Software Engineering", 5);
		system.addCourse(SubjectCode.CPSC, 5120, "Software Project Management", 3);
		system.addCourse(SubjectCode.CPSC, 5200, "Software Architecture and Design", 5);
		system.addCourse(SubjectCode.CPSC, 5210, "Software Testing and Debugging", 5);
		system.addCourse(SubjectCode.CPSC, 5220, "User Experience Design", 5);
		system.addCourse(SubjectCode.CPSC, 5240, "Software as a Service", 5);
		system.addCourse(SubjectCode.CPSC, 5250, "Mobile Software Development", 5);
		system.addCourse(SubjectCode.CPSC, 5260, "Refactoring & Software Design", 5);
		system.addCourse(SubjectCode.CPSC, 5270, "Graphics/Game Project", 5);
		system.addCourse(SubjectCode.CPSC, 5300, "Physical Database Design & Optimization", 5);
		system.addCourse(SubjectCode.CPSC, 5305, "Introduction to Data Science", 3);
		system.addCourse(SubjectCode.CPSC, 5310, "Machine Learning", 5);
		system.addCourse(SubjectCode.CPSC, 5320, "Visual Analytics", 3);
		system.addCourse(SubjectCode.CPSC, 5330, "Big Data Analytics", 3);
		system.addCourse(SubjectCode.CPSC, 5400, "Complier Principles and Techniques", 5);
		system.addCourse(SubjectCode.CPSC, 5510, "Computer Networks", 5);
		system.addCourse(SubjectCode.CPSC, 5520, "Distributed Systems", 5);
		system.addCourse(SubjectCode.CPSC, 5600, "Parallel Computing", 5);
		system.addCourse(SubjectCode.CPSC, 5610, "Artificial Intelligence", 5);
		system.addCourse(SubjectCode.CPSC, 5700, "Computer Graphics", 5);
		system.addCourse(SubjectCode.CPSC, 5800, "Ethics and Professional Issues in Computing", 2);
		system.addCourse(SubjectCode.CPSC, 5810, "SW Engineering Project I", 4);
		system.addCourse(SubjectCode.CPSC, 5820, "SW Engineering Project II", 4);
		system.addCourse(SubjectCode.CPSC, 5830, "Data Science Capstone Project", 5);
		system.addCourse(SubjectCode.CPSC, 5890, "Seminar", 3);
	}

	private static void populatePrerequisites(RegistrationSystem system) throws CourseNotFoundException {
		system.addPrerequisite(SubjectCode.CPSC, 1430, SubjectCode.CPSC, 1420);
		system.addPrerequisite(SubjectCode.CPSC, 2430, SubjectCode.CPSC, 1430);
		system.addPrerequisite(SubjectCode.CPSC, 2500, SubjectCode.CPSC, 1430);
		system.addPrerequisite(SubjectCode.CPSC, 2600, SubjectCode.CPSC, 1430);
		system.addPrerequisite(SubjectCode.CPSC, 3200, SubjectCode.CPSC, 2430);
		system.addPrerequisite(SubjectCode.CPSC, 3300, SubjectCode.CPSC, 2430);
		system.addPrerequisite(SubjectCode.CPSC, 3400, SubjectCode.CPSC, 2430);
		system.addPrerequisite(SubjectCode.CPSC, 3400, SubjectCode.CPSC, 2600);
		system.addPrerequisite(SubjectCode.CPSC, 3500, SubjectCode.CPSC, 2430);
		system.addPrerequisite(SubjectCode.CPSC, 4100, SubjectCode.CPSC, 2430);
		system.addPrerequisite(SubjectCode.CPSC, 4100, SubjectCode.CPSC, 2600);
		system.addPrerequisite(SubjectCode.CPSC, 5002, SubjectCode.CPSC, 5001);
		system.addPrerequisite(SubjectCode.CPSC, 5005, SubjectCode.CPSC, 5002);
		system.addPrerequisite(SubjectCode.CPSC, 5011, SubjectCode.CPSC, 5005);
		system.addPrerequisite(SubjectCode.CPSC, 5031, SubjectCode.CPSC, 5005);
		system.addPrerequisite(SubjectCode.CPSC, 5041, SubjectCode.CPSC, 5005);
		system.addPrerequisite(SubjectCode.CPSC, 5042, SubjectCode.CPSC, 5041);
		system.addPrerequisite(SubjectCode.CPSC, 5120, SubjectCode.CPSC, 5110);
		system.addPrerequisite(SubjectCode.CPSC, 5200, SubjectCode.CPSC, 5110);
		system.addPrerequisite(SubjectCode.CPSC, 5210, SubjectCode.CPSC, 5110);
		system.addPrerequisite(SubjectCode.CPSC, 5220, SubjectCode.CPSC, 5110);
		system.addPrerequisite(SubjectCode.CPSC, 5240, SubjectCode.CPSC, 5110);
		system.addPrerequisite(SubjectCode.CPSC, 5250, SubjectCode.CPSC, 5110);
		system.addPrerequisite(SubjectCode.CPSC, 5320, SubjectCode.CPSC, 5305);
		system.addPrerequisite(SubjectCode.CPSC, 5330, SubjectCode.CPSC, 5305);
		system.addPrerequisite(SubjectCode.CPSC, 5810, SubjectCode.CPSC, 5200);
		system.addPrerequisite(SubjectCode.CPSC, 5810, SubjectCode.CPSC, 5210);
		system.addPrerequisite(SubjectCode.CPSC, 5810, SubjectCode.CPSC, 5120);
		system.addPrerequisite(SubjectCode.CPSC, 5820, SubjectCode.CPSC, 5810);
		system.addPrerequisite(SubjectCode.CPSC, 5830, SubjectCode.CPSC, 5310);
		system.addPrerequisite(SubjectCode.CPSC, 5830, SubjectCode.CPSC, 5320);
		system.addPrerequisite(SubjectCode.CPSC, 5830, SubjectCode.CPSC, 5330);
		system.addPrerequisite(SubjectCode.CPSC, 5830, SubjectCode.CPSC, 5610);
	}

	private static void populateSections(RegistrationSystem system) throws CourseNotFoundException, PersonNotFoundException, DuplicateSectionException, SectionNotFoundException {
		system.addSection(SubjectCode.CPSC, 2430, 1, "Susan", "Reeder", Quarter.FQ, 2024, 15, Building.PIGT, "207");
		system.addSection(SubjectCode.CPSC, 2430, 2, "Adair", "Dingle", Quarter.FQ, 2024, 30, Building.PIGT, "207");
		system.addSection(SubjectCode.CPSC, 2600, 1, "Sheila", "Oh", Quarter.FQ, 2024, 30, Building.LEML, "122");
		system.addSection(SubjectCode.CPSC, 4100, 1, "Yingwu", "Zhu", Quarter.FQ, 2024, 34, Building.PIGT, "102");
		system.addSection(SubjectCode.CPSC, 5011, 1, "Steven", "Hanks", Quarter.FQ, 2024, 20, Building.ADMN, "203");
		system.addSection(SubjectCode.CPSC, 5011, 2, "Sheila", "Oh", Quarter.FQ, 2024, 30, Building.LEML, "122");
		system.addSection(SubjectCode.CPSC, 5021, 1, "Michael", "McKee", Quarter.FQ, 2024, 36, Building.BANN, "401");
		system.addSection(SubjectCode.CPSC, 5021, 2, "Michael", "McKee", Quarter.FQ, 2024, 30, Building.LEML, "122");
		system.addSection(SubjectCode.CPSC, 5510, 1, "Kevin", "Lundeen", Quarter.FQ, 2024, 30, Building.BANN, "304");
		system.addSection(SubjectCode.CPSC, 5600, 2, "Kevin", "Lundeen", Quarter.FQ, 2024, 20, Building.BANN, "402");
	}

}
