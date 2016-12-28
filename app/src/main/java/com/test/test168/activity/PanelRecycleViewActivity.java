package com.test.test168.activity;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;

import butterknife.ButterKnife;

public class PanelRecycleViewActivity extends BaseActivity {


    @Override
    protected void initViews() {
        setContentView(R.layout.activity_panel_recycle_view);
        ButterKnife.bind(this);


        /*ScrollablePanel mScrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
        ResultPanelAdapter mMemberInquiryResultAdapter = null;
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                temp.add("test " + i + " " + j);
            }
            list.add(temp);
        }
        L.i("list : " + list);
        mMemberInquiryResultAdapter = new ResultPanelAdapter(list);
        mScrollablePanel.setPanelAdapter(mMemberInquiryResultAdapter);*/

//         CommodityCategoryTreeAdapter mAdapter;
//
//        try {
//            mAdapter = new CommodityCategoryTreeAdapter<CommodityCategoryBean>(mRvCommodityCategory, this, list, 20);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
//            @Override
//            public void onClick(Node node, int position) {
//                mPresenter.searchByCategoryId(list.get(position).mSpflBean.id);
//                mAdapter.setSelection(position);
//                mAdapter.notifyDataSetChanged();
//            }
//        });
//        mRvCommodityCategory.setAdapter(mAdapter);

    }
}
