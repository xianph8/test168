package com.test.test168.fragment;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.test168.R;
import com.test.test168.activity.LitePalActivity;
import com.test.test168.activity.RecyclerViewActivity;
import com.test.test168.activity.ShareToWeChatActivity;
import com.test.test168.activity.TestAutoCompleteTextViewActivity;
import com.test.test168.activity.TestWebServiceActivity;
import com.test.test168.activity.ThreadPoolActivity;
import com.test.test168.activity.UriActivity;
import com.test.test168.adapter.HomeMenuAdapter;
import com.test.test168.base.BaseFragment;
import com.test.test168.bean.ItemHomeMenu;
import com.test.test168.bing.BingDailyPicActivity;
import com.test.test168.contract.HomeContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class NineGirdHomeFragment extends BaseFragment implements HomeContract.View {

    public static final String TAG = "NineGirdHomeFragment";

    private List<ItemHomeMenu> menuArray;

    private HashMap<String, ItemHomeMenu> menuMap;

    RecyclerView mMenuList;

    public NineGirdHomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        mMenuList = root.findViewById(R.id.home_menu_list);

        initMenuValue();

        initMenuListView();

    }

    private void initMenuValue() {

        menuMap = new HashMap<String, ItemHomeMenu>() {
            {
                put("red", new ItemHomeMenu(getResDrawable(R.drawable.ic_home),
                        "Grab red envelopes", LitePalActivity.class));
                put("music", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_slideshow),
                        "Listener mine music", RecyclerViewActivity.class));
                put("item1", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_send),
                        "Bing daily picture", BingDailyPicActivity.class));
                put("item2", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_camera),
                        "TestAutoCompleteTextViewActivity", TestAutoCompleteTextViewActivity.class));
                put("item3", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_share),
                        "TestWebServiceActivity", TestWebServiceActivity.class));
                put("item4", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_share),
                        "ShareToWeChatActivity", ShareToWeChatActivity.class));
                put("item5", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_share),
                        "item1", ThreadPoolActivity.class));
                put("item6", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_share),
                        "UriActivity", UriActivity.class));
                put("item7", new ItemHomeMenu(getResDrawable(R.drawable.ic_menu_share),
                        "item1", ThreadPoolActivity.class));
            }
        };

        menuArray = new ArrayList<>();

        menuArray.add(menuMap.get("red"));
        menuArray.add(menuMap.get("music"));
        menuArray.add(menuMap.get("item1"));
        menuArray.add(menuMap.get("item2"));
        menuArray.add(menuMap.get("item3"));
        menuArray.add(menuMap.get("item4"));
        menuArray.add(menuMap.get("item5"));
        menuArray.add(menuMap.get("item6"));
        menuArray.add(menuMap.get("item7"));


    }

    private Drawable getResDrawable(int res) {
        // TODO: 2017/1/8  api target
        Resources.Theme re = getResources().newTheme();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getResources().getDrawable(res, re);
        } else {
            return null;
        }
    }

    private void initMenuListView() {

        mMenuList.setLayoutManager(new GridLayoutManager(mContext, 3));

        mMenuList.setAdapter(new HomeMenuAdapter(mContext, menuArray) {
            @Override
            public void onItemClick(ItemHomeMenu item, int position) {
                startActivity(item.getActivity());
            }
        });

    }

}
