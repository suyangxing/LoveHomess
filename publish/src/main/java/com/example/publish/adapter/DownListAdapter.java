package com.example.publish.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.publish.R;
import com.example.publish.enetity.PrintCategory;

public class DownListAdapter extends BaseAdapter {

    private List<PrintCategory.ListBean.ChildCategoryListBean> objects;
    private Context context;
    private LayoutInflater layoutInflater;

    public DownListAdapter(Context context,List<PrintCategory.ListBean.ChildCategoryListBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public PrintCategory.ListBean.ChildCategoryListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.down_list, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((PrintCategory.ListBean.ChildCategoryListBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(PrintCategory.ListBean.ChildCategoryListBean object, ViewHolder holder) {
        holder.downTv.setText(object.getChild_category_name());
        //TODO implement
    }

    protected class ViewHolder {
        private TextView downTv;

        public ViewHolder(View view) {
            downTv = (TextView) view.findViewById(R.id.down_tv);
        }
    }
}
