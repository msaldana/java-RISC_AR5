package uprm.ece.icom4215.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uprm.ece.icom4215.ar5.RISC_AR5;
import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;


public class MemoryTest {

	@Test
	public void testGetAddress() throws InvalidAddressException {
		RISC_AR5.init();
		RISC_AR5.memory.loadMemory("src/Memory.txt");
		assertEquals("11111010",RISC_AR5.memory.getAddress("0"));
	}
	
	@Test
	public void testSetAddress() throws InvalidAddressException, InvalidAddressValueException{
		RISC_AR5.init();
		RISC_AR5.memory.loadMemory("src/Memory.txt");
		RISC_AR5.memory.setAddress("0", "00000001");
		assertEquals("00000001", RISC_AR5.memory.getAddress("0"));
	}
	
	
	

}
