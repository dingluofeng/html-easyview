package com.eason.html.easyview.core.widget.css;

import com.eason.html.easyview.core.widget.Text;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月21日 下午8:53:09
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月21日
 * @modify by reason:{方法名}:{原因}
 */
public class BaseStyle {

	public static CssStyle of() {
		CssStyle cssstyle = CssStyle.of("@charset \"UTF-8\";");
		cssstyle.add(Text.of(".bootstrap-table .table:not(.table-condensed) > tbody > tr > td {"));
		cssstyle.add(Text.of("   padding: 5px !important;"));
		cssstyle.add(Text.of("}"));
		cssstyle.add(Text.of(".table > tbody > tr > td {"));
		cssstyle.add(Text.of("   vertical-align: middle;"));
		cssstyle.add(Text.of("}"));
		cssstyle.add(Text.of("button > span {"));
		cssstyle.add(Text.of("   margin-right: 5px;"));
		cssstyle.add(Text.of("}"));
		cssstyle.add(Text.of("input[type='checkbox'], button, a {"));
		cssstyle.add(Text.of("    cursor: pointer;"));
		cssstyle.add(Text.of("    outline: none;"));
		cssstyle.add(Text.of("}"));
		cssstyle.add(Text.of(".input-group label {"));
		cssstyle.add(Text.of("    margin-right: 10px;"));
		cssstyle.add(Text.of("}"));
		cssstyle.add(Text.of(".input-group input[type='text'], select {"));
		cssstyle.add(Text.of("    height: 30px;"));
		cssstyle.add(Text.of("    width: 153px;"));
		cssstyle.add(Text.of("    padding-left: 5px;"));
		cssstyle.add(Text.of("    margin-right: 10px;"));
		cssstyle.add(Text.of("    border-radius: 3px;"));
		cssstyle.add(Text.of("    border: 1px solid #eee;"));
		cssstyle.add(Text.of("}"));

		cssstyle.add(Text.of(".pull-right.pagination-detail{"));
		cssstyle.add(Text.of("	display:none;"));
		cssstyle.add(Text.of("}"));
		return cssstyle;
	}
}
