package com.example.dong.zz.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    private OkHttpClient okHttpClient;
    private static  OkhttpUtils okhttpUtils;
    private Handler handler=new Handler();
    private OkhttpUtils(){
        okHttpClient=new OkHttpClient.Builder()
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public static OkhttpUtils insertent(){
         if (okhttpUtils==null){
             synchronized (OkhttpUtils.class){
                 if (okhttpUtils==null){
                     okhttpUtils=new OkhttpUtils();
                 }
             }
         }
         return  okhttpUtils;
    }
    public  void doPost(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        FormBody.Builder builder =new FormBody.Builder();
        for (Map.Entry<String, String> P:params.entrySet()){
            builder.add(P.getKey(), P.getValue());
        }
        Request request =new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                          okhttpCallback.failure("请求失败");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  if (okhttpCallback!=null){
                      final String result = response.body().string();
                      if (200==response.code()){

                          handler.post(new Runnable() {
                              @Override
                              public void run() {
                                  okhttpCallback.success(result);
                              }
                          });

                      }

                  }
            }
        });
    }
    public  void cannleTask(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
