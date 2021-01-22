package com.eason.html.test;

import java.util.ArrayList;
import java.util.List;

import com.eason.html.easyview.core.statictable.HtmlStaticTableBuilder;
import com.eason.html.easyview.core.statictable.HtmlStaticTableBuilder.TableStyle;
import com.eason.html.easyview.core.statictable.model.StaticTableData;
import com.eason.html.easyview.core.statictable.model.StaticTableData.TableDataBuilder;
import com.google.common.collect.Lists;

public class MainTest {

	public static void main(String[] args) {
		HtmlStaticTableBuilder builder = new HtmlStaticTableBuilder();
		
		builder.headBuider().tableStyle(TableStyle.NEWSPAPER_TABLE_STYLE);
		String[] columns = new String[] { "t1", "t2", "t3" };
		List<Object[]> rows = new ArrayList<>();
		for (int i = 0; i < columns.length; i++) {
			rows.add(new String[] { "value1" + i, "value2" + i, "value3" + i });
		}
		StaticTableData tableData = TableDataBuilder.newBuilder().columnTitles(columns).rows(rows).build();
		List<UserInfo> rowEntities=new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			List<Address> addresses=new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				Address address=new Address("中国", "浙江", "杭州"+i);
				addresses.add(address);
			}
			UserInfo user=new UserInfo("username"+i, i*i, addresses);
			user.setFavs(Lists.newArrayList(new String[] {"游泳","篮球"},new String[] {"乒乓球","跑步"}));
			user.setFavs2(Lists.newArrayList("乒乓球","跑步"));
			rowEntities.add(user);
		}
		TableDataBuilder tableDataBuilder = TableDataBuilder.newBuilderWithRowEntities(rowEntities);
		tableDataBuilder.title("测试TableTitle");
		builder.bodyBuilder().newTableBuilder(tableDataBuilder.build()).newTableBuilder(tableData);
		System.out.println(builder.build());
	}
}