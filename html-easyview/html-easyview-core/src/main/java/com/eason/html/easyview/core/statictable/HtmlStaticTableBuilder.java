package com.eason.html.easyview.core.statictable;

import java.util.ArrayList;
import java.util.List;

import com.eason.html.easyview.core.statictable.model.StaticTableData;
import com.eason.html.easyview.core.statictable.model.StaticTableData.TableDataBuilder;
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
		html.append("<html>").append("\r\n");
		if (htmlHeadBiulder != null) {
			html.append(htmlHeadBiulder.build());
		}
		if (htmlBodyBiulder != null) {
			html.append(htmlBodyBiulder.build());
		}
		html.append("</html>").append("\r\n");
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

		public String build() {
			return body.build();
		}
	}

	public static class HtmlHeadBiulder {

		private HtmlHead head = new HtmlHead();

		HtmlHeadBiulder title(String title) {
			head.title = title;
			return this;
		}

		public String build() {
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

		public HtmlTableBiulder columns(String[] columns) {
			table.columns = columns;
			return this;
		}

		public HtmlTableBiulder rows(List<Object[]> rows) {
			table.rows = rows;
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
			body.append("<body>").append("\r\n");
			for (HtmlTableBiulder tableBiulder : tableBiulders) {
				if (tableBiulder != null) {
					body.append(tableBiulder.build());
				}
				body.append("<hr style=\"height:1px;border:none;border-top:1px dashed #0066CC;\" />").append("\r\n");
			}
			body.append("</body>").append("\r\n");
			return body.toString();
		}

	}

	public static class HtmlHead {

		String title;

		TableStyle htmlStyle;

		String build() {
			StringBuilder head = new StringBuilder();
			head.append("<head>").append("\r\n");
			head.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">").append("\r\n");
			if (title != null) {
				head.append("<h1>").append("\r\n");
				head.append(title);
				head.append("</h1>").append("\r\n");
			}
			if (htmlStyle != null) {
				head.append(htmlStyle.build());
			}
			head.append("</head>").append("\r\n");
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
				css.append("<style type=\"text/css\">").append("\r\n");
				css.append(tablecss.cssText());
				css.append("</style>").append("\r\n");
			}
			return css.toString();
		}
	}

	public static class HtmlTable {

		String tableTitle;

		String[] columns;

		List<Object[]> rows;

		String build() {
			StringBuilder table = new StringBuilder();
			table.append("<table class=\"\" style=\"min-width:300px\">").append("\r\n");
			if (tableTitle != null) {
				table.append("<caption>");
				table.append(tableTitle);
				table.append("</caption>");
			}
			buildTableHead(table);
			buildTableBody(table);
			table.append("</table>").append("\r\n");
			return table.toString();
		}

		private final void buildTableBody(StringBuilder table) {
			if (rows == null) {
				return;
			}
			table.append("<tbody>").append("\r\n");
			int i = 0;
			for (Object[] rowData : rows) {
				if (i++ % 2 == 0) {
					table.append("<tr class=\"odd\">").append("\r\n");
				} else {
					table.append("<tr>").append("\r\n");
				}
				// 列索引
				table.append("<td>");
				table.append(i);
				table.append("</td>").append("\r\n");

				for (Object data : rowData) {
					table.append("<td>");
					table.append(data);
					table.append("</td>").append("\r\n");
				}
				table.append("</tr>").append("\r\n");
			}
			table.append("</tbody>").append("\r\n");
		}

		private final void buildTableHead(StringBuilder table) {
			if (columns == null) {
				return;
			}
			table.append("<thead>").append("\r\n");
			table.append("<tr>").append("\r\n");
			// 列表索引序列
			table.append("<th scope=\"col\">");
			table.append("#");
			table.append("</th>").append("\r\n");
			// titles
			for (String col : columns) {
				table.append("<th scope=\"col\">");
				table.append(col);
				table.append("</th>").append("\r\n");
			}
			table.append("</tr>").append("\r\n");
			table.append("</thead>").append("\r\n");
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

	public static void main(String[] args) {
		HtmlStaticTableBuilder builder = new HtmlStaticTableBuilder();
		builder.headBuider().title("my title").tableStyle(TableStyle.NEWSPAPER_TABLE_STYLE);
		String[] columns = new String[] { "t1", "t2", "t3" };
		List<Object[]> rows = new ArrayList<>();
		for (int i = 0; i < columns.length; i++) {
			rows.add(new String[] { "value1" + i, "value2" + i, "value3" + i });
		}
		StaticTableData tableData = TableDataBuilder.newBuilder().columnTitles(columns).rows(rows).build();
		builder.bodyBuilder().newTableBuilder(tableData);
		System.out.println(builder.build());
	}

}
