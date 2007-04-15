package net.lojjic.xul.rdf.impl;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.*;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Iterator;

/**
 * Unit tests for {@link net.lojjic.xul.rdf.impl.RDFSerializerImpl}
 */
public class RDFSerializerImplTest extends TestCase {

	private static final String RDFXML_DOC_URI = "classpath:/net/lojjic/xul/rdf/impl/animals.rdf";


	public void testSerialize() throws Exception {
		RDFService rdfService = new RDFServiceImpl();
		RDFDataSource dataSource = rdfService.getDataSource(RDFXML_DOC_URI);

		File file = File.createTempFile("xul-rdf-RDFSerializerImplTest-", ".rdf");
		OutputStream out = new FileOutputStream(file);
		try {
			// Serialize out to temp file:
			new RDFSerializerImpl().serialize(dataSource, out);

			// Create a new datasource from the temp file:
			RDFDataSource dataSource2 = rdfService.getDataSource("file:" + file.getAbsolutePath());

			// Compare the contents of the two datasources:
			Iterator<RDFResource> subjects = dataSource.getAllResources();
			while(subjects.hasNext()) {
				RDFResource subject = subjects.next();
				Iterator<RDFResource> arcs = dataSource.arcLabelsOut(subject);
				while(arcs.hasNext()) {
					RDFResource arc = arcs.next();
					Iterator<RDFNode> targets = dataSource.getTargets(subject, arc, true);
					while(targets.hasNext()) {
						assertTrue(dataSource2.hasAssertion(subject, arc, targets.next(), true));
					}
				}
			}
		}
		finally {
			out.close();
			file.delete();
		}
	}

}
