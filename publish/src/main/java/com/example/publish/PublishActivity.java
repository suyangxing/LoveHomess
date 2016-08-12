package com.example.publish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.publish.adapter.InterfaceItemAdapter;
import com.example.publish.enetity.PrintCategory;
import com.example.publish.service.HTTPService;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

public class PublishActivity extends AppCompatActivity {

    GridView gridView;
    List<PrintCategory.ListBean> list;
    DownPicPopupWindowActivity downView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        gridView = (GridView) findViewById(R.id.gv_gridV);
        list = new ArrayList<PrintCategory.ListBean>();
        getData();
    }

    public void getData() {
        HTTPService.getHttpService().getData("http://123.206.87.139/LoveHomeTownServer/printCategory", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d("PublishActivity", s);
                PrintCategory category = JSON.parseObject(s, PrintCategory.class);
                list=category.getList();
                Log.d("PublishActivity", list.toString());
                InterfaceItemAdapter itemAdapter = new InterfaceItemAdapter(PublishActivity.this,list);
                gridView.setAdapter(itemAdapter);
                Log.d("PublishActivity", "list.size():" + list.size());
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        downView= new DownPicPopupWindowActivity(PublishActivity.this,position);
                        downView.showAtLocation(PublishActivity.this.findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //
                    }
                });
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
}
