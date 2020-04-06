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
public class VerZebraTablecss implements ITablecss {

//    <colgroup>
//    <col class="vzebra-odd" />
//    <col class="vzebra-even" />
//    <col class="vzebra-odd" />
//    <col class="vzebra-even" />
//</colgroup>
//<thead>
//    <tr>
//        <th scope="col" id="vzebra-comedy">Comedy</th>
//        <th scope="col" id="vzebra-adventure">Adventure</th>
//        <th scope="col" id="vzebra-action">Action</th>
//        <th scope="col" id="vzebra-children">Children</th>
//    </tr>
//</thead>
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

		css.append("table \r\n" + "{\r\n"
				+ "    font-family: \"Lucida Sans Unicode\", \"Lucida Grande\", Sans-Serif;\r\n"
				+ "    font-size: 12px;\r\n" + "    margin: 45px;\r\n" + "    width: 480px;\r\n"
				+ "    text-align: left;\r\n" + "    border-collapse: collapse;\r\n" + "}\r\n" + "table  th\r\n"
				+ "{\r\n" + "    font-size: 14px;\r\n" + "    font-weight: normal;\r\n" + "    padding: 12px 15px;\r\n"
				+ "    border-right: 1px solid #fff;\r\n" + "    border-left: 1px solid #fff;\r\n"
				+ "    color: #039;\r\n" + "}\r\n" + "table  td\r\n" + "{\r\n" + "    padding: 8px 15px;\r\n"
				+ "    border-right: 1px solid #fff;\r\n" + "    border-left: 1px solid #fff;\r\n"
				+ "    color: #669;\r\n" + "}\r\n" + ".vzebra-odd\r\n" + "{\r\n" + "    background: #eff2ff;\r\n"
				+ "}\r\n" + ".vzebra-even\r\n" + "{\r\n" + "    background: #e8edff;\r\n" + "}\r\n"
				+ "table  #vzebra-adventure, table  #vzebra-children\r\n" + "{\r\n" + "    background: #d0dafd;\r\n"
				+ "    border-bottom: 1px solid #c8d4fd;\r\n" + "}\r\n"
				+ "table  #vzebra-comedy, table  #vzebra-action\r\n" + "{\r\n" + "    background: #dce4ff;\r\n"
				+ "    border-bottom: 1px solid #d6dfff;\r\n" + "}");
		return css.toString();
	}

}
