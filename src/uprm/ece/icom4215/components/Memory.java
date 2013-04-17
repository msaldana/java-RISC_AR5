package uprm.ece.icom4215.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import uprm.ece.icom4215.exceptions.InvalidAddressException;
import uprm.ece.icom4215.exceptions.InvalidAddressValueException;
import uprm.ece.icom4215.exceptions.InvalidMemoryFileSizeException;
import uprm.ece.icom4215.util.GarbageGenerator;
import uprm.ece.icom4215.util.NotationConversion;

/**
 * Represents the memory component of the RISC AR5. Memory for this
 * processor is specified to contain 256 addressable 8-bit words.
 * Usage: Main constructor initializes all memory locations to empty. 
 * Memory addresses can be manually loaded through the setAddress method
 * or given a valid external file through the loadMemory method. Use the 
 * getAddress method to return the data in a valid address.
 *
 */
public class Memory {

	
	//Map that will hold 256 addressable bits that hold 8-bit long 
	//words. Usage: this.memory.get([0-255]) - returns data in specified
	//location.
	HashMap<String, String> memory = new HashMap<String, String>();


	/**
	 * Main constructor: initializes memory addresses with an empty string. This is 
	 * done with the purpose to simulate that no information is stored in the initial 
	 * state of memory.
	 */
	public Memory(){
		for(int i =0; i<256; i++)
			memory.put(i+"",GarbageGenerator.generate8BitWord());
	}

	/**
	 * Reset all memory addresses.
	 */
	public void reset(){
		for(int i = 0; i< 256; i++)
			memory.put(i+"",GarbageGenerator.generate8BitWord());
	}

	/**
	 * Returns the 8 bit word located at the given address location. Usage: Valid keys
	 * are String objects representing the numbers 0-255. Any other key will return the
	 * string "null".
	 * @param addressKey
	 * @return
	 * @throws InvalidAddressException 
	 */
	public String getAddress(String addressKey) throws InvalidAddressException{
		if (this.memory.containsKey(addressKey))
			return this.memory.get(addressKey);
		else{
			throw new InvalidAddressException("The specified address -"+ addressKey+ "is not" +
					"valid for the RISC AR5. Valid representations for addressing memory" +
					"include any string representation in the range 0-255.");
		}
	}

	/**
	 * Sets the memory address specified by the key to hold the input value. The 
	 * memory for the RISC AR5 holds 256 addressable bits, each capable of storing 8 bits
	 * of data. Therefore, this method will throw an exception if the address key is 
	 * not a string object representing a number in this domain [0-255]. Moreover, an 
	 * exception will also be thrown if an address in trying to be loaded by a value
	 * that is not of 8 bits.
	 * @param addressKey
	 * @param addressValue
	 * @throws InvalidAddressValueException 
	 * @throws InvalidAddressException 
	 */
	public void setAddress(String addressKey, String addressValue) throws InvalidAddressValueException, InvalidAddressException{
		//HashMap returns false with the use of this method if the input key is not defined.
		//If the addressKey parameter is not a string representation of 0-255
		//it will return false and we can pick up the exception.
		if (this.memory.containsKey(addressKey)){
			//Must be 8-bit binary code to enter the memory address.
			if(addressValue.matches("[0-1]{8}")){
				memory.put(addressKey, addressValue);
			}
			else{
				throw new InvalidAddressValueException("The specified value- "+ addressValue+
						"- is not of considered valid for the RISC AR5. The value should be" +
						"an binary string of 8 digits.");
			}
		}
		else{
			throw new InvalidAddressException("The specified address -"+ addressKey+ "is not" +
					"valid for the RISC AR5. Valid representations for addressing memory" +
					"include any string representation in the range 0-255.");
		}
	}

	/**
	 * Invokes the file validation method to verify if the input file
	 *can be stored into memory.
	 * 
	 * @param filePath
	 */
	public void loadMemory(String filePath){
		try {
			if(isValidInputFile(filePath)){
				try {
					Scanner scanner = new Scanner(new File(filePath));
					String currentLine = "";
					int counter =0;
					while (scanner.hasNext()){
						currentLine = scanner.next();
						if (!currentLine.trim().isEmpty()){
							try{
								//Inserts first two hexadecimal digits of the 4 digit hex instruction into memory.
								this.setAddress(counter+"", NotationConversion.hexTo8BitBinary(currentLine.substring(0,2)));
								counter++;
								//Inserts last two hexadecimal digits of the 4 digit hex instruction into the subsequent
								//memory address.
								this.setAddress(counter+"", NotationConversion.hexTo8BitBinary(currentLine.substring(2)));
								counter++;
							}
							catch(InvalidAddressException e){
								//Address that is not in the range of 0-255 is being added!
								e.printStackTrace();

							}
							catch(InvalidAddressValueException e){
								//A word that is not 8-bit is trying to be added!
								e.printStackTrace();
							}
						}

					}
				} catch (FileNotFoundException e) {
					// Specified File cannot be found at specified location or cannot be opened. 
					e.printStackTrace();
				}	
			}
		} catch (NumberFormatException e) {
			// Detected a line in the input file that was not in hexadecimal notation or was not of 4 digits.
			e.printStackTrace();
		} catch (InvalidMemoryFileSizeException e) {
			// The amount of 8-bit words exceed 128 and, therefore, will not fit in our memory.
			e.printStackTrace();
		}
	}


	/**
	 * Iterates the entire input file, verifying that it contains 128 8-bit words
	 * or less. These words must represent hexadecimal words of exactly 4 digits.
	 * If the entire file is read without triggering any of the exceptions, the file
	 * is considered valid and the return method will be true. Note that if it is not 
	 * valid, it will not return false, it will return an exception that will be handled
	 * by the loadMemory method of this class.
	 * @param filePath
	 * @return
	 * @throws InvalidMemoryFileSizeException
	 * @throws NumberFormatException
	 */
	public boolean isValidInputFile(String filePath)throws InvalidMemoryFileSizeException, NumberFormatException{
		int count = 0;
		try {
			Scanner scanner = new Scanner(new File(filePath));
			String currentLine = "";
			while (scanner.hasNext()){
				currentLine = scanner.next();

				//Line must match regular expression if it is not empty.
				if (!currentLine.matches("[0-9a-fA-F]{4}")&& !currentLine.trim().isEmpty()){
					throw new NumberFormatException("The following line is not a 4 digit Hex Number: "+
							currentLine);
				}

				//If a line is empty, counter ignores it: no operation.
				if (!currentLine.trim().isEmpty()){
					count++;
				}

				//There must not be more than 128 8-bit words;
				if (count > 64){
					throw new InvalidMemoryFileSizeException("Input file: +"+filePath+" contains " +
							"more than 128 8-bit words.");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;

	}


	/**
	 * Utility method-
	 * Prints the entire contents the memory into a file called memoryContents. This
	 * file will be rewritten every time this method is invoked and its location is at the
	 * root of this project. 
	 */
	public void printMemoryFile(){
		try {
			FileWriter writer = new FileWriter("./memoryContents.txt");
			for (int i=0; i<256; i++){
				writer.append(memory.get(""+i)+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Utility method-
	 * Returns the entire contents of the memory as a string of the form:
	 * 00: 00110011
	 * 02: 01111101
	 * .
	 * .
	 * .
	 * FF: 01001110
	 */
	public String memoryToString(){
		String str = "";		
		for (int i=0; i<256; i++)
			str += NotationConversion.intToHex(i).toUpperCase() + ": " + memory.get(""+i) + "\n";
		return str;
	}

}



