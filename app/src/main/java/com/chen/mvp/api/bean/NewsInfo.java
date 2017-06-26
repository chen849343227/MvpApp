package com.chen.mvp.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * author long
 * date 17-6-25
 * desc 新闻实体
 */

public class NewsInfo implements Parcelable {

    /**
     * "imgurl":"http://cms-bucket.nosdn.127.net/87a380d3eaec4323ac365d1fa8dea3ef20170624114641.jpeg",
     * "has_content":true,
     * "docurl":"http://war.163.com/17/0624/11/CNMNNTIE000181KT.html",
     * "id":10003,
     * "time":"2017-06-24 11:47:01",
     * "title":"中国海警舰船编队6月24日在中国钓鱼岛领海巡航",
     * "channelname":"war"
     */

    private String imgurl;
    private boolean has_content;
    private String docurl;
    private int id;
    private String time;
    private String title;
    private String channelname;

    protected NewsInfo(Parcel in) {
        imgurl = in.readString();
        has_content = in.readByte() != 0;
        docurl = in.readString();
        id = in.readInt();
        time = in.readString();
        title = in.readString();
        channelname = in.readString();
    }

    public static final Creator<NewsInfo> CREATOR = new Creator<NewsInfo>() {
        @Override
        public NewsInfo createFromParcel(Parcel in) {
            return new NewsInfo(in);
        }

        @Override
        public NewsInfo[] newArray(int size) {
            return new NewsInfo[size];
        }
    };

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public boolean isHas_content() {
        return has_content;
    }

    public void setHas_content(boolean has_content) {
        this.has_content = has_content;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgurl);
        dest.writeByte((byte) (has_content ? 1 : 0));
        dest.writeString(docurl);
        dest.writeInt(id);
        dest.writeString(time);
        dest.writeString(title);
        dest.writeString(channelname);
    }
}
