package com.example.dong.zz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dong.zz.apadter.ProductApadter;
import com.example.dong.zz.bean.ProductBean;
import com.example.dong.zz.contract.ProductContract;
import com.example.dong.zz.net.OkhttpUtils;
import com.example.dong.zz.presenter.ProductPrensenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ProductContract.IproductView {
      @BindView(R.id.rv)
      RecyclerView rv;
      @BindView(R.id.bt)
      Button bt;
      private ProductApadter productApadter;
      private ProductPrensenter prensenter;
      private String page="1";
      @BindView(R.id.ed)
       EditText ed;

      
      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        bt=findViewById(R.id.bt);
        ed=findViewById(R.id.ed);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keywords = ed.getText().toString();
                HashMap<String,String> params=new HashMap<>();
                params.put("keywords",keywords);
                params.put("page",page);
                if (prensenter!=null){
                    prensenter.prdouct(params);
                }

            }
        });
        rv=findViewById(R.id.rv);
        productApadter=new ProductApadter(this);
        rv.setAdapter(productApadter);
        rv.setLayoutManager(new LinearLayoutManager(this,1,false));
    }


    private void initData() {
   prensenter =new ProductPrensenter(this);

    }





    @Override
    public void success(ProductBean productBean) {
        productApadter.setData(productBean.getData());
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successmsg(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void keywordsError(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkhttpUtils.insertent().cannleTask();
    }
}
