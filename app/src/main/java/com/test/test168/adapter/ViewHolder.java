package com.test.test168.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/29 0029.
 * ListView GridView RecycleView 的ViewHolder
 */
public class ViewHolder {
    //    private Context mContext;
    private final SparseArray<View> mViews;
    public int mPosition;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
//        this.mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        // setTag
        mConvertView.setTag(this);
    }

    private ViewHolder(View view) {
        mViews = new SparseArray<View>();
        mConvertView = view;
        mConvertView.setTag(mViews);
    }

    public static ViewHolder getViewHolder(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        return viewHolder;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }


    public View getConvertView() {
        return mConvertView;
    }


    public int getPosition() {
        return mPosition;
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为控件设置可见属性
     *
     * @param viewId
     * @param visible
     * @return
     */
    public ViewHolder setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    /**
     * 为View设置点击监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /***
     * 为EditText 设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setEditText(int viewId, String text) {
        EditText view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为Button设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setButtonText(int viewId, String text) {
        Button view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字体颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    public ViewHolder setTag(int viewId, long tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }


    /***
     * 为 Text View 设置背景
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setTextViewBackground(int viewId, int drawableId) {
        TextView view = getView(viewId);
        view.setBackgroundResource(drawableId);
        return this;
    }

    public ViewHolder setTextViewBackgroundColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setBackgroundColor(color);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    public ViewHolder setImageBackgroundColor(int viewId, int color) {
        ImageView view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ImageView getImageView(int viewId) {
        ImageView view = getView(viewId);
        return view;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

}
