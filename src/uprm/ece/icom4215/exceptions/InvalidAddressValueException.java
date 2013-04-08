package uprm.ece.icom4215.exceptions;

/**
 * Signals that the data denoted by a specified parameter cannot be
 * written into the specified addressed memory. This exception will 
 * be thrown by operations writing into the {@link Memory} map or into 
 * the {@link Registers} map.
 *
 */
@SuppressWarnings("serial")
public class InvalidAddressValueException extends Exception {

	
	/**
	 * Constructs a <code>InvalidAddressValueException</code> with 
	 * <code>null</code> as its error detail message.
	 */
	public InvalidAddressValueException(){
		super();
	}
	
	/**
	 * Constructs a <code>InvalidAddressValueException</code> with 
	 * the specified detail message. The string <code>s</code> can be
	 * retrieved later by the <code>{@link java.lang.Throwable#getMessage}
	 * </code> method of class <code>java.lang.Throwable</code>.
	 * 
	 * @param s the description message.
	 * 
	 */
	public InvalidAddressValueException(String s){
		super(s);
	}
	
	/**
	 * Constructs a <code>InvalidAddressValueException</code> with a detail
	 * message consisting of the given pathname string followed by the given 
	 * reason string. If the <code>reason</code> argument is <code>null</code>
	 * then it will be omitted. 
	 * @param path
	 * @param reason
	 */
	public InvalidAddressValueException(String path, String reason){
		super(path + ((reason == null)
               ? ""
               : " (" + reason + ")"));
	}

}

