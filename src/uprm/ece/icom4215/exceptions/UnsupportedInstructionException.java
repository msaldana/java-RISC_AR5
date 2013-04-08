package uprm.ece.icom4215.exceptions;

/**
 * Signals that the instruction specified by a parameter cannot be 
 * executed because it is not part of the RISC AR5 processor. This 
 * exception will be thrown by the next(instruction) operation of 
 * {@link InstructionSet}
 *
 */
@SuppressWarnings("serial")
public class UnsupportedInstructionException extends Exception {

	
	/**
	 * Constructs a <code>UnsupportedInstructionException</code> with 
	 * <code>null</code> as its error detail message.
	 */
	public UnsupportedInstructionException(){
		super();
	}
	
	/**
	 * Constructs a <code>UnsupportedInstructionException</code> with 
	 * the specified detail message. The string <code>s</code> can be
	 * retrieved later by the <code>{@link java.lang.Throwable#getMessage}
	 * </code> method of class <code>java.lang.Throwable</code>.
	 * 
	 * @param s the description message.
	 * 
	 */
	public UnsupportedInstructionException(String s){
		super(s);
	}
	
	/**
	 * Constructs a <code>UnsupportedInstructionException</code> with a detail
	 * message consisting of the given pathname string followed by the given 
	 * reason string. If the <code>reason</code> argument is <code>null</code>
	 * then it will be omitted. 
	 * @param path
	 * @param reason
	 */
	public UnsupportedInstructionException(String path, String reason){
		super(path + ((reason == null)
               ? ""
               : " (" + reason + ")"));
	}

}

