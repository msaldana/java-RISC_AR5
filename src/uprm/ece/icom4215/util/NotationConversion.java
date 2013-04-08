package uprm.ece.icom4215.util;

/**
 * Utility class to facilitate conversions from
 * hexadecimal to binary, binary to decimal, and decimal to 
 * binary. Fills more significant bits to 0 in order to declare
 * 8-bit words in the form of strings.
 *
 */
public class NotationConversion {
	
	
	/**
	 * Returns a binary string representation of the input hex number.
	 * @param hexString
	 * @return
	 */
	public static String hexToBinary(String hexString){
		
		int number = Integer.parseInt(hexString, 16);
		return Integer.toBinaryString(number);
			
	}
	
	/**
	 * Returns an 8-bit word representation of the given hex number. This assumes that
	 * the number does not consume more than 8-bits. If it does, it simply returns
	 * the string representation of the binary conversion.
	 * @param decimal
	 * @return
	 */
	public static String hexTo8BitBinary(int hexInt){
		return hexTo8BitBinary(hexInt+"");
	}
	
	/**
	 * Returns an 8-bit word representation of the given hex number. This assumes that
	 * the number does not consume more than 8-bits. If it does, it simply returns
	 * the string representation of the binary conversion.
	 * @param decimal
	 * @return
	 */
	public static String hexTo8BitBinary(String hexString){
		String binary = hexToBinary(hexString);
		while (binary.length()<8){
			binary="0"+binary;
		}
		return binary;
	}
	
	/**
	 * Returns an 8-bit word representation of the given decimal. This assumes that
	 * the decimal does not consume more than 8-bits. If it does, it simply returns
	 * the string representation of the binary conversion.
	 * @param decimal
	 * @return
	 */
	public static String decimalTo8BitBinary(int decimal){
		return decimalTo8BitBinary(decimal+"");
	}
	
	/**
	 * Returns an 8-bit word representation of the given decimal. This assumes that
	 * the decimal does not consume more than 8-bits. If it does, it simply returns
	 * the string representation of the binary conversion.
	 * @param decimal
	 * @return
	 */
	public static String decimalTo8BitBinary(String decimal){
		String binary = Integer.toBinaryString(Integer.parseInt(decimal));
		while (binary.length()<8){
			binary="0"+binary;
		}
		return binary;
	}
	
	/**
	 * Return a binary string representation of the input decimal.
	 */
	public static String binaryToDecimal(String binary){
		return Integer.parseInt(binary, 2)+"";
	}
	

}
