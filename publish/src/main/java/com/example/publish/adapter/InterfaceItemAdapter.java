package com.example.publish.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.publish.R;
import com.example.publish.enetity.PrintCategory;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class InterfaceItemAdapter extends BaseAdapter {
    private ImageLoader imageLoader;
    private List<PrintCategory.ListBean> objects;
    private Context context;
    private LayoutInflater layoutInflater;

    public InterfaceItemAdapter(Context context,List<PrintCategory.ListBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public PrintCategory.ListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.interface_item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((PrintCategory.ListBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(PrintCategory.ListBean object, ViewHolder holder) {
        getImage();
        //TODO implement
        imageLoader.displayImage(object.getParent_cate_img_url(),holder.ivPImg);
        holder.tvPName.setText(object.getParent_cate_name());
    }

    protected class ViewHolder {
        private ImageView ivPImg;
        private TextView tvPName;

        public ViewHolder(View view) {
            ivPImg = (ImageView) view.findViewById(R.id.iv_pImg);
            tvPName = (TextView) view.findViewById(R.id.tv_pName);
        }
    }
    public void getImage(){
        //获取ImageLoader对象
        imageLoader = ImageLoader.getInstance();
        //配置imageLoad
        // ImageLoaderConfiguration config = new
        // ImageLoaderConfiguration.Builder(
        // context).threadPriority(Thread.NORM_PRIORITY - 2)
        // .denyCacheImageMultipleSizesInMemory()
        // .discCacheFileNameGenerator(new Md5FileNameGenerator())
        // .tasksProcessingOrder(QueueProcessingType.LIFO)
        // .writeDebugLogs()
        // .build();
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");//待会图片保存的位置

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.hunqing) // 加载图片时的图片
                .showImageForEmptyUri(R.drawable.hunqing) // 没有图片资源时的默认图片
                .showImageOnFail(R.drawable.hunqing) // 加载失败时的图片
                // .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true).cacheOnDisc(true)
                // .imageScaleType(ImageScaleType.EXACTLY)
                 .displayer(new RoundedBitmapDisplayer(20)) //设置显示风格这里是圆角矩形
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(options)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024)
                .discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        imageLoader.init(config);
    }
}
