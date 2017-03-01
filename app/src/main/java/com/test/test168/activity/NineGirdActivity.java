package com.test.test168.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.fragment.HomeFragment;
import com.test.test168.fragment.SettingFragment;
import com.test.test168.fragment.TestDialogFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NineGirdActivity extends BaseActivity {

    @Bind(R.id.main_bottom_menu)
    BottomNavigationView mMainBottomMenu;

    private Map<String, Fragment> homeFragment;
    private Fragment mCurrentFragment = null;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_nine_gird);
        ButterKnife.bind(this);
        initNavMenu();
    }

    private void initNavMenu() {
        homeFragment = new HashMap<>();
        homeFragment.put("home", new HomeFragment());
        homeFragment.put("test", new TestDialogFragment());
        homeFragment.put("setting", new SettingFragment());
        mMainBottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()) return false;
                switch (item.getItemId()) {
                    case R.id.main_nav_home:
                        changeFragment(homeFragment.get("home"));
                        break;
                    case R.id.main_nav_nothing:
                        changeFragment(homeFragment.get("test"));
                        break;
                    case R.id.main_nav_setting:
                        changeFragment(homeFragment.get("setting"));
                        break;
                }
                return true;
            }
        });
    }

    private void changeFragment(Fragment target) {
        if (target == null || target.equals(mCurrentFragment)) return;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != null) fragmentTransaction.hide(mCurrentFragment);
        if (target.isAdded()) {
            fragmentTransaction.show(target);
        } else {
            fragmentTransaction.add(R.id.frameLayout_content, target);
        }
        fragmentTransaction.commitAllowingStateLoss();
//        fragmentTransaction.commitNowAllowingStateLoss();
        mCurrentFragment = target;
    }

    @OnClick(R.id.submit)
    public void onClick() {
    }
}
