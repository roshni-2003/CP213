package cp213;

import java.io.*;
import java.util.Scanner;

/**
 * @author Your name and id here
 * @version 2022-09-23
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	boolean alldigs = true;
	int n = 0;
	String nums = "1234567890";
	
	for (int i=0; i<str.length(); i++) {
	    if (nums.contains(str.subSequence(i, i+1))){
		n++;
	    }
	    else {
		alldigs = false;
	    }
	}

	return alldigs;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {
	String first = sn.substring(0,3);
	String first_must = "SN/";
	String second = sn.substring(3,7);
	String third = sn.substring(7,8);
	String third_must = "-";
	String fourth = sn.substring(8);
	boolean result1, result2, result3, result4, final_result;
	
	if(first.equals(first_must)) {
	    result1 = true;
	}
	else {
	    result1 = false;
	}
	if (SerialNumber.allDigits(second) == true && second.length() == 4) {
	    result2 = true;
	}
	else {
	    result2 = false;
	}
	if (third.equals(third_must)) {
	    result3 = true;
	}
	else {
	    result3 = false;
	}
	if (SerialNumber.allDigits(fourth) == true && fourth.length() == 3) {
	    result4 = true;
	}
	else {
	    result4 = false;
	}
	
	if (result1 == true && result2 == true && result3 == true && result4 == true) {
	    final_result = true;
	}
	else {
	    final_result = false;
	}
	

	return final_result;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {
	
	while (fileIn.hasNextLine()) {
	    String line = fileIn.nextLine();
	    if (SerialNumber.validSn(line) == true) {
		goodSns.append(line + "\n");
	    }
	    else {
		badSns.append(line + "\n");
	    }
	}
	fileIn.close();
	goodSns.close();
	badSns.close();
	return;
    }

}
