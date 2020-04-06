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
public class VerMinimalistTablecss implements ITablecss {

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
						+ "    font-size: 12px;\r\n" + "    margin: 45px;\r\n" +
						// " width: 480px;\r\n" +
						"    text-align: left;\r\n" + "    border-collapse: collapse;\r\n" + "}\r\n" + "table  th\r\n"
						+ "{\r\n" + "    padding: 8px 2px;\r\n" + "    font-weight: normal;\r\n"
						+ "    font-size: 14px;\r\n" + "    border-bottom: 2px solid #6678b1;\r\n"
						+ "    border-right: 30px solid #fff;\r\n" + "    border-left: 30px solid #fff;\r\n"
						+ "    color: #039;\r\n" + "}\r\n" + "table  td\r\n" + "{\r\n"
						+ "    padding: 12px 2px 0px 2px;\r\n" + "    border-right: 30px solid #fff;\r\n"
						+ "    border-left: 30px solid #fff;\r\n" + "    color: #669;\r\n" + "}");
		return css.toString();
	}

}
