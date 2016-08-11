package com.example.syyhm.lovehomess;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TabActivity {
    private TabHost tabHost;
    // 存放Tab页中ImageView信息
    private List<ImageView> imageList = new ArrayList<ImageView>();
    // 存放Tab页中TextView信息
    private List<TextView> textList = new ArrayList<TextView>();

    private void addTab(int tabId, String tabName, int drawableId, Class<?> cls) {
        TabSpec tabSpec = tabHost.newTabSpec(tabId + ""); //id是选项卡的标示
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab1, getTabWidget(), false);//底部导航栏
        ImageView home_image = (ImageView) tabIndicator.findViewById(R.id.home_icon);
        TextView home_title = (TextView) tabIndicator.findViewById(R.id.home_title);
        home_title.setText(tabName);    //这是底部导航栏上显示的文字
        if (tabId == 0) {                 //四个选项卡上的文字在xml中默认都设置了灰色，第一个要设置为默认选中的绿色
            home_title.setTextColor(getResources().getColor(R.color.presscolor));//设置为绿色 选中
        }
        home_image.setImageResource(drawableId);
        tabSpec.setIndicator(tabIndicator);
        tabSpec.setContent(new Intent(this, cls));   //设置要切换到的Activity
        tabHost.addTab(tabSpec);
        imageList.add(home_image);
        textList.add(home_title);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = getTabHost();         //获取TabHost控件
        //设置TabHost的内容（TabHost的内容区是使用Activity表示的，Activity比较消耗资源，这是TabHost在4.0以后过期的原因）
        //newTabSpec创建一个选项卡，参数就是这个选项卡的标识，返回一个TabSpec对象，待会利用这个对象操作选项卡的切换
        //ts1.setIndicator("微信");						//设置控制区显示的内容
        //ts1.setIndicator("", getResources().getDrawable(R.drawable.amu)); //没有文字才出现图标，解决方案见文档
//        TabSpec ts1 = tabhost.newTabSpec(0+"");
//        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab1, getTabWidget(), false);
//        TextView title = (TextView) tabIndicator.findViewById(R.id.title);
//        title.setText("首页");
//
//        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
//        icon.setImageResource(R.drawable.main_index_home_pressed);
//        ts1.setIndicator(tabIndicator);
//        ts1.setContent(new Intent(this,Activity1.class));		//设置要切换到的Activity
//        tabhost.addTab(ts1);						//设置好的控制区加到TabHost中
        //// TODO: 2016/8/11 首页页面
        addTab(0, "爱家乡", R.drawable.home_press, Main2Activity.class);
        //// TODO: 2016/8/11 发布页面
        addTab(0, "发布", R.drawable.publish, Main3Activity.class);
        //// TODO: 2016/8/11 我的页面
        addTab(0, "我的", R.drawable.wode, Main4Activity.class);
        tabHost.getTabWidget().setStripEnabled(false);//设置底部下划线是否出现
        tabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
        //设置当前显示哪一个标签
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // 设置所有选项卡的图片为未选中图片
                imageList.get(0).setImageDrawable(getResources().getDrawable(R.drawable.home));
                imageList.get(1).setImageDrawable(getResources().getDrawable(R.drawable.publish));
                imageList.get(2).setImageDrawable(getResources().getDrawable(R.drawable.wode));
                textList.get(1).setTextColor(getResources().getColor(R.color.usualcolor));
                textList.get(2).setTextColor(getResources().getColor(R.color.usualcolor));
                switch (Integer.parseInt(tabId)) {
                    case 0:
                        imageList.get(0).setImageDrawable(getResources().getDrawable(R.drawable.home_press));
                        textList.get(0).setTextColor(getResources().getColor(R.color.presscolor));
                        break;
                    case 1:
                        imageList.get(1).setImageDrawable(getResources().getDrawable(R.drawable.publish_press));
                        textList.get(1).setTextColor(getResources().getColor(R.color.presscolor));
                        break;
                    case 2:
                        imageList.get(2).setImageDrawable(getResources().getDrawable(R.drawable.wode_press));
                        textList.get(2).setTextColor(getResources().getColor(R.color.presscolor));
                        break;
                }
            }
        });
    }
}
