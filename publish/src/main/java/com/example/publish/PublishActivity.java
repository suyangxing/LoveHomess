package com.example.publish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class PublishActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        //应用图片
        final int[] img = {R.drawable.meishi, R.drawable.yule, R.drawable.fangchan, R.drawable.che, R.drawable.hunqing, R.drawable.zhuangxiu, R.drawable.jiaoyu,
                R.drawable.gongzuo, R.drawable.baihuo, R.drawable.tiaozhao, R.drawable.shangwu, R.drawable.bianmin, R.drawable.laoxianghui, R.drawable.qita};
        //应用名
        final String[] name = {"美食", "娱乐", "房产", "车", "婚庆", "装修", "教育",
                "工作", "百货", "跳蚤", "商务", "便民", "老乡会", "其他"};

        gridView = (GridView) findViewById(R.id.gv_gridV);

        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return img.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(PublishActivity.this).inflate(R.layout.interface_item, parent, false);
                ImageView pImg = (ImageView) convertView.findViewById(R.id.iv_pImg);
                TextView pName = (TextView) convertView.findViewById(R.id.tv_pName);
                pImg.setImageResource(img[position]);
                pName.setText(name[position]);
                return convertView;
            }
        });
    }
}
