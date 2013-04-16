package uprm.ece.icom4215.components;

import java.util.HashMap;

import uprm.ece.icom4215.ar5.RISC_AR5;
import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;
import uprm.ece.icom4215.exceptions.InvalidProgramCounterException;
import uprm.ece.icom4215.util.NotationConversion;

/**
 * Represents the component of the RISC AR5. Registers for this
 * processor are specified to contain 8 addressable units of 8-bit words.
 * The instruction register holds a 16-bit word; while the program counter
 * and accumulator hold single 8-bit words. The status register - containing
 * the flags for zero, carry, negative, and overflow, is defined as a single
 * 4-bit word.
 * Usage: Main constructor initializes all components. 
 * All components can be manually loaded through their set function and retrieved
 * by the get functions. 
 */
public class Registers {

	//Map that will hold 8 general purpose registers, the instruction
	//register, the accumulator, the program counter, and the status 
	//register. Each general purpose register contains 8-bit long words, as
	//does the program counter and accumulator. The instruction register is 
	//composed of a 16 bit-long word and the status register of a 4-bit long 
	//word. Usage: this.registers.get(registerKey) -returns data in specified
	//location.
	HashMap<String, String> registers = new HashMap<String, String>();

	/**
	 * Basic Constructor- Sets the PC to point at the first address of Memory:
	 * address location 0 or 00000000 in binary. The accumulator and status 
	 * registers are also initialized at 0 (for their respective sizes). All
	 * other registers await to be initialized by the processor.
	 */
	public Registers(){
		registers.put("PC", "00000000");
		registers.put("SR", "0000");
		registers.put("IR", "");
		registers.put("Acc", "00000000");
		registers.put("R0", "");
		registers.put("R1", "");
		registers.put("R2", "");
		registers.put("R3", "");
		registers.put("R4", "");
		registers.put("R5", "");
		registers.put("R6", "");
		registers.put("R7", "");
	}

	/**
	 * Returns the 8-bit word representing the program counter.
	 * @return
	 */
	public String getPC(){
		return registers.get("PC");
	}

	/**
	 * Returns the 4-bit word representing the status register.
	 * @return
	 */
	public String getSR(){
		return registers.get("SR");
	}

	/**
	 * Returns the 2 8-bit words representing the instruction register.
	 * @return
	 */
	public String getIR(){
		return registers.get("IR");
	}
	/**
	 * Returns the 8-bit word representing the accumulator.
	 * @return
	 */
	public String getAcc(){
		return registers.get("Acc");
	}
	
	/**
	 * Returns the zero flag.
	 * @return
	 */
	public char getZeroFlag(){
		return getSR().charAt(0);
	}
	
	/**
	 * Returns the carry bit.
	 * @return
	 */
	public char getCarryFlag(){
		return getSR().charAt(1);
	}
	
	/**
	 * Returns the negative bit.
	 * @return
	 */
	public char getNegativeFlag(){
		return getSR().charAt(2);
	}
	
	/**
	 * Returns the overflow bit.
	 * @return
	 */
	public char getOverflowFlag(){
		return getSR().charAt(3);
		
	}
	/**
	 * Returns the 8-bit word representing the general purpose register RX.
	 * @return
	 * @throws InvalidAddressException 
	 */
	public String getRegister(String rf) throws InvalidAddressException{
		//if the number is not in the range 0-7 return error
		if (rf.matches("[0-1]{3}")){
			int registerNumber = Integer.parseInt(rf,2);
			if (registerNumber==0)
				return registers.get("R0");
			else if (registerNumber==1)
				return registers.get("R1");
			else if (registerNumber==2)
				return registers.get("R2");
			else if (registerNumber==3)
				return registers.get("R3");
			else if (registerNumber==4)
				return registers.get("R4");
			else if (registerNumber==5)
				return registers.get("R5");
			else if (registerNumber==6)
				return registers.get("R6");
			else{
				return registers.get("R7");
			}
		}
		else
			throw new InvalidAddressException("The specified address -"+ rf+ "is not" +
					"valid for the RISC AR5. Valid representations for addressing general" +
					"registers include any 3-bit string representation.");
	}


	/**
	 * Sets the status register's zero bit to the desired value. In the RISC
	 * AR5, the most significant bit in the status register (SR) denotes the
	 * value of the zero flag.
	 * @param condition
	 */
	public void setZeroBit(boolean condition){
		String sr = getSR();
		if(condition){
			registers.put("SR", "1"+sr.substring(1));
		}

		else{
			registers.put("SR", "0"+sr.substring(1));
		}
	}

	/**
	 * Sets the status register's carry bit to the desired value. In the 
	 * RISC AR5, the second (left-to-right) bit in the status register (SR)
	 * denotes the value of the carry flag.
	 * @param condition
	 */
	public void setCarryBit(boolean condition){
		String sr = getSR();
		if(condition){
			registers.put("SR", sr.charAt(0)+"1"+sr.substring(2));
		}

		else{
			registers.put("SR", sr.charAt(0)+"0"+sr.substring(2));
		}
	}

	/**
	 * Sets the status register's negative bit to the desired value. In the RISC
	 * AR5, the third (left-to-right) bit in the status register (SR) denotes
	 * the value of the negative flag.
	 * @param condition
	 */
	public void setNegativeBit(boolean condition){

		String sr = getSR();
		if(condition){
			registers.put("SR", sr.substring(0,2)+"1"+sr.charAt(3));
		}

		else{
			registers.put("SR", sr.substring(0,2)+"0"+sr.charAt(3));
		}

	}



	/**
	 * Sets the status register's overflow bit to the desired value. In the
	 * RISC AR5, the least significant bit in the status register (SR), denotes
	 * the value of the overflow flag.
	 * @param condition
	 */
	public void setOverflowBit(boolean condition){

		String sr = getSR();
		if(condition){
			registers.put("SR", sr.substring(0,3)+"1");
		}
		else{
			registers.put("SR", sr.substring(0,3)+"0");
		}
	}

	/**
	 * Clears all values of the status register by setting all flags to 0.
	 */
	public void clearSR(){
		registers.put("SR", "0000");
	}

	/**
	 * Sets the program counter to the address that holds the next instruction
	 * to be executed. 
	 * @param address
	 * @throws InvalidProgramCounterException 
	 */

	public void setPC(String addressKey) throws InvalidProgramCounterException{
		int address = Integer.parseInt(addressKey,2);
		//Address must be greater than 0.
		if (address<0){
			throw new InvalidProgramCounterException("The program counter is trying to" +
					"be set to a negative address. Usage: utilize an 8-bit string that " +
					"does not exceed the representation of 154 (11111110). 254]. The most" +
					"siginificant bit is NOT considered negative for these purposes");

		}
		else if (address>254){
			throw new InvalidProgramCounterException("If the program counter" +
					"where to increment, it would be addressing a memory address that" +
					"does not exist in the RISC AR5. Program counter can address any " +
					"even value in the range of [0-254]. Current PC: "+getPC());
		}
		//Address must be even!
		else if (address%2!=0){
			throw new InvalidProgramCounterException("The program counter must always" +
					"be pointing to an even address in memory. The address " + addressKey+
					"is not even.");
		} else
			try {
				if(RISC_AR5.memory.getAddress(address+"").trim().isEmpty()){
					throw new InvalidProgramCounterException("The program counter must not hold the " +
							"address of an emtpy space in memory. This will cause the Instruction " +
							"Register to try to excute a null instruction.");
				}
				else{
					//Valid PC - put into memory
					registers.put("PC", addressKey);
				}
			} 
		catch (InvalidAddressException e) {
			//Not a valid memory address [0-255]
			e.printStackTrace();
		}

	}

	/**
	 * Sets the accumulator to the specified 8-bit word. If the value
	 * is not an 8-bit word, throws exception.
	 * @param value
	 * @throws InvalidAddressValueException 
	 */
	public void setAcc(String value) throws InvalidAddressValueException{
		if (value.matches("[0-1]{8}"))
			registers.put("Acc", value);
		else{
			throw new InvalidAddressValueException("The specified value- "+ value+
					"- is not of considered valid for the RISC AR5. The accumulator" +
					"should be loaded with a binary string of 8 digits.");
		}
	}

	/**
	 * Sets the instruction register according to the value specified by the PC.
	 */
	public void setIR(){
		int decimalPC = Integer.parseInt(getPC(),2);
		String instruction;
		try {
			instruction = RISC_AR5.memory.getAddress(decimalPC+"")+RISC_AR5.memory.getAddress((decimalPC+1)+"");
			this.registers.put("IR", instruction);
		} catch (InvalidAddressException e) {
			// Not valid address memories. Usage: string representation of any number in 0-255.
			e.printStackTrace();
		}

	}

	/**
	 * Increments the program counter by two. Therefore, the program counter will point to
	 * the memory address of the next instruction to be executed. This holds the condition 
	 * the PC cannot be incremented above the 8-bit word that represents 154 (11111110),
	 * for it will fall out of bounds.
	 * @throws ProgramCounterOutOfBoundsException
	 */
	public void incrementPC() throws InvalidProgramCounterException{
		int currentPC = Integer.parseInt(getPC(),2);
		if (currentPC<254){
			registers.put("PC", NotationConversion.decimalTo8BitBinary(currentPC+2));
		}
		else{
			new InvalidProgramCounterException("Program Counter cannot be set at an address" +
					"higher than 254. Memory only reaches to 256 addressable units [0-255];" +
					" therefore, the instruction contained in location 254 and 255 are last legal" +
					" options.");
		}

	}

	/**
	 * Uses the 3-bit string rf to determine the register in which
	 * to load the 8-bit word represented by the parameter "value". Three
	 * bits denote exactly 8 registers (R0-R7). Therefore, if rf is not 3-bit
	 * or value is not 3-bit, throw respective exception.
	 * @param rf
	 * @param value
	 * @throws InvalidAddressException
	 * @throws InvalidAddressValueException
	 */
	public void setRegister(String rf, String value) throws InvalidAddressException, 
	InvalidAddressValueException{
		//Verify if value is 8-bit binary
		if (value.matches("[0-1]{8}")){
			if (rf.matches("[0-1]{3}")){
				int registerNumber = Integer.parseInt(rf,2);
				//If rf does not reference any of the registers, there is a 
				//faulty addressing parameter.
				if (registerNumber==0)
					registers.put("R0", value);
				else if (registerNumber==1)
					registers.put("R1", value);
				else if (registerNumber==2)
					registers.put("R2", value);
				else if (registerNumber==3)
					registers.put("R3", value);
				else if (registerNumber==4)
					registers.put("R4", value);
				else if (registerNumber==5)
					registers.put("R5", value);
				else if (registerNumber==6)
					registers.put("R6", value);
				else{ 
					registers.put("R7", value);
				}
			}
			else{
				throw new InvalidAddressException("The specified address -"+ rf+ "-is not" +
						"valid for the RISC AR5. Valid representations for addressing general" +
						"registers include any string representation in the range 0-7.");
			}
		}
		else{
			throw new InvalidAddressValueException("The specified value- "+ value+
					"- is not of considered valid for the RISC AR5. The general " +
					"purpose registers should be loaded with a binary string of 8 digits.");
		}
	}

	/**
	 * Utility method-
	 * Print all of the Status Register's flags to the console. Used for debugging
	 * purposes. 
	 */
	public void printFlags(){
		String result=	"Zero Flag: "+ getSR().charAt(0)+"\n"+
				"Carry Flag: "+ getSR().charAt(1)+"\n"+
				"Negative Flag: "+ getSR().charAt(2)+"\n"+
				"Overflow Flag: "+ getSR().charAt(3);

		System.out.println(result);
	}
}
