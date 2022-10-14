package cp213;

/**
 * @author Your name and id here
 * @version 2022-09-23
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	if(string.isEmpty())
	        return true;
	     
	    String str = string.toLowerCase();
	    
	    str = str.replaceAll("[^a-zA-Z0-9]", "");
	     
	    StringBuilder revstr = new StringBuilder(str);
	    revstr.reverse();
	    String rstr = revstr.toString();
	     
	    if(str.equals(rstr))
	        return true;
	     
	    return false;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	boolean val = true;
	String start = "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	if (name.isEmpty()) {
	    val = false;
	}
	if (!(start.contains(name.subSequence(0, 1)))) {
	    val = false;
	}
	if (name.length() < 2 && (name.subSequence(0, 1)).equals("_")) {
	    val = false;
	}
	for (int i = 1; i < name.length(); i++) {
	    if (!Character.isJavaIdentifierPart(name.charAt(i))) {
		val = false;
	    }
	}
	return val;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	int len = word.length();
	int index = -1;
	for (int i = 0; i < len; i++) {
	    if (VOWELS.contains(word.subSequence(i, i + 1))) {
		index = i;
		break;
	    }
	}
	if (index == -1)
	    return "-1";
	
	String piglat = word.substring(index) + word.substring(0, index) + "ay";
	piglat = piglat.toLowerCase();
	
	if (Character.isUpperCase(word.charAt(0))) {
	    piglat = Character.toUpperCase(piglat.charAt(0)) + piglat.substring(1);
	}
	return piglat;
    }

}
