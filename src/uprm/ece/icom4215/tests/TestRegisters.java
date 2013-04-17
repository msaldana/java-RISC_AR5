package uprm.ece.icom4215.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import uprm.ece.icom4215.ar5.RISC_AR5;
import uprm.ece.icom4215.components.Registers;
import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;
import uprm.ece.icom4215.exceptions.InvalidProgramCounterException;


public class TestRegisters {

	@Test
	public void testRegistersPC() throws InvalidProgramCounterException, InvalidAddressValueException, InvalidAddressException {
		RISC_AR5.init();
		RISC_AR5.memory.setAddress("2", "00000000");
		RISC_AR5.memory.setAddress("3", "00000000");
		RISC_AR5.registers.setPC("00000010");
		assertEquals("00000010", RISC_AR5.registers.getPC());
	}
	
	@Test
	public void testRegistersAcc() throws InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setAcc("00000000");
		assertEquals("00000000", registers.getPC());
	}
	
	@Test
	public void testRegistersIR() throws InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setAcc("00000000");
		assertEquals("00000000", registers.getPC());
	}
	
	@Test
	public void testRegistersrf0() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("000", "00000000");
		assertEquals("00000000", registers.getRegister("000"));
	}
	
	@Test
	public void testRegistersrf1() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("001", "00000000");
		assertEquals("00000000", registers.getRegister("001"));
	}
	
	@Test
	public void testRegistersrf2() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("010", "00000000");
		assertEquals("00000000", registers.getRegister("010"));
	}
	
	@Test
	public void testRegistersrf3() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("011", "00000000");
		assertEquals("00000000", registers.getRegister("011"));
	}
	
	@Test
	public void testRegistersrf4() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("100", "00000000");
		assertEquals("00000000", registers.getRegister("100"));
	}

	@Test
	public void testRegistersrf5() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("101", "00000000");
		assertEquals("00000000", registers.getRegister("101"));
	}

	@Test
	public void testRegistersrf6() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("110", "00000000");
		assertEquals("00000000", registers.getRegister("110"));
	}

	@Test
	public void testRegistersrf7() throws InvalidAddressException, InvalidAddressValueException {
		Registers registers = new Registers();
		registers.setRegister("111", "00000000");
		assertEquals("00000000", registers.getRegister("111"));
	}
	
	@Test
	public void testRegistersSR() throws InvalidAddressValueException {
		Registers registers = new Registers();
		assertEquals("0000", registers.getSR());
	}
	
	@Test
	public void testRegistersOverflowBit() {
		Registers registers = new Registers();
		registers.setOverflowBit(true);
		assertEquals("0001", registers.getSR());
	}
	
	@Test
	public void testRegistersOverflowBitF() {
		Registers registers = new Registers();
		registers.setOverflowBit(false);
		assertEquals("0000", registers.getSR());
	}
	
	@Test
	public void testRegistersNegativeBit() {
		Registers registers = new Registers();
		registers.setNegativeBit(true);
		assertEquals("0010", registers.getSR());
	}
	
	@Test
	public void testRegistersNegativeBitF() {
		Registers registers = new Registers();
		registers.setNegativeBit(false);
		assertEquals("0000", registers.getSR());
	}
	
	@Test
	public void testRegistersCarryBit() {
		Registers registers = new Registers();
		registers.setCarryBit(true);
		assertEquals("0100", registers.getSR());
	}
	
	@Test
	public void testRegistersCarryBitF() {
		Registers registers = new Registers();
		registers.setCarryBit(false);
		assertEquals("0000", registers.getSR());
	}
	
	@Test
	public void testRegistersZeroBit() {
		Registers registers = new Registers();
		registers.setZeroBit(true);
		assertEquals("1000", registers.getSR());
	}
	
	@Test
	public void testRegistersZeroBitF() {
		Registers registers = new Registers();
		registers.setZeroBit(false);
		assertEquals("0000", registers.getSR());
	}
	


	


}
