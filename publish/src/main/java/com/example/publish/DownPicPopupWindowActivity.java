package com.example.publish;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSON;
import com.example.publish.adapter.DownListAdapter;
import com.example.publish.enetity.PrintCategory;
import com.example.publish.service.HTTPService;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

public class DownPicPopupWindowActivity extends PopupWindow {

    List<PrintCategory.ListBean.ChildCategoryListBean> list;
    ListView listView;
    ImageView quit;
    private View view;
    int index;
    public DownPicPopupWindowActivity(final Activity context,int indexs) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.activity_down_pic_popup_window,null);
        list = new ArrayList<PrintCategory.ListBean.ChildCategoryListBean>();
        listView = (ListView)view.findViewById(R.id.down_listview);
        quit = (ImageView)view.findViewById(R.id.quit_listview);
        this.index = indexs;
        HTTPService.getHttpService().getData(index,"http://123.206.87.139/LoveHomeTownServer/printCategory", new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d("DownPicPopupWindowActiv", s);
                PrintCategory printCategory = JSON.parseObject(s,PrintCategory.class);
                list = printCategory.getList().get(index).getChildCategoryList();
                Log.d("DownPicPopupWindowActiv", "list:" + list.toString());
                listView.setAdapter(new DownListAdapter(context,list));
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
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.pop_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xbeeeeeee);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.pop_linearlayout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
//    public void initView(){
//        list = new ArrayList<PrintCategory.ListBean.ChildCategoryListBean>();
//        listView = (ListView)view.findViewById(R.id.down_listview);
//        quit = (ImageView)view.findViewById(R.id.quit_listview);
//    }
//    public void getData(){
//        HTTPService.getHttpService().getData(index,"http://123.206.87.139/LoveHomeTownServer/printCategory", new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                PrintCategory.ListBean printCategory = JSON.parseObject(s,PrintCategory.ListBean.class);
//                list.add(printCategory.getChildCategoryList().get(index));
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException e) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
}
