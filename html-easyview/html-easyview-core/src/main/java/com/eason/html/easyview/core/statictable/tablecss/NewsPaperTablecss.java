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
public class NewsPaperTablecss implements ITablecss {

    @Override
    public String cssText() {
        StringBuilder css=new StringBuilder();
        css.append("table \r\n" + 
                "{\r\n" + 
                "    font-family: \"Lucida Sans Unicode\", \"Lucida Grande\", Sans-Serif;\r\n" + 
                "    font-size: 12px;\r\n" + 
                "    margin: 45px;\r\n" +
                // " width: 480px;\r\n" +
                "    text-align: left;\r\n" + 
                "    border-collapse: collapse;\r\n" + "    border: 1px solid #69c;\r\n" +
                "}\r\n" + 
                "table  th\r\n" + 
                "{\r\n" + 
                "    padding: 12px 17px 12px 17px;\r\n" +
                "    font-weight: normal;\r\n" + 
                "    font-size: 14px;\r\n" +
                "    color: #039;\r\n" + 
                "    border-bottom: 1px dashed #69c;\r\n" +
                "}\r\n" + 
                "table  td\r\n" + 
                "{\r\n" + 
                "    padding: 7px 17px 7px 17px;\r\n" +
                "    color: #669;\r\n" + 
                "}\r\n" + 
                "table  tbody tr:hover td\r\n" +
                "{\r\n" + 
                "    color: #339;\r\n" + 
                "    background: #d0dafd;\r\n" + 
                "}");
        return css.toString();
    }

}
