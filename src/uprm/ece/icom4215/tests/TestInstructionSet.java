package uprm.ece.icom4215.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import uprm.ece.icom4215.ar5.RISC_AR5;
import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;
import uprm.ece.icom4215.exceptions.UnsupportedInstructionException;


public class TestInstructionSet {

	@Test
	public void testAND() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("01010010");
		RISC_AR5.registers.setRegister("010", "01000010");
		RISC_AR5.instructions.next("0000001000000000");
		assertEquals("01000010", RISC_AR5.registers.getAcc());	
	}
	
	@Test
	public void testOR() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("01010010");
		RISC_AR5.registers.setRegister("010", "01000010");
		RISC_AR5.instructions.next("0000101000000000");
		assertEquals("01010010", RISC_AR5.registers.getAcc());	
	}
	
	@Test
	public void testADDC() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("10010010");
		RISC_AR5.registers.setRegister("010", "11000010");
		RISC_AR5.instructions.next("0001101000000000");
		assertEquals("0101", RISC_AR5.registers.getSR());	
		assertEquals("01010100", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testADDCOverflow() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("01101000");
		RISC_AR5.registers.setRegister("010", "00101101");
		RISC_AR5.instructions.next("0001101000000000");
		assertEquals("0011", RISC_AR5.registers.getSR());	
		assertEquals("10010101", RISC_AR5.registers.getAcc());
	}
	
	
	@Test
	public void testADDCNegative() throws UnsupportedInstructionException, InvalidAddressValueException, InvalidAddressException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("11101101");
		RISC_AR5.registers.setRegister("010", "11111001");
		RISC_AR5.instructions.next("0001101000000000");
		assertEquals("0110", RISC_AR5.registers.getSR());	
		assertEquals("11100110", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testSUB() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("01000010");
		RISC_AR5.registers.setRegister("010", "01010011");
		RISC_AR5.instructions.next("0010001000000000");
		assertEquals("0010",RISC_AR5.registers.getSR());	
		assertEquals("11101111", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testMULOverflow() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("01001001");
		RISC_AR5.registers.setRegister("010", "01011001");
		RISC_AR5.instructions.next("0010101000000000");
		assertEquals("0000", RISC_AR5.registers.getSR());
		assertEquals("01010001", RISC_AR5.registers.getAcc());
	}
	
	
	@Test
	public void testNEG() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("01000010");
		RISC_AR5.instructions.next("0011001000000000");
		assertEquals("10111110", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testNOT() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("01000010");
		RISC_AR5.instructions.next("0011101000000000");
		assertEquals("10111101", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testRLC() throws UnsupportedInstructionException, InvalidAddressValueException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("11000010");
		RISC_AR5.instructions.next("0100001000000000");
		assertEquals("0110", RISC_AR5.registers.getSR());
		assertEquals("10000100", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testRRC() throws UnsupportedInstructionException, InvalidAddressValueException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("11000010");
		RISC_AR5.registers.setCarryBit(true);
		RISC_AR5.instructions.next("0100101000000000");
		assertEquals("0010", RISC_AR5.registers.getSR());
		assertEquals("11100001", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testLDArf() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setRegister("011", "01010011");
		RISC_AR5.instructions.next("0101001100000000");
		assertEquals("01010011", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testSTArf() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("11000010");
		RISC_AR5.registers.setRegister("011", "01010011");
		RISC_AR5.instructions.next("0101101100000000");
		assertEquals("11000010", RISC_AR5.registers.getRegister("011"));
	}
	
	@Test
	public void testLDAaddr() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("11000010");
		RISC_AR5.memory.setAddress("4", "00000010");
		RISC_AR5.instructions.next("0110001100000100");
		assertEquals("00000010", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testSTAaddr() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.registers.setAcc("11000010");
		RISC_AR5.memory.setAddress("4", "00000010");
		RISC_AR5.instructions.next("0110101100000100");
		assertEquals("11000010", RISC_AR5.memory.getAddress("4"));
	}
	
	@Test
	public void testLDI() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.instructions.next("0111001100000100");
		assertEquals("00000100", RISC_AR5.registers.getAcc());
	}
	
	@Test
	public void testBRZ() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.memory.setAddress("2", "00111000");
		RISC_AR5.memory.setAddress("0", "00010000");
		RISC_AR5.registers.setZeroBit(true);
		RISC_AR5.registers.setRegister("111", "00000010");
		RISC_AR5.instructions.next("1000011100000001");
		assertEquals("00000010", RISC_AR5.registers.getPC());
	}
	
	@Test
	public void testBRC() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.memory.setAddress("2", "00111000");
		RISC_AR5.memory.setAddress("0", "00010000");
		RISC_AR5.registers.setCarryBit(true);
		RISC_AR5.registers.setRegister("111", "00000010");
		RISC_AR5.instructions.next("1000111100000001");
		assertEquals("00000010", RISC_AR5.registers.getPC());
	}
	
	@Test
	public void testBRN() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.memory.setAddress("2", "00111000");
		RISC_AR5.memory.setAddress("0", "00010000");
		RISC_AR5.registers.setNegativeBit(true);
		RISC_AR5.registers.setRegister("111", "00000010");
		RISC_AR5.instructions.next("1001011100000001");
		assertEquals("00000010", RISC_AR5.registers.getPC());
	}
	
	@Test
	public void testBRO() throws InvalidAddressValueException, InvalidAddressException, UnsupportedInstructionException {
		RISC_AR5.init();
		RISC_AR5.memory.setAddress("2", "00111000");
		RISC_AR5.memory.setAddress("0", "00010000");
		RISC_AR5.registers.setOverflowBit(true);
		RISC_AR5.registers.setRegister("111", "00000010");
		RISC_AR5.instructions.next("1001111100000001");
		assertEquals("00000010", RISC_AR5.registers.getPC());
	}
	
	/*Stop not tested yet*/
	
	/*No operation not tested yet*/
	
	

}
