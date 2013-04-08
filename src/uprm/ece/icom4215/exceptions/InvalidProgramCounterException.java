package uprm.ece.icom4215.exceptions;

/**
 * Signals that the address denoted by a specified parameter cannot be
 * a possible value for the program counter, because it is out of bounds or not even. 
 * This exception will be thrown by the setPC operation in {@link Registers}.
 * In the RISC AR5 the program counter can hold any even address in the range 0-255.
 * Anything other than that will trigger this exception.
 * or into the {@link Registers} map.
 *
 */
@SuppressWarnings("serial")
public class InvalidProgramCounterException extends Exception {

	
	/**
	 * Constructs a <code>InvalidProgramCounterException</code> with 
	 * <code>null</code> as its error detail message.
	 */
	public InvalidProgramCounterException(){
		super();
	}
	
	/**
	 * Constructs a <code>InvalidProgramCounterException</code> with 
	 * the specified detail message. The string <code>s</code> can be
	 * retrieved later by the <code>{@link java.lang.Throwable#getMessage}
	 * </code> method of class <code>java.lang.Throwable</code>.
	 * 
	 * @param s the description message.
	 * 
	 */
	public InvalidProgramCounterException(String s){
		super(s);
	}
	
	/**
	 * Constructs a <code>InvalidProgramCounterException</code> with a detail
	 * message consisting of the given pathname string followed by the given 
	 * reason string. If the <code>reason</code> argument is <code>null</code>
	 * then it will be omitted. 
	 * @param path
	 * @param reason
	 */
	public InvalidProgramCounterException(String path, String reason){
		super(path + ((reason == null)
               ? ""
               : " (" + reason + ")"));
	}

}


