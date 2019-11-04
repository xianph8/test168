package com.test.test168.fragment;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.test.test168.R;
import com.test.test168.base.BaseFragment;
import com.test.test168.rx.RxViewActivity;
import com.xian.common.adapter.RecycleViewAdapter;
import com.xian.common.adapter.ViewHolder;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxAndroidFragment extends BaseFragment {
    public static final String TAG = "RxAndroidFragment";


    RecyclerView mRvRxExampleList;

    public RxAndroidFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_rx_android;
    }

    @Override
    protected void initViews() {
        mRvRxExampleList = root.findViewById(R.id.rv_rx_example_list);

        RxRecyclerView.scrollEvents(mRvRxExampleList).subscribe(new Consumer<RecyclerViewScrollEvent>() {
            @Override
            public void accept(RecyclerViewScrollEvent recyclerViewScrollEvent) throws Exception {
//                recyclerViewScrollEvent.
            }
        });

        initMenuList();

    }

    private void initMenuList() {

        mRvRxExampleList.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<String> rxExampleNameList = new ArrayList<>();
        final ArrayList<Class<? extends Activity>> rxExampleActivityList = new ArrayList<>();
        rxExampleNameList.add("rx view ");
        rxExampleActivityList.add(RxViewActivity.class);

        mRvRxExampleList.setAdapter(new RecycleViewAdapter<String>(rxExampleNameList) {
            @Override
            public void onBindViewHolder(ViewHolder holder, String item) {
                holder.setText(R.id.list_item, item);
            }

            @Override
            public void onItemClick(String item, int position) {
                startActivity(rxExampleActivityList.get(position));
            }

            @Override
            public int getItemLayoutId() {
                return R.layout.item_main_menu;
            }
        });

    }

}
