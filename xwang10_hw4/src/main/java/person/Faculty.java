package person;

import enums.Building;
import enums.FacultyType;
import enums.PersonStatus;

/**
 * The Faculty class holds information about a faculty member.
 * 
 * - first name: first name of the student
 * - last name: last name of the student
 * - suid: Seattle U identification number
 * - status: the status of the faculty (see PersonStatus enum)
 * - faculty type: the type of faculty (see FacultyType enum)
 * - office: includes building (i.e. BANN) and room number (i.e 504)
 * - email: the school (i.e. SU) email address
 * 
 * @author Xiaoyu Wang
 */
public class Faculty {

	/**
	 * Constructor.
	 * @param firstName	The first name of the faculty
	 * @param lastName	The last name of the faculty
	 */
	public Faculty(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		status = PersonStatus.ACTIVE;
	}

	// first name, last name, SUID, status, faculty type, office (see Building enum), email
	private final String firstName;
	private final String lastName;
	private final PersonStatus status;
	private FacultyType facultyType;
	private String office;
	private String email;
	private int suid;

	// Getters and setters

	/**
	 * The getFirstName method returns the faculty member's first name.
	 * @return The first name.
	 */
	public String getFirstName() { return firstName; }

	/**
	 * The getLastName method returns the faculty member's last name.
	 * @return The last name.
	 */
	public String getLastName() { return lastName; }

	/**
	 * The setEmail method sets the faculty member's email.
	 * @param email Faculty member's email.
	 */
	public void setEmail(String email) { this.email = email; }

	/**
	 * The setFacultyType sets the faculty member's type.
	 * @param facultyType The faculty member's type.
	 */
	public void setFacultyType(FacultyType facultyType) { this.facultyType = facultyType; }

	/**
	 * The setOffice method sets the office of a faculty member.
	 * @param bldg Building.
	 * @param room Room number.
	 */
	public void setOffice(Building bldg, String room) { office = bldg + " " + room; }

	/**
	 * The setSuid method sets the faculty member's suid.
	 * @param suid The SUID.
	 */
	public void setSuid(int suid) { this.suid = suid; };

	/**
	 * The getSuid method returns the suid.
	 * @return The SUID.
	 */
	public int getSuid() { return suid; }


	@Override
	public String toString() {
		return String.format("Faculty: Name=%s %s, SUID=%s, Email=%s, Status=%s, Type=%s, Office=%s",
				firstName, lastName, getSuid(), email, status, facultyType, office);
	}
}
