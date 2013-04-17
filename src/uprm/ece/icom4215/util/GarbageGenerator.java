package uprm.ece.icom4215.util;

import java.util.Random;

/**
 * Utility method to load the processor's components with
 * random values.
 *
 */
public class GarbageGenerator {

	/**
	 * Creates a random 8-bit word. This is a utility method
	 * to simulate that the memory is loaded with unknown 
	 * "garbage values" before the actual program memory is 
	 * loaded and instructions are executed.
	 */
	public static String generate8BitWord(){
		Random r = new Random();
		return NotationConversion.decimalTo8BitBinary(r.nextInt(255));
	}
}
