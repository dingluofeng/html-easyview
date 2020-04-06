package com.eason.html.easyview.core.statictable.tablecss;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月7日 下午8:53:43
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月7日
 * @modify by reason:{方法名}:{原因}
 */
public class BoxTablecss implements ITablecss {

	@Override
	public String cssText() {
		StringBuilder css = new StringBuilder();
		css.append("table tr:first-child th:first-child {\r\n" + "            border-top-left-radius: 12px;\r\n"
				+ "        }\r\n" + "\r\n" + "        table tr:first-child th:last-child {\r\n"
				+ "            border-top-right-radius: 12px;\r\n" + "        }\r\n"
				+ "        table tr:last-child td:first-child {\r\n"
				+ "            border-bottom-left-radius: 12px;\r\n" + "        }\r\n" + "\r\n"
				+ "        table tr:last-child td:last-child {\r\n"
				+ "            border-bottom-right-radius: 12px;\r\n" + "        }");

		css.append(
				"table \r\n" + "{\r\n" + "    font-family: \"Lucida Sans Unicode\", \"Lucida Grande\", Sans-Serif;\r\n"
						+ "    font-size: 12px;\r\n" + "    /*margin: 45px;*/\r\n" +
						// " width: 480px;\r\n" +
						"    text-align: left;\r\n" + "    border-collapse: separate;\r\n" + "}\r\n" + "table  th\r\n"
						+ "{\r\n" + "    font-size: 13px;\r\n" + "    font-weight: normal;\r\n"
						+ "    padding: 8px;\r\n" + "    background: #b9c9fe;\r\n"
						+ "    border-top: 4px solid #aabcfe;\r\n" + "    border-bottom: 1px solid #fff;\r\n"
						+ "    color: #039;\r\n" + "}\r\n" + "table  td\r\n" + "{\r\n" + "    padding: 8px;\r\n"
						+ "    background: #e8edff; \r\n" + "    /*border-bottom: 1px solid #fff;*/\r\n"
						+ "    color: #669;\r\n" + "    border-top: 1px solid transparent;\r\n" + "}\r\n"
						+ "table  tr:hover td\r\n" + "{\r\n" + "    background: #d0dafd;\r\n" + "    color: #339;\r\n"
						+ "}");
		return css.toString();
	}

}
