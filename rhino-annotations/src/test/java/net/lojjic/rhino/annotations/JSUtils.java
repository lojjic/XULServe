package net.lojjic.rhino.annotations;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;

/**
 * Utility methods for working with JavaScript files
 */
public class JSUtils {

	public static void loadJSFile(Context cx, Scriptable scope, String filename) throws IOException {
		InputStream inputStream = JSUtils.class.getResourceAsStream(filename);
		Reader reader = new InputStreamReader(inputStream);
		cx.evaluateReader(scope, reader, filename, 1, null);
	}

}
