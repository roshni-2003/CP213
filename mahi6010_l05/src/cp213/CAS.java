package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author
 * @version 2022-02-25
 */
public class CAS extends Professor {
    
    private String term = null;

    public CAS(final String lastName, final String firstName, final String department, final String term) {
	super(lastName, firstName, department);
	this.term = term;
    }

    public String getTerm() {
        return term;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of IA data.
     */
    @Override
    public String toString() {

	return (super.toString() + "\nTerm: " + this.term);
    }
    

}
