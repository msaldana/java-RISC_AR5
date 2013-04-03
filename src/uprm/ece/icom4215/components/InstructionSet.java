package uprm.ece.icom4215.components;


import java.util.HashMap;

import uprm.ece.icom4215.ar5.RISC_AR5;

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
			//this.sub(instruction.substring(5,8));
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
			//this.rlc();

		}
		else if(op.equals("RRC")){
			//Implicit Addressing: don't care about bits in instruction.
			//Need operand in accumulator (A)
			//this.rrc();

		}
		else if(op.equals("LDA rf")){
			//Register Direct Addressing: need bits 8-10.
			//this.lda(instruction.substring(5,8));

		}
		else if(op.equals("STA rf")){
			//Register Direct Addressing: need bits 8-10.
			//this.sta(instruction.substring(5,8));

		}
		else if(op.equals("LDA addr")){
			//Direct Addressing: need all bits after the opcode [ 0-10].
			//this.ldaAddr(instruction.substring(5));

		}
		else if(op.equals("STA addr")){
			//Direct Addressing: need all bits after the opcode [ 0-10].
			//this.staAddr(instruction.substring(5));

		}
		else if(op.equals("LDI Immediate")){
			//Immediate Addressing: need bits 0-7.
			//this.ldi(instruction.substring(8));

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
	
	
	
	/**
	 * Utilizes the 3 bit parameter to fetch for the 8-bit word associated with the 
	 * chosen register. Then, it adds this register to the accumulator and stores the 
	 * result in the accumulator.
	 * @param rf
	 */
	private void add(String rf){
		RISC_AR5.registers.clearSR();
		String acc = RISC_AR5.registers.getAcc();
		String register = RISC_AR5.registers.getRegister(rf);
		StringBuilder result = new StringBuilder();
		int accBit, registerBit,carryBit;
		carryBit =0;
		//System.out.println("Passed acc: "+ acc);
		//System.out.println("Passed reg: "+register);

		for(int i=7; i>=0; i--){
			accBit = Integer.parseInt(acc.charAt(i)+"");
			registerBit = Integer.parseInt(register.charAt(i)+"");
			
			//System.out.println("Going to add bit "+(7-i)+ "[acc: "+accBit+", reg: "+registerBit+"]");

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
		
		System.out.println("Result of Add Operation: "+result.toString());

		//If carryBit is 1 then carryBit flag is true, otherwise carry flag is false.
		RISC_AR5.registers.setCarryBit(carryBit==1);

		//In order to verify if an overflow condition has happened the following conditions
		//must be met: most significant bit in the accumulator must be equal to the most 
		//significant bit in the register, and the selected accumulator's bit must be different
		//from the results most significant bit.
		accBit = Integer.parseInt(acc.substring(0));
		registerBit = Integer.parseInt(register.substring(0));

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
		
		
		RISC_AR5.registers.setAcc(result.toString());
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
		RISC_AR5.registers.setAcc(result.toString());
	}

}
