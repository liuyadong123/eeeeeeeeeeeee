package com.example.dong.zz.modle;

import android.os.Handler;
import android.text.TextUtils;

import com.example.dong.zz.api.ProductApi;
import com.example.dong.zz.bean.ProductBean;
import com.example.dong.zz.contract.ProductContract;
import com.example.dong.zz.net.OkhttpCallback;
import com.example.dong.zz.net.OkhttpUtils;
import com.example.dong.zz.net.RequestCallBack;
import com.google.gson.Gson;

import java.util.HashMap;

public class ProductModle implements ProductContract.IProductModle {
    Handler handler=new Handler();
    @Override
    public void prdouct(HashMap<String, String> params, final RequestCallBack callBack) {
        OkhttpUtils.insertent().doPost(ProductApi.PRODCUT_SERCH, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                           callBack.failure("请求失败");
                        }
                    });
                }
            }

            @Override
            public void success(String productBean) {
                if (!TextUtils.isEmpty(productBean)){
                    parenter(productBean,callBack);
                }
            }


        });
    }

    private void parenter(String productBean, final RequestCallBack callBack) {
        final ProductBean productBean1 = new Gson().fromJson(productBean, ProductBean.class);
        if (callBack!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.success(productBean1);
                }
            });
        }

    }


}
