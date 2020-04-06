package com.eason.html.easyview.core.form.table.model;

public class TableColumnBuilder {

	TableColumn column = new TableColumn();

	public static TableColumnBuilder newBuild() {
		return new TableColumnBuilder();
	}

	public static TableColumn newIndexColumn() {
		return new IndexColumn();
	}

	public static TableColumn newCheckColumn() {
		return new CheckColumn();
	}
	
	public static TableColumn newItemOptColumn() {
		return new ItemOptColumn();
	}

	public TableColumnBuilder title(String title) {
		column.title = title;
		return this;
	}

	public TableColumnBuilder field(String field) {
		column.field = field;
		return this;
	}

	public TableColumnBuilder checkbox(boolean checkbox) {
		column.checkbox = checkbox;
		return this;
	}

	public TableColumnBuilder align(String align) {
		column.align = align;
		return this;
	}

	public TableColumnBuilder valign(String valign) {
		column.valign = valign;
		return this;
	}

	public TableColumnBuilder sortable(boolean sortable) {
		column.sortable = sortable;
		return this;
	}

	public TableColumnBuilder formatter(String formatter) {
		column.formatter = formatter;
		return this;
	}

	public TableColumn build() {
		return column;
	}

	public static class TableColumn {

		public String title;

		public String field;

		public boolean checkbox = false;

		public String align = "center";

		public String valign = "middle";

		public boolean sortable = false;

		public String events;

		public String formatter;

		private TableColumn() {
			super();
		}

		public TableColumn(String title, String field, boolean checkbox, String align, String valign, boolean sortable,
				String events, String formatter) {
			super();
			this.title = title;
			this.field = field;
			this.checkbox = checkbox;
			this.align = align;
			this.valign = valign;
			this.sortable = sortable;
			this.events = events;
			this.formatter = formatter;
		}

		@Override
		public String toString() {
			return "TableColumn [title=" + title + ", field=" + field + ", checkbox=" + checkbox + ", align=" + align
					+ ", valign=" + valign + ", sortable=" + sortable + ", events=" + events + ", formatter="
					+ formatter + "]";
		}

	}

	public static class IndexColumn extends TableColumn {

		public IndexColumn() {
			super("#", "index", false, "center", "middle", false, null,"genderIndex");
		}
	}

	public static class CheckColumn extends TableColumn {

		public CheckColumn() {
			super("", "state", true, "center", "middle", false, null, null);
		}
	}

	public static class ItemOptColumn extends TableColumn {

		public ItemOptColumn() {
			super("操作", "operate", false, "center", "middle", false, "operateEvents", "genderOpt");
		}
	}

}
