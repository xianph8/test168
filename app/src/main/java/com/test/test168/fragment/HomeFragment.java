package com.test.test168.fragment;


import com.test.test168.R;
import com.test.test168.base.BaseFragment;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private String[] items = new String[]{
            "item1", "item2", "item3",
            "item4", "item5", "item6",
            "item7", "item8", "item9"
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {

    }

}
