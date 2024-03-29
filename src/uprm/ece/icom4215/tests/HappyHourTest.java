package uprm.ece.icom4215.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uprm.ece.icom4215.ar5.RISC_AR5;
import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;

/**
 *Simulates the test file that will be used in the demonstration 
 *of this project. All test must execute the expected behavior to
 *assume proper functioning of the system.
 *
 */
public class HappyHourTest {
	
	/**
	 * Initialize processor to default values, prior to the execution 
	 * of the test. Load up the memory with the evaluation text file.
	 */
	@Before
	public void setUp(){
		RISC_AR5.init();
		RISC_AR5.memory.loadMemory("./happyHour.txt");
	}
	
	/**
	 * Runs all instructions and asserts that all of them are 
	 * functioning as per the expected proper value. 
	 * @throws InvalidAddressValueException
	 * @throws InvalidAddressException
	 */
	@Test
	public void testRun() throws  InvalidAddressValueException, InvalidAddressException {
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("00110010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getRegister("001").equals("00110010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("11110110"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='1');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getRegister("010").equals("11110110"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='1');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("00101000"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='1');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getRegister("111").equals("00101000"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='1');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("00000010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='1');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getRegister("011").equals("00000010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='1');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.memory.getAddress("128").equals("00000010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='1');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("00000101"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals(
					RISC_AR5.memory.getAddress("129")));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		//No carry flag.
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("11111011"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='1');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.memory.getAddress("130").equals("11111011"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='1');
		
		/**If no overflow occurs then we can set this to 0 from the program
		 * and no problem. However, our discussion led to the conclusion that
		 * if the two significant bits of the operands are the same the result
		 * must be positive (0 in MSB) otherwise, it should be negative (1 in MSB).
		 * If this doesn't occur, we throw an overflow. 
		 */
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("01000010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		//Since 250 is not defined, stepping and trying to load this memory will
		//crash the system. Lets assume that 250 has the random word "10101010"
		//in its memory. This way we can make the test continue.
		RISC_AR5.memory.setAddress("250", "10101010");
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("10101010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='1');
		
		//This instruction uses the previous operation. Since we assumed that
		//250 contained a word, then the accumulator got buffered with its contents.
		//This test puts those contents into Register 1. On the GUI, this will be done
		//with the keyboard input.
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getRegister("001").equals("10101010"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='1');
		
		//Since overflow occurs on the step before, this is unchanged by the 
		//two's compliment negate operation.
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("01010110"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='0');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("00000000"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='1');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='1');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getPC().equals(
				RISC_AR5.registers.getRegister("111")));
		
		//skips to 40 (28 hex)
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.registers.getAcc().equals("01001110"));
		assertTrue(RISC_AR5.registers.getZeroFlag()=='0');
		assertTrue(RISC_AR5.registers.getCarryFlag()=='1');
		assertTrue(RISC_AR5.registers.getOverflowFlag()=='0');
		assertTrue(RISC_AR5.registers.getNegativeFlag()=='0');
		
		RISC_AR5.step();
		assertTrue((char)Integer.parseInt(RISC_AR5.memory.getAddress("252"),2) == 'N');
		
		RISC_AR5.step();
		assertTrue(RISC_AR5.isStopped()==true);
				
	}
	
	

}
