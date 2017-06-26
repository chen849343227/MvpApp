package com.chen.mvp.local.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author long
 * date 17-6-25
 * desc 新闻类型
 */
@Entity
public class NewsTypeInfo {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String type;

    @Generated(hash = 215923915)
    public NewsTypeInfo() {
    }

    @Generated(hash = 2136814308)
    public NewsTypeInfo(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NewsTypeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
