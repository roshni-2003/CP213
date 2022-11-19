package cp213;

import java.io.PrintStream;

/**
 * Food class definition.
 *
 * @author your name, id, email here
 * @version 2022-10-04
 */
public class Food implements Comparable<Food> {

    // Constants
    public static final String ORIGINS[] = { "Canadian", "Chinese", "Indian", "Ethiopian", "Mexican", "Greek",
	    "Japanese", "Italian", "Moroccan", "Scottish", "Columbian", "English" };

    /**
     * Creates a string of food origins in the format:
     *
     * <pre>
    Origins
    0 Canadian
    1 Chinese
    ...
    11 English
     * </pre>
     *
     * @return A formatted numbered string of valid food origins.
     */
    public static String originsMenu() {

	String foodStr = "Origins\n";
	for (int i = 0; i < ORIGINS.length; i++) {
	    foodStr += i + " " + ORIGINS[i] + "\n";
	}

	return foodStr;
    }

    // Attributes
    private String name = null;
    private int origin = 0;
    private boolean isVegetarian = false;
    private int calories = 0;

    /**
     * Food constructor.
     *
     * @param name         food name
     * @param origin       food origin code
     * @param isVegetarian whether food is vegetarian
     * @param calories     caloric content of food
     */
    public Food(final String n, final int o, final boolean v, final int c) {

	name  = n;
	origin = o;
	isVegetarian = v;
	calories = c;

	return;
    }

    /*
     * (non-Javadoc) Compares this food against another food.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    /**
     * Foods are compared by name, then by origin if the names match. Must ignore
     * case.
     */
    @Override
    public int compareTo(final Food target) {

	int result = 0;

	result = name.compareTo(target.name);
	if (result == 0) {
	    if (target.origin == origin) {
		result = 0;
	    }
	    else if (target.origin < origin) {
		result = 1;
	    }
	    else {
		result = -1;
	    }
	}
	

	return result;
    }

    /**
     * Getter for calories attribute.
     *
     * @return calories
     */
    public int getCalories() {

	return calories;

     }

    /**
     * Getter for name attribute.
     *
     * @return name
     */
    public String getName() {

	return name;
    }

    /**
     * Getter for origin attribute.
     *
     * @return origin
     */
    public int getOrigin() {

	return origin;
    }

    /**
     * Getter for string version of origin attribute.
     *
     * @return string version of origin
     */
    public String getOriginString() {

	return ORIGINS[origin];
    }

    /**
     * Getter for isVegetarian attribute.
     *
     * @return isVegetarian
     */
    public boolean isVegetarian() {

	return isVegetarian;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object//toString() Creates a formatted string of food data.
     */
    /**
     * Returns a string version of a Food object in the form:
     *
     * <pre>
    Name:       name
    Origin:     origin string
    Vegetarian: true/false
    Calories:   calories
     * </pre>
     */
    @Override
    public String toString() {

	String string = String.format("%-11s %s\n%-11s %s\n%-11s %s\n%-11s %s" , "Name:", name, "Origin:", ORIGINS[origin], "Vegetarian:", isVegetarian, "Calories:", calories);

	return string;
    }

    /**
     * Writes a single line of food data to an open PrintStream. The contents of
     * food are written as a string in the format name|origin|isVegetarian|calories
     * to ps.
     *
     * @param ps The PrintStream to write to.
     */
    public void write(final PrintStream ps) {

	String food = String.format("%s|%s|%s|%s", name, origin, isVegetarian, calories);
	ps.println(food);

	return;
    }

}
