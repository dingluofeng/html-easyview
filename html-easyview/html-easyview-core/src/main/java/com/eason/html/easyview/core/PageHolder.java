package com.eason.html.easyview.core;

import java.util.ArrayList;
import java.util.List;

import com.eason.html.easyview.core.basecontroller.PageParams;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年3月2日 下午7:13:59
 * @version V1.0
 * @param <T>
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年3月2日
 * @modify by reason:{方法名}:{原因}
 */
public class PageHolder<T> {

	public final int total;

	public final List<T> records;

	public PageHolder(int total, List<T> records) {
		super();
		this.total = total;
		this.records = records;
	}

	public static <T> PageHolder<T> empty() {
		return new PageHolder<T>(0, new ArrayList<T>());
	}

	public static <T> PageHolder<T> single(T record) {
		List<T> records = new ArrayList<>();
		records.add(record);
		return new PageHolder<T>(1, records);
	}

	public static <T> PageHolder<T> pageList(int total, List<T> records) {
		return new PageHolder<T>(total, records);
	}
	
	public static <T> PageHolder<T> pageListWithPage(int page, int pageSize, List<T> records) {
		int total = 0;
		if (records != null) {
			int curSize = records.size();
			total = curSize == pageSize ? pageSize * page + 1 : pageSize * (page - 1) + curSize;
		}
		return new PageHolder<T>(total, records);
	}
	
	public static <T> PageHolder<T> pageListWithOffset(int offset, int limit, List<T> records) {
		int total = 0;
		if (records != null) {
			int curSize = records.size();
			total = curSize == limit ? limit + offset + 1 : offset + curSize;
		}
		return new PageHolder<T>(total, records);
	}
	
	public static <T> PageHolder<T> pageList(PageParams pageParams, List<T> records) {
		int total = 0;
		if (records != null) {
			int limit = pageParams.getLimit();
			int offset = pageParams.getOffset();
			int curSize = records.size();
			total = curSize == limit ? limit + offset + 1 : offset + curSize;
		}
		return new PageHolder<T>(total, records);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [total=");
		builder.append(total);
		builder.append(", records=");
		builder.append(records);
		builder.append("]");
		return builder.toString();
	}
}