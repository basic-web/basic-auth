package com.github.ququzone.common;

import java.io.Serializable;
import java.util.List;

/**
 * page model.
 *
 * @author Yang XuePing
 */
public class Page<T> implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private static final long serialVersionUID = 7033820517178003823L;
    public static Page EMPTY;

    static {
        EMPTY = new Page();
        EMPTY.setTotal(0);
        EMPTY.setCurrent(1);
        EMPTY.setPageSize(DEFAULT_PAGE_SIZE);
    }

    private long total;
    private List<T> data;
    private int current;
    private int pageSize;
    private int totalPage;

    public int getTotalPage() {
        if (total == 0)
            return 0;
        if (totalPage == 0)
            totalPage = (total % pageSize == 0) ? (int) (total / pageSize)
                    : (int) (total / pageSize + 1);
        return totalPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}