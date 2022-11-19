package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author your name, id, email here
 * @version 2022-10-04
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {

	int average = 0;

	for (int i = 0; i < foods.size(); i++) {
	    average += foods.get(i).getCalories();
	}
	
	average = average / foods.size();

	return average;
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

	ArrayList<Food> averageFoods = new ArrayList<Food>();
	int length = foods.size();
	for (Food food : foods) {
	    if (food.getOrigin() == origin) {
		averageFoods.add(food);
	    }
	}
	int average = averageCalories(averageFoods);
	return average;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

	ArrayList<Food> Originfoods = new ArrayList<Food>();
	int length = foods.size();
	for (int i = 0; i < length; i++) {
	    if (foods.get(i).getOrigin() == origin) {
		Originfoods.add(foods.get(i));
	    }
	}

	return Originfoods;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {

	System.out.println("Name: ");
	String name = keyboard.nextLine();
	System.out.println(Food.originsMenu());
	System.out.println("Origin: ");
	int origin = Integer.parseInt(keyboard.nextLine());
	System.out.println("Vegetarian (Y/N): ");
	String vegYN = keyboard.nextLine();
	System.out.println("Calories: ");
	int calories = Integer.parseInt(keyboard.nextLine());

	Boolean isVegetarian = true;
	if (vegYN.equals("N")) {
	    isVegetarian = false;
	}

	Food food = new Food(name, origin, isVegetarian, calories);
	return food;
    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

	ArrayList<Food> VegFoods = new ArrayList<Food>();
	int length = foods.size();
	for (int i = 0; i < length; i++) {
	    if (foods.get(i).isVegetarian()) {
		VegFoods.add(foods.get(i));
	    }
	}

	return VegFoods;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {

	String[] result = line.split("\\|");
	String name = result[0];
	int origin = Integer.parseInt(result[1]);
	Boolean isVegetarian = Boolean.parseBoolean(result[2]);
	int calories = Integer.parseInt(result[3]);
	Food food = new Food(name, origin, isVegetarian, calories);

	return food;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn) {

	ArrayList<Food> foods = new ArrayList<Food>();
	String line;
	while (fileIn.hasNext()) {
	    line = fileIn.nextLine();
	    foods.add(readFood(line));
	}

	return foods;
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {

	ArrayList<Food> SearchFoods = new ArrayList<Food>();
	int length = foods.size();
	if (origin == -1) {
	    if (maxCalories == 0) {
		if (isVegetarian == false) {
		    for (int i = 0; i < length; i++) {
			SearchFoods.add(foods.get(i));
		    }
		} else {
		    for (int i = 0; i < length; i++) {
			if (foods.get(i).isVegetarian()) {
			    SearchFoods.add(foods.get(i));
			}
		    }
		}
	    } else if (isVegetarian == false) {
		for (int i = 0; i < length; i++) {
		    if (foods.get(i).getCalories() == maxCalories) {
			SearchFoods.add(foods.get(i));
		    }
		}
	    } else if (isVegetarian == true) {
		for (int i = 0; i < length; i++) {
		    if (foods.get(i).getCalories() == maxCalories) {
			if (foods.get(i).isVegetarian()) {
			    SearchFoods.add(foods.get(i));
			}
		    }
		}
	    }
	} else if (maxCalories == 0) {
	    if (isVegetarian == false) {
		for (int i = 0; i < length; i++) {
		    SearchFoods.add(foods.get(i));
		}
	    } else {
		for (int i = 0; i < length; i++) {
		    if (foods.get(i).isVegetarian()) {
			SearchFoods.add(foods.get(i));
		    }
		}
	    }
	} else if (isVegetarian == false) {
	    for (int i = 0; i < length; i++) {
		if (foods.get(i).getCalories() == maxCalories) {
		    SearchFoods.add(foods.get(i));
		}
	    }
	} else if (isVegetarian == true) {
	    for (int i = 0; i < length; i++) {
		if (foods.get(i).getCalories() == maxCalories) {
		    if (foods.get(i).isVegetarian()) {
			SearchFoods.add(foods.get(i));
		    }
		}
	    }
	}

	return SearchFoods;
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

	for (int i = 0; i < foods.size(); i++) {
	    foods.get(i).write(ps);
	}
    }
}
