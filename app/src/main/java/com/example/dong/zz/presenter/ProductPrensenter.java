package com.example.dong.zz.presenter;

import com.example.dong.zz.bean.ProductBean;
import com.example.dong.zz.contract.ProductContract;
import com.example.dong.zz.modle.ProductModle;
import com.example.dong.zz.net.RequestCallBack;

import java.util.HashMap;

public class ProductPrensenter extends ProductContract.ProductPrensenter {

    private ProductModle productModle;
    private ProductContract.IproductView iproductView;
    public  ProductPrensenter(ProductContract.IproductView iproductView){
         productModle=new ProductModle();
         this.iproductView=iproductView;
    }
    @Override
    public void prdouct(HashMap<String, String> params) {
         if (productModle!=null){
             productModle.prdouct(params, new RequestCallBack() {
                 @Override
                 public void success(ProductBean productBean) {
                     if (iproductView!=null){
                   iproductView.success(productBean);

                     }

                 }

                 @Override
                 public void failure(String msg) {
                     if (iproductView!=null){
                 iproductView.failure(msg);

                     }
                 }

                 @Override
                 public void successmsg(String msg) {
                     if (iproductView!=null){

                 iproductView.successmsg(msg);
                     }
                 }
             });
         }

    }
}
