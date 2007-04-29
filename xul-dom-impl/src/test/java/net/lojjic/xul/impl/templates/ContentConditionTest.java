package net.lojjic.xul.impl.templates;

import junit.framework.TestCase;
import net.lojjic.xul.rdf.RDFDataSource;
import net.lojjic.xul.rdf.RDFNode;
import net.lojjic.xul.rdf.RDFResource;
import net.lojjic.xul.rdf.RDFService;
import net.lojjic.xul.rdf.impl.RDFServiceImpl;
import org.apache.xml.utils.UnImplNode;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for {@link ContentCondition}
 */
public class ContentConditionTest extends TestCase {

	public void testApply() throws Exception {
		RDFService rdfService = new RDFServiceImpl();
		RDFDataSource dataSource = rdfService.getDataSource("classpath:net/lojjic/xul/impl/templates/photos.rdf");

		Element element = new UnImplNode() {
			public String getAttribute(String name) {
				if(name.equals("uri")) {
					return "?start";
				}
				return super.getAttribute(name);
			}
		};

		ContentCondition contentCondition = new ContentCondition(rdfService, element);
		List<Map<String, RDFNode>> varsList = new ArrayList<Map<String, RDFNode>>();
		RDFResource start = rdfService.getResource("http://www.xulplanet.com/rdf/myphotos");
		contentCondition.applyToVariablesList(dataSource, varsList, start);

		assertEquals(1, varsList.size());
		assertEquals(1, varsList.get(0).size());
		assertEquals(start, varsList.get(0).get("?start"));
	}

}
