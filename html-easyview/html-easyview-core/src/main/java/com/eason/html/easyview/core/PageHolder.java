package com.eason.html.easyview.core;

import java.util.List;

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