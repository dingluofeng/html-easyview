package com.eason.html.easyview.core.basecontroller;

import java.io.Serializable;

/**
 * <p></p>
 * @author DingLuoFeng 2020年1月23日 下午12:34:51
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020年1月23日
 * @modify by reason:{方法名}:{原因}
 */
public class PageParams implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 排序表格field字段，这里是实体类字段名
     */
    private String sort;

    /**
     * 排序：aes desc
     */
    private String order;

    /**
     * 数据分页偏移起始位置
     */
    private int offset;

    /**
     * 分页获取数据大小
     */
    private int limit;

    /**
     * 当前分页页码
     */
    private int curPage;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PageQueryParams [sort=");
        builder.append(sort);
        builder.append(", order=");
        builder.append(order);
        builder.append(", offset=");
        builder.append(offset);
        builder.append(", limit=");
        builder.append(limit);
        builder.append(", curPage=");
        builder.append(curPage);
        builder.append("]");
        return builder.toString();
    }

}
