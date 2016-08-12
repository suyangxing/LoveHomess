package com.example.publish.service;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by syYhm on 2016/8/12.
 */
public class HTTPService {
    public static HTTPService httpService;
    private HTTPService(){}
    public static HTTPService getHttpService(){
        if(httpService ==null){
            httpService = new HTTPService();
        }
        return httpService;
    }
    public void getData(String url,Callback.CommonCallback<String> common){
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams,common);
    }
    public void getData(int index,String url,Callback.CommonCallback<String> common){
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams,common);
    }
}
