package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author your name here
 * @version 2022-01-17
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student( int i, String s, String f, LocalDate d) {

	forename = f;
	birthDate = d;
	id = i;
	surname = s;

	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "";

	string += String.format("Name: %11s, %4s \nID: %16s \nBirthdate: %10s", surname, forename, id, birthDate);

	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
	int result = 0;

	result = surname.compareTo(target.surname);
	if (result == 0) {
	    result = target.forename.compareTo(forename);
	    if (result==0) {
		    if (target.id == id) {
			result = 0;
		    }
		    else if (target.id < id) {
			result = 1;
		    }
		    else {
			result = -1;
		    }
		}
	}
	

	return result;
    }


    public void setForename(String forename) {
        this.forename = forename;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getForename() {
        return forename;
    }
    public String getSurname() {
        return surname;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public int getId() {
        return id;
    }


}
