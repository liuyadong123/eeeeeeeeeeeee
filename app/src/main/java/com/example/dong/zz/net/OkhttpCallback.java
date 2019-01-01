package com.example.dong.zz.net;

import com.example.dong.zz.bean.ProductBean;

public interface OkhttpCallback {
    void failure(String msg);
    void success(String productBean);
}
