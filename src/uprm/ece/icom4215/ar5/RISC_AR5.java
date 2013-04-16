package uprm.ece.icom4215.ar5;

import uprm.ece.icom4215.components.InstructionSet;
import uprm.ece.icom4215.components.Memory;
import uprm.ece.icom4215.components.Registers;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;
import uprm.ece.icom4215.exceptions.UnsupportedInstructionException;

/**
 * Represents the RISC_AR5 microprocessor. Usage of this class
 * is done in a static way. Simply call the init() method, load
 * the memory and start the fetch-execute cycle. 
 *
 */
public class RISC_AR5 {
	public static Memory memory;
	public static Registers registers;
	public static InstructionSet instructions;
	private static boolean stop;
	
	/**
	 * Statically initialize the microprocessor so that
	 * it can be used freely by all components.
	 */
	public static void init(){
		memory = new Memory();
		registers = new Registers();
		instructions = new InstructionSet();
		stop = false;
	}
	
	/**
	 * Sets the stop condition to true, indicating that
	 * the main program has finished all instructions.
	 */
	public static void stop(){
		stop = true;
	}
	
	/**
	 * Returns a status detailing whether the main
	 * program is stopped or still running.
	 * @return
	 */
	public static boolean isStopped(){
		return stop;
	}
	
	/**
	 * Executes the next instruction according to the program
	 * counter.
	 */
	public static void step(){
		registers.setIR();
		String IR = registers.getIR();
		String opcode = IR.substring(0,5);
		try {
			instructions.next(IR);
			//Don't increment if branch.
			if(!opcode.equals("10000")&&!opcode.equals("10001")&&!opcode.equals("10010")
					&&!opcode.equals("10011"))
			registers.incrementPC();
			
		} catch (InvalidAddressValueException e) {
			// Instruction is not a 16 bit word.
			e.printStackTrace();
		} catch (UnsupportedInstructionException e) {
			// Opcode does not belong to a specified operation.
			e.printStackTrace();
		}
	}
	
}
