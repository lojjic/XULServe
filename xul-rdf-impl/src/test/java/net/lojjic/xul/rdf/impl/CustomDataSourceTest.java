package net.lojjic.xul.rdf.impl;

import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.RDFDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.model.Resource;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.URI;
import org.openrdf.model.Literal;
import junit.framework.TestCase;

/**
 * Unit tests for custom datasource registration
 */
public class CustomDataSourceTest extends TestCase {

	private static final String DATASOURCE_NAME = "my-kickass-custom-datasource";

	private static BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("net/lojjic/xul/rdf/impl/CustomDataSourceTestBeans.xml"));

	public void testIt() throws Exception {
		RDFService rdfService = (RDFService)beanFactory.getBean("rdfService");
		RDFDataSource dataSource = rdfService.getDataSource("rdf:" + DATASOURCE_NAME);

		assertTrue(
			dataSource.hasAssertion(
				rdfService.getResource("http://lojjic.net/rdf/my-subject"),
				rdfService.getResource("http://lojjic.net/rdf/my-predicate"),
				rdfService.getLiteral("my-object-literal"),
				true
			)
		);
		assertFalse(
			dataSource.hasAssertion(
				rdfService.getResource("http://lojjic.net/rdf/my-subject"),
				rdfService.getResource("http://lojjic.net/rdf/my-predicate"),
				rdfService.getLiteral("does-not-exist"),
				true
			)
		);
	}

	public static class CustomDataSource extends RDFMemoryDataSourceImpl {
		public CustomDataSource(RDFService rdfService) {
			super(rdfService);
			// Add data:
			SesameUtils.execute(
					getSesameRepository(),
					new SesameConnectionCallback() {
						public Object doInConnection(RepositoryConnection conn) throws Exception {
							ValueFactory valFac = conn.getRepository().getValueFactory();
							URI source = valFac.createURI("http://lojjic.net/rdf/my-subject");
							URI arc = valFac.createURI("http://lojjic.net/rdf/my-predicate");
							Literal target = valFac.createLiteral("my-object-literal");
							conn.add(source, arc, target);
							return null;
						}
					}
			);
		}
	}

}
