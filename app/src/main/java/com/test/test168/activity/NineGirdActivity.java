package com.test.test168.activity;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.fragment.NineGirdHomeFragment;
import com.test.test168.fragment.RxAndroidFragment;
import com.test.test168.fragment.SettingFragment;

import java.util.HashMap;
import java.util.Map;

public class NineGirdActivity extends BaseActivity {

    BottomNavigationView mMainBottomMenu;

    private Map<String, Fragment> homeFragment;
    private Fragment mCurrentFragment = null;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_nine_gird);
//        ButterKnife.bind(this);
        mMainBottomMenu = findViewById(R.id.main_bottom_menu);
        initNavMenu();
        changeFragment(homeFragment.get(NineGirdHomeFragment.TAG));
    }

    private void initNavMenu() {
        homeFragment = new HashMap<>();
        homeFragment.put(NineGirdHomeFragment.TAG, new NineGirdHomeFragment());
        homeFragment.put(RxAndroidFragment.TAG, new RxAndroidFragment());
        homeFragment.put(SettingFragment.TAG, new SettingFragment());
        mMainBottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()) return false;
                switch (item.getItemId()) {
                    case R.id.main_nav_home:
                        changeFragment(homeFragment.get(NineGirdHomeFragment.TAG));
                        break;
                    case R.id.main_nav_rxjava:
                        changeFragment(homeFragment.get(RxAndroidFragment.TAG));
                        break;
                    case R.id.main_nav_setting:
                        changeFragment(homeFragment.get(SettingFragment.TAG));
                        break;
                    default:
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

    public void onClick() {
    }
}
