package com.eason.html.easyview.core;

import com.eason.html.easyview.core.utils.StringUtils;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2020年2月2日 下午7:33:50
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年2月2日
 * @modify by reason:{方法名}:{原因}
 */
public class DateTimeInfo {

	public String id;

	public String type;

	public String range;

	public DateTimeInfo(String id, String type, String range) {
		super();
		this.id = id;
		this.type = type;
		if (StringUtils.isNotBlank(range)) {
			this.range = range;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DateTimeInfo [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", range=");
		builder.append(range);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateTimeInfo other = (DateTimeInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
