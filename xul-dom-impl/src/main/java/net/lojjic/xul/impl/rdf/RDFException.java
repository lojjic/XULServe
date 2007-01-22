package net.lojjic.xul.impl.rdf;

/**
 * Runtime exception class for RDF operations
 */
public class RDFException extends RuntimeException {

	public RDFException() {
	}

	public RDFException(Throwable cause) {
		super(cause);
	}

	public RDFException(String message) {
		super(message);
	}

	public RDFException(String message, Throwable cause) {
		super(message, cause);
	}

}
