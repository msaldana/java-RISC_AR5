package uprm.ece.icom4215.exceptions;

/**
 * Signals that the size of the file denoted by a specified pathname
 * is greater than the memory of the RISC AR5. This exception will be 
 * thrown by the {@link Memory} isValid class when a file with the specified 
 * pathname contains more than 128 8-bit word lines. 
 *
 */

@SuppressWarnings("serial")
public class InvalidMemoryFileSizeException extends Exception{
	
	/**
	 * Constructs a <code>InvalidMemoryFileSizeException</code> with 
	 * <code>null</code> as its error detail message.
	 */
	public InvalidMemoryFileSizeException(){
		super();
	}
	
	/**
	 * Constructs a <code>InvalidMemoryFileSizeException</code> with 
	 * the specified detail message. The string <code>s</code> can be
	 * retrieved later by the <code>{@link java.lang.Throwable#getMessage}
	 * </code> method of class <code>java.lang.Throwable</code>.
	 * 
	 * @param s the description message.
	 * 
	 */
	public InvalidMemoryFileSizeException(String s){
		super(s);
	}
	
	/**
	 * Constructs a <code>InvalidMemoryFileSizeException</code> with a detail
	 * message consisting of the given pathname string followed by the given 
	 * reason string. If the <code>reason</code> argument is <code>null</code>
	 * then it will be omitted. 
	 * @param path
	 * @param reason
	 */
	public InvalidMemoryFileSizeException(String path, String reason){
		super(path + ((reason == null)
               ? ""
               : " (" + reason + ")"));
	}

}

