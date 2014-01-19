package helpers;

import javax.swing.JOptionPane;

/**
 * Helper Class for parsing methods
 */
public class Parser {

	/**
	 * Returns true if a string can be parsed to an int. False otherwise.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Validates a given string by checking if it can be parsed to and int and
	 * if it lies between given boundaries. Returns true if so, false otherwise.
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean validateIntRange(String value, int min, int max) {
		boolean result = false;
		int tmp;

		if (Parser.tryParseInt(value)) {
			tmp = Integer.parseInt(value);

			if (tmp >= min && tmp <= max) {
				result = true;
			} else {
				String message = String.format(
						"Error: Please enter a number between %d and %d", min,
						max);

				JOptionPane.showMessageDialog(null, message, "Error Massage",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error: Please enter a number",
					"Error Massage", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
}
