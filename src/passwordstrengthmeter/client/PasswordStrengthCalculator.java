package passwordstrengthmeter.client;

/**
 * Class for methods and constants regarding the calculation of password strength.
 * Uses a system of strength levels (i.e Integers) between 0 and 6, where 0 represents that the password is too short,
 * 1 represents a weak password and up to 6 that represents a super strong password.
 * @author magpe097
 * @version 1.0
 */
public class PasswordStrengthCalculator {
	
	//string containing characters that are considered "special"...
	private static String specialCharacters = "\"╜!@#гд%&{()=}?+^~и*'-.,|$[//>_<\\]з";
	
	/**
	 * Minimum length of the password.
	 */
	public static final int MINIMUM_LENGTH = 5;
	
	/**
	 * If the password length is longer than this then it gains a strength level.
	 */
	public static final int LENGTH_LEVEL_1 = 8;
	
	/**
	 * If the password length is longer than this then it gains another strength level.
	 */
	public static final int LENGTH_LEVEL_2 = 12;
	
	/**
	 * Number of possible strength levels.
	 */
	public static final int STRENGTH_LEVELS = 5;
	
	/**
	 * Calculates the strength of a given password. It uses the following algorithm:
	 * If the password contains a special character then it gains a strength level.
	 * If the password contains a numerical character then it gains a strength level.
	 * If the password contains both upper- and lower case characters then it gains a strength level.
	 * If the password is longer that LENGTH_LEVEL_1 it gains a strength level.
	 * If the password is longer that LENGTH_LEVEL_2 it gains another strength level.
	 * 
	 * @param password Password the check strength on.
	 * @return Integer representing the strength level of the given password. In the range 0-6.
	 */
	public static int getStrength(String password) {
		//if the password is too short return 0.
		if (password.length()<MINIMUM_LENGTH) return 0;
		
		//set variables.
		int strength = 1;
		boolean specialCharacterFound = false, numberFound = false, upperCaseFound = false, lowerCaseFound = false;
		
		//check length levels.
		if (password.length()>=LENGTH_LEVEL_1) {
			if (password.length()>=LENGTH_LEVEL_2) strength++;
			strength++;
		}
		
		//iterate over the password and look for interesting characters.
		for (char c : password.toCharArray()) {	
			if (specialCharacters.indexOf(c)!=-1) 	specialCharacterFound = true;
			if (Character.isDigit(c))				numberFound = true;
			if (Character.isUpperCase(c))			upperCaseFound = true;
			if (Character.isLowerCase(c))			lowerCaseFound = true;
		}
		
		//give strength points.
		if (specialCharacterFound) strength++;
		if (numberFound) strength++;
		if (lowerCaseFound && upperCaseFound) strength++;
		
		//return it like it's hot!
		return strength;
	}
	
}
