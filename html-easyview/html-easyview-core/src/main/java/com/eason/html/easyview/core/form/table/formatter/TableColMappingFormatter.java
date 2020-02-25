/**
 * 
 */
package com.eason.html.easyview.core.form.table.formatter;

import java.util.Map;

/**
 * @author dingluofeng
 *
 */
public interface TableColMappingFormatter {
	
	String functionName();
	
    // String columnField();

	Map<String, String> mapping();

}
