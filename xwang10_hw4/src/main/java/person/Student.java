package person;

import enums.*;

/**
 * The Student class holds information about a student. 
 * 
 * - first name: first name of the student
 * - last name: last name of the student
 * - suid: Seattle U identification number
 * - status: the status of the student (see PersonStatus enum)
 * - student type: a student can only be assigned a single student type
 *   (see StudentType enum)
 * - student program: a student can only be assigned to a single program at 
 *   a point of time, but can switch from one program to another (i.e. when 
 *   they've graduated from a program (see StudentProgram enum)
 * - student year: only relevant for undergraduates (see StudentYear enum)
 * - faculty advisor: students are assigned a faculty advisor, but may switch 
 *   advisors (i.e. faculty leaves or on sabbatical); may not be assigned an
 *   advisor for a period of time when first enrolled as a student
 * - start term: associated with the quarter and year a student starts a
 *   particular program; for example, a single student may start the CERT in 
 *   RQ17 and then continue the MSCS in FQ18 (see Quarter enum)
 * - email: the school (i.e. SU) email address
 * 
 * @author Xiaoyu Wang
 */
public class Student {

	/**
	 * Constructor.
	 * @param firstName	The first name of the student
	 * @param lastName	The last name of the student
	 */
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = PersonStatus.ACTIVE;
	}

	// first name, last name, SUID, status, student type, student program,
	// start quarter/year, faculty advisor, email
	// if undergrad, also add student year (default to freshman)
	// Note -- do not need to worry about registering students for courses;
	//         will implement this functionality during HW5
	private String firstName;
	private String lastName;
	private int suid;
	private PersonStatus status;
	private StudentType studentType;
	private StudentProgram studentProgram;
	private StudentYear studentYear;
	private String term;
	private String email;
	private Faculty facultyAdvisor;
	private int year;

	// Getters and setters

	/**
	 * The getFirstName method returns the first name of a student.
	 * @return The first name.
	 */
	public String getFirstName() { return firstName; }

	/**
	 * The getLastName method returns the last name of a student.
	 * @return The last name.
	 */
	public String getLastName() { return lastName; }

	/**
	 * The getSuid method returns the suid of a student.
	 * @return The suid.
	 */
	public int getSuid() { return suid; }

	/**
	 * The setStudentType method sets a student's type.
	 * @param studentType The student type.
	 */
	public void setStudentType( StudentType studentType) { this.studentType = studentType; setStudentYear();}

	/**
	 * The setStudentProgram method sets the student's program.
	 * @param studentProgram The student program.
	 */
	public void setStudentProgram( StudentProgram studentProgram) { this.studentProgram = studentProgram; }

	/**
	 * The setTerm method sets the student's term.
	 * @param quarter The quarter.
	 * @param year The year.
	 */
	public void setTerm(Quarter quarter, int year) {
		this.term = quarter.toString() + " " + year;
		this.year = year;
		setStudentYear();
	}

	/**
	 * The setStudentYear sets the student year.
	 */
	public void setStudentYear() {
		if(studentType == StudentType.UNDERGRAD) {
			switch (year) {
				case 2024:
					studentYear = StudentYear.FRESHMAN;
					break;
				case 2023:
					studentYear = StudentYear.SOPHOMORE;
					break;
				case 2022:
					studentYear = StudentYear.JUNIOR;
					break;
				case 2021:
					studentYear = StudentYear.SENIOR;
					break;
				default:
					studentYear = null;
			}
		} else {
			studentYear = null;
		}
	}

	/**
	 * The setSuid method sets the student's suid.
	 * @param suid The suid.
	 */
	public void setSuid(int suid) { this.suid = suid; }

	/**
	 * The setEmail method sets the student's email.
	 * @param email The student's email.
	 */
	public void setEmail(String email) { this.email = email.toLowerCase() + "@seattleu.edu"; }

	/**
	 * The getEmail method returns the student's email.
	 * @return The student's email.
	 */
	public String getEmail() { return email; }

	/**
	 * The getTerm method returns the student's term.
	 * @return The student's term.
	 */
	public String getTerm() { return term; }

	/**
	 * The setFacultyAdvisor sets the student's advisor.
	 * @param advisor The advisor of a a student.
	 */
	public void setFacultyAdvisor(Faculty advisor) { this.facultyAdvisor = advisor; }

	/**
	 * The getStudentProgram returns a student's program.
	 * @return The student's program.
	 */
	public StudentProgram getStudentProgram() { return studentProgram; }

	@Override
	public String toString() {
		String str = String.format("Student: name=%s %s, SUID=%s, Email=%s, Status=%s, Type=%s, Program=%s, " +
								   "Term=%s", firstName, lastName, getSuid(), getEmail(), status, studentType,
				getStudentProgram(), getTerm());
		if (facultyAdvisor != null) {
			str += ", Advisor=" + facultyAdvisor.getFirstName() + " " + facultyAdvisor.getLastName();
		}
		if (studentYear != null) {
			str += ", Year=" + studentYear;
		}
		return str;
	}

}
