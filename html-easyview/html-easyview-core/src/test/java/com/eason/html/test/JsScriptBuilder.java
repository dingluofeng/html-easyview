/**
 * 
 */
package com.eason.html.test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.io.Files;

/**
 * @author dingluofeng
 *
 */
public class JsScriptBuilder {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("D://a.js");
		List<String> readLines = Files.readLines(file, Charset.defaultCharset());
		for (String line : readLines) {
			System.out.println("script.add(Text.of(\""+line.replaceAll("\"", "'")+"\"));");
		}
	}

}
