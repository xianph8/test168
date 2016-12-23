package com.test.test168.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.test168.R;
import com.test.test168.adapter.MemberInquiryResultAdapter;
import com.test.test168.base.BaseActivity;
import com.test.test168.bean.Tb_hyBean;
import com.test.test168.utils.L;

import java.util.ArrayList;
import java.util.List;

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

        RecyclerView mRvMemberCategoryResult = (RecyclerView) findViewById(R.id.rv_member_category_result);
        MemberInquiryResultAdapter mMemberInquiryResultAdapter = null;
        List<Tb_hyBean> hylist = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            hylist.add(new Tb_hyBean());
        }
        L.i("hylist : " + hylist);
        mRvMemberCategoryResult.setLayoutManager(new LinearLayoutManager(mContext));
        mMemberInquiryResultAdapter = new MemberInquiryResultAdapter(mContext, hylist);
        mRvMemberCategoryResult.setAdapter(mMemberInquiryResultAdapter);


        /*List<List<Tb_hyBean>> hylist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<Tb_hyBean> temp = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                temp.add(new Tb_hyBean());
            }
            hylist.add(temp);
        }

        TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        MatrixTableAdapter matrixTableAdapter = new MatrixTableAdapter(this, hylist);
        tableFixHeaders.setAdapter(matrixTableAdapter);*/

    }
}
