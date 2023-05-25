package br.com.engel.utils;

import br.com.engel.exceptions.UnsupportedMathOperationException;

public class MathUtils {

	private static final String NON_NUMERIC_VALUE = "Please set a numeric value!";
	private static final String DIVISION_BY_ZERO = "Can't divide by zero!";
	
	public static Double sum(String n1, String n2) {
		return convertToDouble(n1) + convertToDouble(n2);
	}

	public static Double subtract(String n1, String n2) {
		return convertToDouble(n1) - convertToDouble(n2);
	}

	public static Double multiply(String n1, String n2) {
		return convertToDouble(n1) * convertToDouble(n2);
	}

	public static Double divide(String n1, String n2) {
		if(convertToDouble(n2) == 0)
			throw new UnsupportedMathOperationException(DIVISION_BY_ZERO);
		return convertToDouble(n1) / convertToDouble(n2);
	}

	public static Double average(String n1, String n2) {
		return sum(n1, n2) / 2;
	}

	public static Double sqrt(String n1) {
		return Math.sqrt(convertToDouble(n1));
	}

	public static Double convertToDouble(String strNumber) {
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number))
			return Double.parseDouble(number);
		return 0D;
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	public static boolean validateNumbers(String n1, String n2) {
		if (isNumeric(n1) && isNumeric(n2))
			return true;
		throw new UnsupportedMathOperationException(NON_NUMERIC_VALUE);
	}
	
	public static boolean validateNumbers(String n1) {
		if (isNumeric(n1))
			return true;
		throw new UnsupportedMathOperationException(NON_NUMERIC_VALUE);
	}

}
