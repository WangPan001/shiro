package com.xdl.cn.util;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @program: substance-manager
 * @description: 分页类
 * @author: XuMiao
 * @create: 2018-10-29 18:00
 **/
public class PageUtils<T> implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(描述该变量...)
     */
    private static final long serialVersionUID = 6052548904902064806L;

    //当前页
    private int page_num;
    //每页的数量
    private int page_size;
    //总记录数
    private long total;
    //总页数
    private int pages;

    private List<T> list;

    //是否为第一页
    private boolean first_page = false;

    //是否为最后一页
    private boolean last_page = false;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isFirst_page() {
        return first_page;
    }

    public void setFirst_page(boolean first_page) {
        this.first_page = first_page;
    }

    public boolean isLast_page() {
        return last_page;
    }

    public void setLast_page(boolean last_page) {
        this.last_page = last_page;
    }

    public PageUtils() {

    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageUtils(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.page_num = page.getPageNum();
            this.page_size = page.getPageSize();

            this.pages = page.getPages();
            this.list = page;
            this.total = page.getTotal();
        } else if (list instanceof Collection) {
            this.page_num = 1;
            this.page_size = list.size();

            this.pages = 1;
            this.list = list;
            this.total = list.size();
        }
        if (list instanceof Collection) {
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        first_page = page_num == 1;
        last_page = total<=(page_num*page_size);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("page_num=").append(page_num);
        sb.append(", page_size=").append(page_size);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append(", first_page=").append(first_page);
        sb.append(", last_page=").append(last_page);
        sb.append(", navigatepageNums=");
        sb.append('}');
        return sb.toString();
    }
}
