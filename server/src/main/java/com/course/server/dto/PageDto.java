package com.course.server.dto;

import java.util.List;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-05-29 21:37
 */
public class PageDto<T> {

    protected int page;

    protected int size;

    protected long total;

    protected List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
