package com.human.model;

import java.util.List;
/**
 * 分页实体类
 * @author hq
 * @Date2019年5月14日 上午11:20:10
 */
public class PageBean<T> {

    private int currPage;//当前页数
    private int pageSize;//每页显示的记录数
    private boolean isPreviousPage;//是否有前一页
    private boolean isAfterPage;//是否有后一页
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private List<T> lists;//每页的显示的数据

    public PageBean() {
        super();
    }

    public int getCurrPage() {
        return currPage;
    }

    public boolean isPreviousPage() {
        return isPreviousPage;
    }

    public boolean isAfterPage() {
        return isAfterPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPreviousPage(boolean previousPage) {
        isPreviousPage = previousPage;
    }

    public void setAfterPage(boolean afterPage) {
        isAfterPage = afterPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

}
