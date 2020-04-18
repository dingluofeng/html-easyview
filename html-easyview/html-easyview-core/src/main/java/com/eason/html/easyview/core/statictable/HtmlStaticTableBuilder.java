package com.eason.html.easyview.core.statictable;

import java.util.ArrayList;
import java.util.List;

import com.eason.html.easyview.core.statictable.model.StaticTableData;
import com.eason.html.easyview.core.statictable.model.StaticTableData.StaticTableColumn;
import com.eason.html.easyview.core.statictable.model.StaticTableData.StaticTableRow;
import com.eason.html.easyview.core.statictable.model.StaticTableData.TableCell;
import com.eason.html.easyview.core.statictable.tablecss.BoxTablecss;
import com.eason.html.easyview.core.statictable.tablecss.HorMinimalistTablecss;
import com.eason.html.easyview.core.statictable.tablecss.HorZebraTablecss;
import com.eason.html.easyview.core.statictable.tablecss.ITablecss;
import com.eason.html.easyview.core.statictable.tablecss.NewsPaperTablecss;
import com.eason.html.easyview.core.statictable.tablecss.VerMinimalistTablecss;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年1月7日 下午7:54:31
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月7日
 * @modify by reason:{方法名}:{原因}
 */
public class HtmlStaticTableBuilder {

	public static final String ENTER_R_N = "\r\n";
	
	private final StringBuilder html = new StringBuilder();

	HtmlHeadBiulder htmlHeadBiulder;

	HtmlBodyBiulder htmlBodyBiulder;

	public HtmlHeadBiulder headBuider() {
		if (htmlHeadBiulder == null) {
			htmlHeadBiulder = new HtmlHeadBiulder();
		}
		return htmlHeadBiulder;
	}

	public HtmlBodyBiulder bodyBuilder() {
		if (htmlBodyBiulder == null) {
			htmlBodyBiulder = new HtmlBodyBiulder();
		}
		return htmlBodyBiulder;
	}

	public String build() {
		html.append("<html>").append(ENTER_R_N);
		if (htmlHeadBiulder != null) {
			html.append(htmlHeadBiulder.build());
		}
		if (htmlBodyBiulder != null) {
			html.append(htmlBodyBiulder.build());
		}
		html.append("</html>").append(ENTER_R_N);
		return html.toString();
	}

	public static class HtmlBodyBiulder {

		HtmlBody body = new HtmlBody();

		public HtmlBodyBiulder newTableBuilder(StaticTableData tableData) {
			HtmlTableBiulder tableBiulder = body.newTableBuilder();
			tableBiulder.title(tableData.getTableTitle());
			tableBiulder.columns(tableData.getColumnTitles());
			tableBiulder.rows(tableData.getRows());
			return this;
		}

		private String build() {
			return body.build();
		}
	}

	public static class HtmlHeadBiulder {

		private HtmlHead head = new HtmlHead();

		public HtmlHeadBiulder title(String title) {
			head.title = title;
			return this;
		}

		private String build() {
			return head.build();
		}

		public HtmlHeadBiulder tableStyle(TableStyle style) {
			head.htmlStyle = style;
			return this;
		}

	}

	public static class HtmlTableBiulder {

		HtmlTable table = new HtmlTable();

		public HtmlTableBiulder title(String title) {
			table.tableTitle = title;
			return this;
		}

		public HtmlTableBiulder columns(List<StaticTableColumn> tableColumns) {
			table.columns = tableColumns;
			return this;
		}

		public HtmlTableBiulder rows(List<StaticTableRow> tableRows) {
			table.rows = tableRows;
			return this;
		}

		String build() {
			return table.build();
		}

	}

	public static class HtmlBody {

		List<HtmlTableBiulder> tableBiulders = new ArrayList<>();

		public HtmlTableBiulder newTableBuilder() {
			HtmlTableBiulder tableBuilder = new HtmlTableBiulder();
			tableBiulders.add(tableBuilder);
			return tableBuilder;
		}

		String build() {
			StringBuilder body = new StringBuilder();
			body.append("<body>").append(ENTER_R_N);
			for (HtmlTableBiulder tableBiulder : tableBiulders) {
				if (tableBiulder != null) {
					body.append(tableBiulder.build());
				}
				body.append("<hr style=\"height:1px;border:none;border-top:1px dashed #0066CC;\" />").append(ENTER_R_N);
			}
			body.append("</body>").append(ENTER_R_N);
			return body.toString();
		}

	}

	public static class HtmlHead {

		String title;

		TableStyle htmlStyle;

		String build() {
			StringBuilder head = new StringBuilder();
			head.append("<head>").append(ENTER_R_N);
			head.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">").append(ENTER_R_N);
			if (title != null) {
				head.append("<h1>").append(ENTER_R_N);
				head.append(title);
				head.append("</h1>").append(ENTER_R_N);
			}
			if (htmlStyle != null) {
				head.append(htmlStyle.build());
			}
			head.append("</head>").append(ENTER_R_N);
			return head.toString();
		}

	}

	public static class TableStyle {

		public static final ITablecss HOR_MINIMALIST_CSS = new HorMinimalistTablecss();

		public static final ITablecss VER_MINIMALIST_CSS = new VerMinimalistTablecss();

		public static final ITablecss BOX_TABLE_CSS = new BoxTablecss();

		public static final ITablecss NEWSPAPER_TABLE_CSS = new NewsPaperTablecss();

		public static final ITablecss HOR_ZEBRA_TABLE_CSS = new HorZebraTablecss();

		public static final TableStyle HOR_MINIMALIST_STYLE = new TableStyle(HOR_MINIMALIST_CSS);

		public static final TableStyle VER_MINIMALIST_STYLE = new TableStyle(VER_MINIMALIST_CSS);

		public static final TableStyle BOX_TABLE_STYLE = new TableStyle(BOX_TABLE_CSS);

		public static final TableStyle NEWSPAPER_TABLE_STYLE = new TableStyle(NEWSPAPER_TABLE_CSS);

		public static final TableStyle HOR_ZEBRA_TABLE_STYLE = new TableStyle(HOR_ZEBRA_TABLE_CSS);

		ITablecss tablecss;

		public TableStyle(ITablecss tablecss) {
			super();
			this.tablecss = tablecss;
		}

		String build() {
			StringBuilder css = new StringBuilder();
			if (tablecss != null) {
				css.append("<style type=\"text/css\">").append(ENTER_R_N);
				css.append(tablecss.cssText());
				css.append("</style>").append(ENTER_R_N);
			}
			return css.toString();
		}
	}

	public static class HtmlTable {

		String tableTitle;

		List<StaticTableColumn> columns;

		List<StaticTableRow> rows;

		String build() {
			return buildTable(columns,rows,true);
		}
		
		String buildTable(List<StaticTableColumn> columns,List<StaticTableRow> rows,boolean indexCreate){
			StringBuilder table = new StringBuilder();
			table.append("<table class=\"\" style=\"min-width:300px;margin:5px;\">").append(ENTER_R_N);
			if (tableTitle != null) {
				table.append("<caption>");
				table.append(tableTitle);
				table.append("</caption>");
			}
			boolean indexed=indexCreate && columns.isEmpty();
			buildTableHead(table,columns,rows,indexed);
			buildTableBody(table,rows,indexed);
			table.append("</table>").append(ENTER_R_N);
			return table.toString();
		}

		private final void buildTableBody(StringBuilder table,List<StaticTableRow> rows,boolean indexCreate) {
			if (rows == null) {
				return;
			}
			table.append("<tbody>").append(ENTER_R_N);
			int i = 0;
			for (StaticTableRow rowData : rows) {
				if (i++ % 2 == 0) {
					table.append("<tr class=\"odd\">").append(ENTER_R_N);
				} else {
					table.append("<tr>").append(ENTER_R_N);
				}
				// 列索引
				if (indexCreate) {
					table.append("<td>");
					table.append(i);
					table.append("</td>").append(ENTER_R_N);
				}

				for (TableCell data : rowData.tableCells) {
					table.append("<td>");
					if (data.value instanceof StaticTableData) {
						StaticTableData td=(StaticTableData) data.value;
						table.append(buildTable(td.getColumnTitles(),td.getRows(),false));
					}else {
						table.append(data.value);
					}
					table.append("</td>").append(ENTER_R_N);
				}
				table.append("</tr>").append(ENTER_R_N);
			}
			table.append("</tbody>").append(ENTER_R_N);
		}

		private final void buildTableHead(StringBuilder table,List<StaticTableColumn> columns,List<StaticTableRow> rows,boolean indexCreate) {
			if (columns == null) {
				return;
			}
			table.append("<thead>").append(ENTER_R_N);
			table.append("<tr>").append(ENTER_R_N);
			// 列表索引序列
			if (indexCreate) {
				table.append("<th scope=\"col\">");
				table.append("#");
				table.append("</th>").append(ENTER_R_N);
			}
			// titles
			for (StaticTableColumn col : columns) {
                table.append("<th scope=\"col\"").append(" colspan=\"").append(col.colspan).append("\">");
				table.append(col.title);
				table.append("</th>").append(ENTER_R_N);
			}
			table.append("</tr>").append(ENTER_R_N);
			table.append("</thead>").append(ENTER_R_N);
		}
	}

	public final <T> String buildHtmlTablePage(StaticTableData tableData) {
		return buildHtmlTablePage(tableData, TableStyle.NEWSPAPER_TABLE_STYLE);
	}

	public final <T> String buildHtmlTablePage(StaticTableData tableData, TableStyle htmlStyle) {
		if (tableData.hasData()) {
			return "<html><body><h1>NODATA</h1></body></html>";
		}
		HtmlStaticTableBuilder builder = new HtmlStaticTableBuilder();
		builder.headBuider().title("").tableStyle(htmlStyle);
		builder.bodyBuilder().newTableBuilder(tableData);
		return builder.build();
	}

}
