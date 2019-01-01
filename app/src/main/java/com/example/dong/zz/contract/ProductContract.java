package com.example.dong.zz.contract;

import com.example.dong.zz.bean.ProductBean;
import com.example.dong.zz.net.RequestCallBack;

import java.util.HashMap;

public interface ProductContract {
    public  abstract  class ProductPrensenter{
        public  abstract void prdouct(HashMap<String,String> params);
    }
    interface  IProductModle{
        void prdouct(HashMap<String,String> params, RequestCallBack callBack);
    }
    interface IproductView{
        void success(ProductBean productBean);
        void failure(String msg);
        void successmsg(String msg);
        void keywordsError(String msg);
    }
}

