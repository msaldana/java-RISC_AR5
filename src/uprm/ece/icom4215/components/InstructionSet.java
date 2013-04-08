package uprm.ece.icom4215.components;



import java.util.HashMap;

import uprm.ece.icom4215.ar5.RISC_AR5;
import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;
import uprm.ece.icom4215.exceptions.InvalidProgramCounterException;

public class InstructionSet {

	HashMap<String, String> set = new HashMap<String, String>();

	public InstructionSet(){
		set.put("00000", "AND rf");
		set.put("00001", "OR rf");
		set.put("00011", "ADDC rf");
		set.put("00100", "SUB rf");
		set.put("00101", "MUL rf");
		set.put("00110", "NEG");
		set.put("00111", "NOT");
		set.put("01000", "RLC");
		set.put("01001", "RRC");
		set.put("01010", "LDA rf");
		set.put("01011", "STA rf");
		set.put("01100", "LDA addr");
		set.put("01101", "STA addr");
		set.put("01110", "LDI Immediate");
		set.put("10000", "BRZ");
		set.put("10001", "BRC");
		set.put("10010", "BRN");
		set.put("10011", "BRO");
		set.put("11111", "STOP");
		set.put("11000", "NOP");

	}

	public void next(String instruction){
		String op = set.get(instruction.substring(0, 5));

		if (op.equals("AND rf")){
			//Register Direct Addressing: need bits 8-10.
			this.and(instruction.substring(5,8));
		}

		else if(op.equals("OR rf")){
			//Register Direct Addressing: need bits 8-10.
			this.or(instruction.substring(5,8));
		}
		else if(op.equals("ADDC rf")){
			//Register Direct Addressing: need bits 8-10.
			this.add(instruction.substring(5,8));

		}
		else if(op.equals("SUB rf")){
			//Register Direct Addressing: need bits 8-10.
			this.sub(instruction.substring(5,8));
		}
		else if(op.equals("MUL rf")){
			//Register Direct Addressing: need bits 8-10.
			//this.mul(instruction.substring(5,8));
		}
		else if(op.equals("NEG")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			this.neg();

		}
		else if(op.equals("NOT")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			this.not();

		}
		else if(op.equals("RLC")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			this.rlc();

		}
		else if(op.equals("RRC")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			this.rrc();

		}
		else if(op.equals("LDA rf")){
			//Register Direct Addressing: need bits 8-10.
			this.lda(instruction.substring(5,8));

		}
		else if(op.equals("STA rf")){
			//Register Direct Addressing: need bits 8-10.
			this.sta(instruction.substring(5,8));

		}
		else if(op.equals("LDA addr")){
			//Immediate Addressing: need all bits after the opcode [ 0-10].
			this.ldaAddr(instruction.substring(8));

		}
		else if(op.equals("STA addr")){
			//Immediate Addressing: need bits 0-7.
			this.staAddr(instruction.substring(8));

		}
		else if(op.equals("LDI Immediate")){
			//Immediate Addressing: need bits 0-7.
			this.ldi(instruction.substring(8));

		}
		else if(op.equals("BRZ")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			//this.brz();
		}
		else if(op.equals("BRC")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			//this.brc();
		}
		else if(op.equals("BRN")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			//this.brn();
		}
		else if(op.equals("BRO")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			//this.bro();
		}
		else if(op.equals("STOP")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			//this.stop();
		}
		else if(op.equals("NOP")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			//this.nop();
		}

		else{
			//Error Exception RISC AR5 Unsupported Instruction
		}
	}

	/**
	 * Utilizes the 3 bit parameter to search for the 8-bit word associated with the
	 * chosen register. Then compares each corresponding bit of the accumulator and 
	 * the word contained in the register to perform the AND operation. If both bits are '1' 
	 * then the AND result is '1', otherwise it is '0'.
	 * @param rf
	 */
	private void and(String rf) {
		String acc, register;
		acc = RISC_AR5.registers.getAcc();
		try {
			register = RISC_AR5.registers.getRegister(rf);
			StringBuilder result = new StringBuilder();
			for(int i=0; i< acc.length(); i++){
				if(register.charAt(i) == '1' && acc.charAt(i) == '1')
					result.append("1");
				else 
					result.append("0");
			}
			RISC_AR5.registers.setAcc(result.toString());
		} 

		catch (InvalidAddressException e) {
			// rf must be a 3-bit string.
			e.printStackTrace();
		}
		catch (InvalidAddressValueException e){
			// the accumulator must be given an 8-bit string.
			e.printStackTrace();
		}

	} 

	/**
	 * Utilizes the 3 bit parameter to search for the 8-bit word associated with the
	 * chosen register. Then compares each corresponding bit of the accumulator and 
	 * the word contained in the register to perform the OR operation. If both bits are '0' 
	 * then the OR result is '0', otherwise it is '1'.
	 * @param rf
	 */
	private void or(String rf) {
		String acc, register;
		acc = RISC_AR5.registers.getAcc();
		try {
			register = RISC_AR5.registers.getRegister(rf);
			StringBuilder result = new StringBuilder();
			for(int i=0; i< acc.length(); i++){
				if(register.charAt(i) == '0' && acc.charAt(i) == '0')
					result.append("0");
				else 
					result.append("1");
			}
			RISC_AR5.registers.setAcc(result.toString());
		} 
		catch (InvalidAddressException e) {
			// rf must be a 3-bit string.
			e.printStackTrace();
		}
		catch (InvalidAddressValueException e){
			// the accumulator must be given an 8-bit string.
			e.printStackTrace();
		}

	} 



	/**
	 * Utilizes the 3 bit parameter to fetch for the 8-bit word associated with the 
	 * chosen register. Then, it adds this register to the accumulator and stores the 
	 * result in the accumulator.
	 * @param rf
	 */
	private void add(String rf){
		try {
			addValue(RISC_AR5.registers.getRegister(rf));
		} catch (InvalidAddressException e) {
			// rf is not a 3-bit word
			e.printStackTrace();
		}
	}
	
	/**
	 * Utilizes an 8-bit word to add to the value of the accumulator. The result of 
	 * the operation is stored in the accumulator.
	 * @param eightBitValue
	 */
	private void addValue(String eightBitValue){
		RISC_AR5.registers.clearSR();
		String acc = RISC_AR5.registers.getAcc();
		try{
			
			StringBuilder result = new StringBuilder();
			int accBit, registerBit,carryBit;
			carryBit =0;

			for(int i=7; i>=0; i--){
				accBit = Integer.parseInt(acc.charAt(i)+"");
				registerBit = Integer.parseInt(eightBitValue.charAt(i)+"");


				if(accBit+registerBit+carryBit==3){ 
					result.insert(0, "1");
					carryBit = 1;
				}

				else if(accBit+registerBit+carryBit==2){
					result.insert(0, "0");
					carryBit = 1;
				}
				else if(accBit+registerBit+carryBit==1){
					result.insert(0,"1");
					carryBit = 0;
				}

				else{
					result.insert(0, "0");
					carryBit=0;
				}
			}


			//If carryBit is 1 then carryBit flag is true, otherwise carry flag is false.
			RISC_AR5.registers.setCarryBit(carryBit==1);

			//In order to verify if an overflow condition has happened the following conditions
			//must be met: most significant bit in the accumulator must be equal to the most 
			//significant bit in the register, and the selected accumulator's bit must be different
			//from the results most significant bit.
			accBit = Integer.parseInt(acc.substring(0));
			registerBit = Integer.parseInt(eightBitValue.substring(0));

			if(accBit == registerBit && Integer.parseInt(result.charAt(0)+"")!=accBit){
				RISC_AR5.registers.setOverflowBit(true);
			}

			//If the result is zero, set the zero flag
			if (Integer.parseInt(result.toString(),2)==0){
				RISC_AR5.registers.setZeroBit(true);
			}

			//If the most significant bit is 1, negative flag true.
			if(result.charAt(0) =='1'){
				RISC_AR5.registers.setNegativeBit(true);
			}

			if(result.length()==8){
				RISC_AR5.registers.setAcc(result.toString());
			}
		}
		catch (InvalidAddressValueException e){
			// the accumulator must be given an 8-bit string.
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Utilizes the 3 bit parameter to fetch for the 8-bit word associated with the 
	 * chosen register. Then, it subtracts this register to the accumulator and stores the 
	 * result in the accumulator.
	 * 
	 * 
	 * @param rf
	 */
	private void sub(String rf){
		//First apply 2's compliment to rf, then add it to 
		//the accumulator.
			try {
				String register = RISC_AR5.registers.getRegister(rf);
				StringBuilder result = new StringBuilder();
				boolean foundFirstOne = false;

				for(int i=7; i>=0; i--){
					if (!foundFirstOne){
						if(register.charAt(i)=='1'){
							foundFirstOne=true;
							result.insert(0,"1");
						}
						else
							result.insert(0, "0");
					}
					else{
						if(register.charAt(i)=='1')
							result.insert(0,"0");
						else
							result.insert(0,"1");

					}
				}
				addValue(result.toString());
			} catch (InvalidAddressException e) {
				// rf is not a 3-bit word
				e.printStackTrace();
			}
				
	}
	
	

	/**
	 * Applies two's compliment to the value stored in the accumulator.
	 * The result of this instruction is handed right back at the accumulator. Two's
	 * compliment is a representation that inverses each bit value taken after the least
	 * significant '1' is detected. Example: "10101011" would result in "01010101".
	 */
	private void neg() {

		String acc = RISC_AR5.registers.getAcc();
		StringBuilder result = new StringBuilder();
		boolean foundFirstOne = false;

		for(int i=7; i>=0; i--){
			if (!foundFirstOne){
				if(acc.charAt(i)=='1'){
					foundFirstOne=true;
					result.insert(0,"1");
				}
				else
					result.insert(0, "0");
			}
			else{
				if(acc.charAt(i)=='1')
					result.insert(0,"0");
				else
					result.insert(0,"1");

			}
		}

		try {
			RISC_AR5.registers.setAcc(result.toString());
		} 
		catch (InvalidAddressValueException e) {
			// the accumulator must be given an 8-bit word
			e.printStackTrace();
		}
	}

	/**
	 * Complements each bit of the 8-bit word located in the accumulator.
	 * Upon finishing, hands the result back to the accumulator.
	 */
	private void not() {
		String acc = RISC_AR5.registers.getAcc();
		StringBuilder result = new StringBuilder(); 

		for(int i=0; i< 8; i++){
			if(acc.charAt(i)=='0')
				result.append("1");
			else 
				result.append("0");
		}
		try {
			RISC_AR5.registers.setAcc(result.toString());
		} catch (InvalidAddressValueException e) {
			// the accumulator must be given an 8-bit word
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the 8-bit word located in the accumulator, then shifts all bits
	 * in a circular left motion by 1 bit. The result of this operation is handed back
	 * to the accumulator.
	 */
	private void rlc(){
		String acc = RISC_AR5.registers.getAcc();
		StringBuilder result = new StringBuilder();
		RISC_AR5.registers.clearSR();


		for (int i=1; i<8;i++){
			result.append(acc.charAt(i));
		}
		result.append(acc.charAt(0));

		if(result.charAt(0)=='1')
			RISC_AR5.registers.setNegativeBit(true);

		if(Integer.parseInt(result.toString(),2)==0)
			RISC_AR5.registers.setZeroBit(true);

		try {
			RISC_AR5.registers.setAcc(result.toString());
		} catch (InvalidAddressValueException e) {
			// the accumulator must be given an 8-bit word
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the 8-bit word located in the accumulator, then shifts all bits
	 * in a circular right motion by 1 bit. The result of this operation is handed back
	 * to the accumulator.
	 */
	private void rrc(){
		String acc = RISC_AR5.registers.getAcc();
		StringBuilder result = new StringBuilder();
		RISC_AR5.registers.clearSR();

		result.append(acc.charAt(7));
		for (int i=0; i<7;i++){
			result.append(acc.charAt(i));
		}


		if(result.charAt(0)=='1')
			RISC_AR5.registers.setNegativeBit(true);

		if(Integer.parseInt(result.toString(),2)==0)
			RISC_AR5.registers.setZeroBit(true);

		try {
			RISC_AR5.registers.setAcc(result.toString());
		} catch (InvalidAddressValueException e) {
			// the accumulator must be given an 8-bit word
			e.printStackTrace();
		}
	}

	/**
	 * Loads the 8-bit word of the selected register into the accumulator.
	 * @param rf
	 */
	private void lda(String rf){
		try {
			RISC_AR5.registers.setAcc(RISC_AR5.registers.getRegister(rf));
		} catch (InvalidAddressValueException e) {
			// the accumulator must be passed an 8-bit word
			e.printStackTrace();
		} catch (InvalidAddressException e) {
			// rf must be a 3-bit word
			e.printStackTrace();
		}
	}

	/**
	 * Stores the 8-bit word of the accumulator into the selected register.
	 * @param rf
	 */
	private void sta(String rf){
		try {
			RISC_AR5.registers.setRegister(rf, RISC_AR5.registers.getAcc());
		} catch (InvalidAddressException e) {
			// rf must be a 3-bit word
			e.printStackTrace();
		} catch (InvalidAddressValueException e) {
			// the register must be given an 8-bit word
			e.printStackTrace();
		}
	}

	/**
	 * Fetches for the 8-bit word located at the direct address location specified
	 * in the instruction. Note that subtracting the operation code and the general
	 * purpose register bits, we have 8 remaining bits. The parameter of this method
	 * is composed of the 3-bit notation for the registers and the 8-bit which are used
	 * to specify the direct address. Therefore, we only really care for the bits in
	 * position 3-10. Represent in decimal to get the memory value at that location and 
	 * record the resulting value in the accumulator.
	 * @param instruction
	 */
	private void ldaAddr(String instruction){
		try {
			RISC_AR5.registers.setAcc(RISC_AR5.memory.getAddress(Integer.parseInt(instruction,2)+""));
		} catch (NumberFormatException e) {
			//The parsing of the instruction should convert an 8-bit word into a decimal 
			//number in the range of 0-255. If the string is not binary, it will not parse
			//correctly.
			e.printStackTrace();
		} catch (InvalidAddressException e) {
			//The address given to the memory component is not defined. Usage: decimal number in
			//the range 0-255.
			e.printStackTrace();
		} catch (InvalidAddressValueException e) {
			// Must give the accumulator an 8-bit word.
			e.printStackTrace();
		}
	}

	/**
	 * Stores the 8-bit word of the accumulator directly into the memory location 
	 * specified by the last 8-bits of the instruction parameter. The first 3 bits of
	 * the parameter represent the general purpose register (not used by this method).
	 * @param instruction
	 */
	private void staAddr(String instruction){
		try {
			RISC_AR5.memory.setAddress(Integer.parseInt(instruction,2)+"", RISC_AR5.registers.getAcc());
		} catch (NumberFormatException e) {
			//The parsing of the instruction should convert an 8-bit word into a decimal 
			//number in the range of 0-255. If the string is not binary, it will not parse
			//correctly.
			e.printStackTrace();
		} catch (InvalidAddressValueException e) {
			//The data trying to be stored into the given memory location is not an 8-bit word.
			e.printStackTrace();
		} catch (InvalidAddressException e) {
			//The address given to the memory component is not defined. Usage: decimal number in
			//the range 0-255.
			e.printStackTrace();
		}
	}

	/**
	 * This instruction is based on Immediate Addressing, therefore, the data
	 * to be operated is contained in the last 8-bits of the original instruction.
	 * Therefore this method directly inserts these bits into the accumulator.
	 * @param instruction
	 */
	private void ldi(String instruction){
		try {
			RISC_AR5.registers.setAcc(instruction);
		} catch (InvalidAddressValueException e) {
			// instruction is not an 8-bit word
			e.printStackTrace();
		}
	}

	/**
	 * If the Zero bit of the status register is 1 then the next instruction 
	 * to be executed will be that located in the general purpose register R7.
	 */
	public void brz() {
		if(RISC_AR5.registers.getSR().charAt(0)=='1'){
			try {
				RISC_AR5.registers.setPC(RISC_AR5.registers.getRegister("111"));
			} catch (InvalidProgramCounterException e) {
				// The program counter is tried to be set into a location that is not
				// valid. Valid locations are non empty even memory addresses in the 
				// range of 0-254.
				e.printStackTrace();
			} catch (InvalidAddressException e) {
				// The setPC function did not receive an 8-bit word.
				e.printStackTrace();
			}
		}
	}

	/**
	 * If the Carry bit of the status register is 1 then the next instruction 
	 * to be executed will be that located in the general purpose register R7.
	 */
	public void brc() {
		if(RISC_AR5.registers.getSR().charAt(1)=='1'){
			try {
				RISC_AR5.registers.setPC(RISC_AR5.registers.getRegister("111"));
			} catch (InvalidProgramCounterException e) {
				// The program counter is tried to be set into a location that is not
				// valid. Valid locations are non empty even memory addresses in the 
				// range of 0-254.
				e.printStackTrace();
			} catch (InvalidAddressException e) {
				// The setPC function did not receive an 8-bit word.
				e.printStackTrace();
			}
		}
	}

	/**
	 * If the Negative bit of the status register is 1 then the next instruction 
	 * to be executed will be that located in the general purpose register R7.
	 */
	public void brn() {
		if(RISC_AR5.registers.getSR().charAt(2)=='1'){
			try {
				RISC_AR5.registers.setPC(RISC_AR5.registers.getRegister("111"));
			} catch (InvalidProgramCounterException e) {
				// The program counter is tried to be set into a location that is not
				// valid. Valid locations are non empty even memory addresses in the 
				// range of 0-254.
				e.printStackTrace();
			} catch (InvalidAddressException e) {
				// The setPC function did not receive an 8-bit word.
				e.printStackTrace();
			}
		}
	}

	/**
	 * If the Overflow bit of the status register is 1 then the next instruction 
	 * to be executed will be that located in the general purpose register R7.
	 */
	public void bro() {
		if(RISC_AR5.registers.getSR().charAt(3)=='1'){
			try {
				RISC_AR5.registers.setPC(RISC_AR5.registers.getRegister("111"));
			} catch (InvalidProgramCounterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Performs the stop instruction, declaring that no further instructions
	 * follow. This is the end of the fetch-execute cycle.
	 */
	public void stop(){
		
		RISC_AR5.stop();
	}


	/**
	 * No operation instruction.
	 */
	@SuppressWarnings("unused")
	private void nop() {}

}
