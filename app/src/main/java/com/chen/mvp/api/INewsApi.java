package com.chen.mvp.api;

import com.chen.mvp.api.bean.NewsInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.chen.mvp.api.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * author long
 * date 17-6-25
 * desc
 */

public interface INewsApi {

    /**
     * 获取新闻列表
     * eg: http://wangyi.butterfly.mopaasapp.com/news/api?type=war&page=1&limit=10
     *
     * @param type 新闻类型
     * @param page 当前页数
     * @param limit 请求条数
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("news/api")
    Observable<Map<String, List<NewsInfo>>> getNewsList(@Query("type") String type, @Query("page") int page,
                                                        @Query("limit") int limit);

}
