package net.lojjic.xul.xbl.impl;

/**
 * Exception class for XBL exceptions.
 */
public class XBLException extends RuntimeException {

	public XBLException() {
	}

	public XBLException(String message) {
		super(message);
	}

	public XBLException(String message, Throwable cause) {
		super(message, cause);
	}

	public XBLException(Throwable cause) {
		super(cause);
	}
	
}
