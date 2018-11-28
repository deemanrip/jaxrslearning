package com.yukhlin.resource.bean;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

    private @QueryParam("year") int year;
    private @QueryParam("offset") int offset;
    private @QueryParam("pageSize") int pageSize;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
