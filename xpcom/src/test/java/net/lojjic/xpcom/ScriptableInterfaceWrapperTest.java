package net.lojjic.xpcom;

import junit.framework.TestCase;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Function;

/**
 * Test cases for {@link net.lojjic.xpcom.ScriptableInterfaceWrapper}
 */
public class ScriptableInterfaceWrapperTest extends TestCase {

	public void testIt() throws Exception {
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();
			Function enumFunc = cx.compileFunction(scope, "function(obj) {var str=':'; for(var i in obj) {str += i + ':'} return str;}", "<test>", 1, null);
			Object obj1 = new ScriptableInterfaceWrapper(scope, new Impl(), IfaceOne.class);
			Object obj2 = new ScriptableInterfaceWrapper(scope, new Impl(), IfaceTwo.class);
			String result1 = (String)enumFunc.call(cx, scope, scope, new Object[]{obj1});
			String result2 = (String)enumFunc.call(cx, scope, scope, new Object[]{obj2});
			assertTrue(result1.contains("methodOne"));
			assertTrue(result1.contains("propertyOne"));
			assertTrue(result1.contains("getPropertyOne"));
			assertTrue(result1.contains("setPropertyOne"));
			assertFalse(result1.contains("methodTwo"));
			assertFalse(result1.contains("propertyTwo"));
			assertFalse(result1.contains("getPropertyTwo"));
			assertFalse(result1.contains("setPropertyTwo"));
			assertTrue(result2.contains("methodTwo"));
			assertTrue(result2.contains("propertyTwo"));
			assertTrue(result2.contains("getPropertyTwo"));
			assertTrue(result2.contains("setPropertyTwo"));
			assertFalse(result2.contains("methodOne"));
			assertFalse(result2.contains("propertyOne"));
			assertFalse(result2.contains("getPropertyOne"));
			assertFalse(result2.contains("setPropertyOne"));
		}
		finally {
			Context.exit();
		}

	}

	private static interface IfaceOne {
		void methodOne(String arg);
		void setPropertyOne(String prop);
		String getPropertyOne();
	}

	private static interface IfaceTwo {
		String methodTwo();
		void setPropertyTwo(int arg);
		int getPropertyTwo();
	}

	private static class Impl implements IfaceOne, IfaceTwo {
		private String propertyOne;
		private int propertyTwo;

		public void methodOne(String arg) {
		}

		public void setPropertyOne(String prop) {
			this.propertyOne = prop;
		}

		public String getPropertyOne() {
			return propertyOne;
		}

		public String methodTwo() {
			return "Method two called";
		}

		public void setPropertyTwo(int arg) {
			this.propertyTwo = arg;
		}

		public int getPropertyTwo() {
			return propertyTwo;
		}
	}

}
