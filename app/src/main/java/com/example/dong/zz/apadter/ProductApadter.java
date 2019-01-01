package com.example.dong.zz.apadter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.zz.R;
import com.example.dong.zz.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class ProductApadter extends RecyclerView.Adapter<ProductApadter.ProductVH> {
    private List<ProductBean.prouct> list;
    private Context context;

    public ProductApadter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void setData(List<ProductBean.prouct> list){
        if (list!=null){
            this.list=list;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ProductApadter.ProductVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.product_item_layout,viewGroup,false);
        ProductVH productVH =new ProductVH(view);
        return productVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductApadter.ProductVH productVH, int i) {
        ProductBean.prouct prouct =list.get(i);
        productVH.tv.setText(prouct.getTitle());
        String images = prouct.getImages();
        String[] split = images.split("!");
        if (split!=null && split.length>0){
            Glide.with(context).load(split[0]).into(productVH.iv);
        }else {
           productVH.iv.setImageResource(R.mipmap.ic_launcher);
        }




    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ProductVH extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;
        public ProductVH(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
