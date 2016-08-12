package com.example.mines;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mine.R;

public class MineActivtity extends AppCompatActivity {
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_item);
        listView = (ListView) findViewById(R.id.mine_ListView);
        final String[] my = {"我的发布", "我的草稿", "我的收藏", "我的消息", "平台消息", "关于“爱家乡”", "分享软件", "修改密码", "清除缓存",};
        final int[] img = {R.drawable.img_my_fragment_release_left, R.drawable.img_my_fragment_draft_left, R.drawable.img_my_fragment_collection_left, R.drawable.img_my_fragment_msg_left, R.drawable.img_my_fragment_stament_left, R.drawable.img_my_fragment_about_left, R.drawable.img_my_fragment_share_left, R.drawable.img_my_fragment_password_left, R.drawable.img_my_fragment_buffer_left};
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return img.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(MineActivtity.this).inflate(R.layout.activity_mine, parent, false);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.mine_img);
                TextView textView = (TextView) convertView.findViewById(R.id.mine_text);
                imageView.setImageResource(img[position]);
                textView.setText(my[position]);
                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }
}
