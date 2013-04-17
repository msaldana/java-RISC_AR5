package uprm.ece.icom4215.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uprm.ece.icom4215.ar5.RISC_AR5;
import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;
import uprm.ece.icom4215.exceptions.UnsupportedInstructionException;

public class TestSample {
	
	@Before
	public void setUp(){
		RISC_AR5.init();
		RISC_AR5.memory.loadMemory("./sampleCode.txt");
	}

	@Test
	public void testSampleCode() throws InvalidAddressException, InvalidAddressValueException, UnsupportedInstructionException {
		RISC_AR5.step();/*7055*/
		assertEquals("01010101", RISC_AR5.registers.getAcc());
		
		RISC_AR5.step();/*5800*/
		assertEquals("01010101", RISC_AR5.registers.getRegister("000"));
		
		RISC_AR5.step();/*70AA*/
		assertEquals("10101010", RISC_AR5.registers.getAcc());
		
		RISC_AR5.step();/*5900*/
		assertEquals("10101010", RISC_AR5.registers.getRegister("001"));
	
		RISC_AR5.step();/*70FF*/
		assertEquals("11111111", RISC_AR5.registers.getAcc());
		
		RISC_AR5.step();/*5A00*/
		assertEquals("11111111", RISC_AR5.registers.getRegister("010"));
		
		RISC_AR5.step();/*5B00*/
		assertEquals("11111111", RISC_AR5.registers.getRegister("011"));
		
		RISC_AR5.step();/*5C00*/
		assertEquals("11111111", RISC_AR5.registers.getRegister("100"));
		
		RISC_AR5.step();/*5D00*/
		assertEquals("11111111", RISC_AR5.registers.getRegister("101"));
		
		RISC_AR5.step();/*5E00*/
		assertEquals("11111111", RISC_AR5.registers.getRegister("110"));
		
		RISC_AR5.step();/*7036*/
		assertEquals("00110110", RISC_AR5.registers.getAcc());
		
		RISC_AR5.step();/*5F00*/
		assertEquals("00110110", RISC_AR5.registers.getRegister("111"));
		
		RISC_AR5.step();/*70FF*/
		assertEquals("11111111", RISC_AR5.registers.getAcc());
		
		RISC_AR5.step();/*5000*/
		assertEquals("01010101", RISC_AR5.registers.getAcc());

		RISC_AR5.step();/*0000*/
		assertEquals("01010101", RISC_AR5.registers.getAcc());
		
		RISC_AR5.step();/*0900*/
		assertEquals("11111111", RISC_AR5.registers.getAcc());
		assertEquals("0010", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*5000*/
		assertEquals("01010101", RISC_AR5.registers.getAcc());
		
		RISC_AR5.step();/*1800*/
		assertEquals("10101010", RISC_AR5.registers.getAcc());
		assertEquals("0011", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*2100*/
		assertEquals("00000000", RISC_AR5.registers.getAcc());
		assertEquals("1100", RISC_AR5.registers.getSR());
		/*aqui no se supone que haya un carry*/
		
		RISC_AR5.step();/*5700*/
		assertEquals("00110110", RISC_AR5.registers.getAcc());
		assertEquals("0100", RISC_AR5.registers.getSR());
		/*aqui no se supone que haya un carry porque es un lda*/
		
		RISC_AR5.step();/*2F00*/
		assertEquals("00100100", RISC_AR5.registers.getAcc());
		assertEquals("0000", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*3000*/
		assertEquals("11011100", RISC_AR5.registers.getAcc());
		assertEquals("0010", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*3F00*/
		assertEquals("00100011", RISC_AR5.registers.getAcc());
		assertEquals("0000", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*4000*/
		assertEquals("01000110", RISC_AR5.registers.getAcc());
		assertEquals("0000", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*4F00*/
		assertEquals("00100011", RISC_AR5.registers.getAcc());
		assertEquals("0000", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*6732*/
		assertEquals("01100111", RISC_AR5.registers.getAcc());
		assertEquals("0000", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*6FFF*/
		assertEquals("01100111", RISC_AR5.registers.getAcc());
		assertEquals("0000", RISC_AR5.registers.getSR());
		
		RISC_AR5.step();/*C000*/
		assertEquals("01100111", RISC_AR5.registers.getAcc());
		assertEquals("0000", RISC_AR5.registers.getSR());

}
}
