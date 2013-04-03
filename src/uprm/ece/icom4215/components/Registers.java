package uprm.ece.icom4215.components;

import java.util.HashMap;

import uprm.ece.icom4215.util.NotationConversion;

public class Registers {

	HashMap<String, String> registers = new HashMap<String, String>();

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
	 * Returns the 8-bit word representing the general purpose register RX.
	 * @return
	 */
	public String getRegister(String rf){
		//if the number is not in the range 0-7 return error
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
		else if (registerNumber==7)
			return registers.get("R7");
		else
			return "-1";
	}
	

	/**
	 * Sets the status register's zero bit to the desired value.
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
	 * Sets the status register's carry bit to the desired value. 
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
	 * Sets the status register's negative bit to the desired value.
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
	 * Sets the status register's overflow bit to the desired value.
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
	 * Clears all values of the status register and sets all flags to 0.
	 */
	public void clearSR(){
		registers.put("SR", "0000");
	}
	
	/**
	 * Sets the program counter to the address of the next instruction to be executed. 
	 * @param address
	 */
	public void setPC(String address){
		registers.put("PC", address);
	}
	
	/**
	 * Sets the accumulator to the specified 8-bit word.
	 * @param value
	 */
	public void setAcc(String value){
		registers.put("Acc", value);
	}
	
	public void incrementPC(){
		//Note: recuerda ponerlo para que no vaya a pasar de 254.
		String currentPC = getPC();
		int newPC = Integer.parseInt(currentPC,2)+2;
		registers.put("PC", NotationConversion.decimalTo8BitBinary(newPC));
	}
	
	public void setRegister(String rf, String value){
		//if the number is not in the range 0-7 return error
		int registerNumber = Integer.parseInt(rf,2);
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
		else if (registerNumber==7)
			registers.put("R7", value);
		else
			System.out.println("Critical error: trying a word different of 8-bits");
	}
	
	public void printFlags(){
		String result=	"Zero Flag: "+ getSR().charAt(0)+"\n"+
				        "Carry Flag: "+ getSR().charAt(1)+"\n"+
				        "Negative Flag: "+ getSR().charAt(2)+"\n"+
				        "Overflow Flag: "+ getSR().charAt(3);
	
		System.out.println(result);
	}
	

	public static void main(String[] args){
		Registers reg = new Registers();
		System.out.println(reg.getSR());
		
		reg.setZeroBit(true);
		System.out.println(reg.getSR());
		reg.setZeroBit(false);
		System.out.println(reg.getSR());
		
		reg.setCarryBit(true);
		System.out.println(reg.getSR());
		reg.setCarryBit(false);
		System.out.println(reg.getSR());
		
		reg.setNegativeBit(true);
		System.out.println(reg.getSR());
		reg.setNegativeBit(false);
		System.out.println(reg.getSR());
		
		reg.setOverflowBit(true);
		System.out.println(reg.getSR());
		reg.setOverflowBit(false);
		System.out.println(reg.getSR());	
	}
}
