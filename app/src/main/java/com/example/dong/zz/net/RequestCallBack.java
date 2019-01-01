package com.example.dong.zz.net;

import com.example.dong.zz.bean.ProductBean;

public interface RequestCallBack {
    void success(ProductBean productBean);
    void failure(String msg);
    void successmsg(String msg);
}
