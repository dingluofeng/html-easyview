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
public class HorZebraTablecss implements ITablecss {

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
                "    border-collapse: collapse;\r\n" +
                "}\r\n" + 
                "table  th\r\n" + 
                "{\r\n" + 
                "    font-size: 14px;\r\n" +
                "    font-weight: normal;\r\n" + 
                "    padding: 10px 8px;\r\n" +
                "    color: #039;\r\n" + 
                "}\r\n" + 
                "table  td\r\n" + 
                "{\r\n" + 
                "    padding: 8px;\r\n" + 
                "    color: #669;\r\n" + 
                "}\r\n" + 
                "table  .odd\r\n" +
                "{\r\n" + 
                "    background: #e8edff; \r\n" + 
                "}");
        return css.toString();
    }

}
