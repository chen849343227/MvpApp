package com.chen.mvp.local.dao;

import android.content.Context;

import com.chen.mvp.greendao.DaoSession;
import com.chen.mvp.greendao.NewsTypeInfoDao;
import com.chen.mvp.local.table.NewsTypeInfo;
import com.chen.mvp.utils.AssetsHelper;
import com.chen.mvp.utils.GsonHelper;

import java.util.List;

/**
 * author long
 * date 17-6-26
 * desc 新闻数据分类访问
 */

public class NewsTypeDao {

    // 所有栏目
    private static List<NewsTypeInfo> sAllChannels;

    private NewsTypeDao() {
    }

    /**
     * 更新本地数据，如果数据库新闻列表栏目为 0 则添加头 3 个栏目
     * @param context
     * @param daoSession
     */
    public static void updateLocalData(Context context, DaoSession daoSession) {
        sAllChannels = GsonHelper.convertEntities(AssetsHelper.readData(context, "NewsChannel_new"), NewsTypeInfo.class);
        NewsTypeInfoDao beanDao = daoSession.getNewsTypeInfoDao();
        if (beanDao.count() == 0) {
            beanDao.insertInTx(sAllChannels.subList(0, 3));
        }
    }

    /**
     * 获取所有栏目
     * @return
     */
    public static List<NewsTypeInfo> getAllChannels() {
        return sAllChannels;
    }
}
