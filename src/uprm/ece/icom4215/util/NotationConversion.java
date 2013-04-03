package uprm.ece.icom4215.util;

public class NotationConversion {

	public static String hexToBinary(String hexString){
		
		int number = Integer.parseInt(hexString, 16);
		return Integer.toBinaryString(number);
			
	}
	
	public static String hexTo8BitBinary(int hexInt){
		return hexTo8BitBinary(hexInt+"");
	}
	
	public static String hexTo8BitBinary(String hexString){
		String binary = hexToBinary(hexString);
		while (binary.length()<8){
			binary="0"+binary;
		}
		return binary;
	}
	
	public static String decimalTo8BitBinary(int decimal){
		return decimalTo8BitBinary(decimal+"");
	}
	
	public static String decimalTo8BitBinary(String decimal){
		String binary = Integer.toBinaryString(Integer.parseInt(decimal));
		while (binary.length()<8){
			binary="0"+binary;
		}
		return binary;
	}
	
	public static String binaryToDecimal(String binary){
		return Integer.parseInt(binary, 2)+"";
	}
	
	public static void main(String[] args){
		System.out.println(hexToBinary("70"));
		System.out.println(hexTo8BitBinary("70"));
		System.out.println(decimalTo8BitBinary("0"));
		System.out.println(binaryToDecimal("11111111"));
		
	}
}
