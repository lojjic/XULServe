package net.lojjic.xul.rdf.impl;

import org.openrdf.model.*;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.apache.xerces.impl.dv.util.Base64;
import net.lojjic.xul.rdf.*;

/**
 * Utility methods for Sesame-related operations
 */
public class SesameUtils {

	/**
	 * Utility to convert a Mozilla RDFNode to a Sesame Value of the appropriate type.
	 */
	public static Value toValue(Repository repository, RDFNode rdfNode) {
		// Resources:
		if(rdfNode instanceof RDFResource) {
			return toResource(repository, (RDFResource)rdfNode);
		}
		// Literals:
		return toLiteral(repository, rdfNode);
	}

	/**
	 * Utility to convert a Mozilla RDFNode to a Sesame Literal
	 */
	public static Literal toLiteral(Repository repository, RDFNode rdfNode) {
		ValueFactory factory = repository.getValueFactory();
		if(rdfNode instanceof RDFLiteral) {
			return factory.createLiteral(((RDFLiteral)rdfNode).getValue());
		}
		if(rdfNode instanceof RDFInt) {
			return factory.createLiteral(String.valueOf(((RDFInt)rdfNode).getValue()), XMLSchema.INT);
		}
		if(rdfNode instanceof RDFDate) {
			URI datatype = XMLSchema.DATETIME;
			return null; //TODO create literal with xsd:datetime-formatted string
		}
		if(rdfNode instanceof RDFBlob) {
			String base64 = Base64.encode(((RDFBlob)rdfNode).getValue());
			return factory.createLiteral(base64, XMLSchema.BASE64BINARY);
		}
		throw new IllegalArgumentException("Cannot convert given object to a Literal.");
	}

	/**
	 * Utility to convert a Mozilla RDFResource to a Sesame Resource.
	 * Same as toURI() but it can return a BNode.
	 */
	public static Resource toResource(Repository repository, RDFResource mozResource) {
		// URI resource:
		if(mozResource.getValue() != null) {
			return toURI(repository, mozResource);
		}
		// Blank node; use the object's hashcode as the bnode id
		return repository.getValueFactory().createBNode(String.valueOf(mozResource.hashCode()));
	}

	/**
	 * Utility to convert a Mozilla RDFResource to a Sesame URI
	 */
	public static URI toURI(Repository repository, RDFResource mozResource) {
		if(mozResource.getValue() == null) {
			throw new IllegalArgumentException("Cannot convert an anonymous resource to a URI.");
		}
		return repository.getValueFactory().createURI(mozResource.getValue());
	}

	/**
	 * Utility to convert a Sesame Resource to a Mozilla RDFResource
	 */
	public static RDFResource toRDFResource(RDFService rdfService, Resource resource) {
		// Blank nodes:
		if(resource instanceof BNode) {
			// TODO figure out how to guarantee the same instance for the same logical bnode
			return rdfService.getAnonymousResource();
		}
		// URI nodes:
		return rdfService.getResource(resource.toString());
	}

	/**
	 * Utility to convert a Sesame Value to a Mozilla RDFNode
	 */
	public static RDFNode toRDFNode(RDFService rdfService, Value value) {
		// Resources:
		if(value instanceof Resource) {
			return toRDFResource(rdfService, (Resource)value);
		}
		// Literals:
		Literal literal = (Literal)value;
		if(XMLSchema.INT.equals(literal.getDatatype())) {
			return rdfService.getIntLiteral(Integer.parseInt(literal.getLabel()));
		}
		if(XMLSchema.DATETIME.equals(literal.getDatatype())) {
			return rdfService.getDateLiteral(0); //TODO parse xsd:datetime string into time
		}
		if(XMLSchema.BASE64BINARY.equals(literal.getDatatype())) {
			byte[] bytes = Base64.decode(literal.getLabel());
			return rdfService.getBlobLiteral(bytes, bytes.length);
		}
		return rdfService.getLiteral(literal.getLabel());
	}

	/**
	 * Utility to build a Sesame Statement from the given Mozilla triple parts
	 */
	protected static Statement toStatement(Repository repository, RDFResource subject, RDFResource predicate, RDFNode object) {
		return repository.getValueFactory().createStatement(toResource(repository, subject), toURI(repository, predicate), toValue(repository, object));
	}

	/**
	 * Execute the given {@link net.lojjic.xul.rdf.impl.SesameConnectionCallback} within
	 * a single connection, handling all cleanup tasks.  Any exceptions thrown from the
	 * callback are rethrown as a {@link net.lojjic.xul.rdf.impl.RDFException}.
	 */
	public static <T> T execute(Repository repository, SesameConnectionCallback<T> callback) {
		try {
			RepositoryConnection con = repository.getConnection();
			try {
				return callback.doInConnection(con);
			}
			finally {
				con.close();
			}
		}
		catch (Exception e) {
			throw new RDFException(e);
		}
	}
}
