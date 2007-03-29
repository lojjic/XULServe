package net.lojjic.xpcom;


public class XPCOMObject {

	private Object wrappedObject;

	public XPCOMObject(Object wrappedObject) {
		this.wrappedObject = wrappedObject;
	}

	@SuppressWarnings({"unchecked"})
	public <T> T queryInterface(Class<T> interfaceClass) {
		if(!interfaceClass.isInterface()) {
			throw new IllegalArgumentException("Class " + interfaceClass.getName() + " is not an interface.");
		}
		if(!interfaceClass.isInstance(wrappedObject)) {
			throw new IllegalArgumentException("The object " + wrappedObject + " " +
					"is not an instance of the interface " + interfaceClass.getName());
		}
		return (T)wrappedObject;
	}

}
