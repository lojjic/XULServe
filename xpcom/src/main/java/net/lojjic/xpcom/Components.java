package net.lojjic.xpcom;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Components {

	private Map<String, Class> interfaces = new HashMap<String, Class>();

	private Map<String, Class> classes = new HashMap<String, Class>();


	public Class getInterface(String name) {
		return interfaces.get(name);
	}

	public ClassTemplate getClass(String contractId) {
		Class cls = classes.get(contractId);
		return (cls == null) ? null : new ClassTemplate(cls);
	}

	private static class ClassTemplate {
		private Class wrappedClass;
		public ClassTemplate(Class wrappedClass) {
			this.wrappedClass = wrappedClass;
		}

		public XPCOMObject createInstance() throws IllegalAccessException, InstantiationException {
			return new XPCOMObject(wrappedClass.newInstance());
		}

		public <T> T createInstance(Class<T> iface) throws IllegalAccessException, InstantiationException {
			return createInstance().queryInterface(iface);
		}
	}

}
