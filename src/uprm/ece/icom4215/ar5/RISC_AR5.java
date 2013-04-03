package uprm.ece.icom4215.ar5;

import uprm.ece.icom4215.components.InstructionSet;
import uprm.ece.icom4215.components.Memory;
import uprm.ece.icom4215.components.Registers;

public class RISC_AR5 {
	public static Memory memory;
	public static Registers registers;
	public static InstructionSet instructions;
	private static boolean stop;
	
	public static void init(){
		memory = new Memory();
		registers = new Registers();
		instructions = new InstructionSet();
		stop = false;
	}
	
	public static void stop(){
		stop = true;
	}
	
	public static void main(String[] args){
		RISC_AR5.init();
		registers.setRegister("000", "01010101");
		registers.setAcc("10101010");
		instructions.next("000110001111");
		
		
		registers.setAcc("11111111");
		registers.setRegister("000", "11111111");
		instructions.next("0001100000000000");
		System.out.println("ADD Op successful: " +"11111110".equals(registers.getAcc()));
		registers.printFlags();
		
		registers.setAcc("10101010");
		registers.setRegister("000", "0101010101");
		instructions.next("0000000000000000");
		System.out.println("AND Op successful: "+"00000000".equals(registers.getAcc()));
		

		registers.setAcc("10101011");
		instructions.next("0011000000000000");
		System.out.println("NEG Op successful: "+"01010101".equals(registers.getAcc()));
		
		instructions.next("0011100000000000");
		System.out.println("NOT Op successful: "+"10101010".equals(registers.getAcc()));
		
		instructions.next("0100000100000000");
		System.out.println("RLC Op successful: "+"01010101".equals(registers.getAcc()));
		
		instructions.next("0100010000000000");
		System.out.println("RRC Op successful: "+ "10101010".equals(registers.getAcc()));
		
		
		
	}
}
