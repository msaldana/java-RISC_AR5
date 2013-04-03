package uprm.ece.icom4215.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import uprm.ece.icom4215.util.NotationConversion;

public class Memory {

	//public static final int BUS_SIZE = 8;

	HashMap<String, String> memory = new HashMap<String, String>();


	/**
	 * Main constructor: initializes memory addresses with an empty string. This is done with the purpose 
	 * to simulate that no information is stored in the initial state of memory.
	 */
	public Memory(){
		for(int i =0; i<256; i++)
			memory.put(i+"","");
	}

	/**
	 * Reset all memory addresses.
	 */
	public void reset(){
		for(int i = 0; i< 256; i++)
			memory.put(i+"","");
	}

	/**
	 * Returns the 8 bit word located at the given address location.
	 * Usage: Valid keys are String objects representing the numbers
	 * 0-255. Any other key will return the string "null".
	 * @param addressKey
	 * @return
	 */
	public String getAddress(String addressKey){
		return this.memory.get(addressKey);
	}

	public void setAddress(String addressKey, String addressValue){
		//Verify if a valid key was given
		if (!this.memory.get(addressKey).equals("null")){
			if(addressValue.length()==8){
				memory.put(addressKey, addressValue);
			}
			else{
				//Invalid Size Exception
			}
		}
		else{
			//Invalid Key Exception
		}
	}

	public void loadMemory(String filePath){
		if(isValidInputFile(filePath)){
				try {
					Scanner scanner = new Scanner(new File(filePath));
					String currentLine = "";
					int counter =0;
					while (scanner.hasNext()){
						currentLine = scanner.next();
						if (!currentLine.trim().isEmpty()){
							System.out.println("Inserting: "+ NotationConversion.hexToBinary(currentLine)+" into "+counter);
							this.setAddress(counter+"", NotationConversion.hexTo8BitBinary(currentLine.substring(0,2)));
							counter++;
							this.setAddress(counter+"", NotationConversion.hexTo8BitBinary(currentLine.substring(2)));
							counter++;
						}
						
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
	}


	public boolean isValidInputFile(String filePath){
		int count = 0;
		try {
			Scanner scanner = new Scanner(new File(filePath));
			String currentLine = "";
			while (scanner.hasNext()){
				currentLine = scanner.next();

				if (!currentLine.matches("[0-9a-fA-F]{4}")&& !currentLine.trim().isEmpty()){
					return false;
				}
				if (!currentLine.trim().isEmpty()){
					count++;
				}

				//There must not be more than 256 8-bit words;
				if (count > 128){
					return false;
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}


	public void printMemoryFile(){
		try {
			FileWriter writer = new FileWriter("./memoryContents.txt");
			for (int i=0; i<256; i++){
				writer.append(memory.get(""+i)+"\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public static void main(String[] args){
		Memory mem = new Memory();
		//mem.printMemoryFile();
		System.out.println(mem.isValidInputFile("./test.txt"));
		mem.loadMemory("./test.txt");
		mem.printMemoryFile();
	}
}



