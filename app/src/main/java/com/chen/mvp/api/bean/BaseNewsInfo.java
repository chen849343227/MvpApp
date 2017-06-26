package com.chen.mvp.api.bean;

import java.util.List;

/**
 * author long
 * date 17-6-26
 * desc
 */

public class BaseNewsInfo {

    private int page;

    private List<NewsInfo> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<NewsInfo> getList() {
        return list;
    }

    public void setList(List<NewsInfo> list) {
        this.list = list;
    }
}
