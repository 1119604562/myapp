package com.example.auction.tast;

import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.auction.R;
import com.example.auction.fragment.MyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class tast_list extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;
    //定义自动完成的列表
    private final String[] mStrings = {"我爱Java","安卓无敌","好好学习"};
    private FrameLayout mainFrame;
    private BottomNavigationView bottomNavigation;
    private MyFragment messageFragment, findFragment, circleFragment, homeFragment, mineFragment;
    private Fragment[] fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener listener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_message) switchFragment(fragments[0]);
            else if (id == R.id.action_find) switchFragment(fragments[1]);
            else if (id == R.id.action_circle) switchFragment(fragments[2]);
            else if (id == R.id.action_home) switchFragment(fragments[3]);
            else if (id == R.id.action_mine) switchFragment(fragments[4]);
            return true;
        }
    };

    // 切换fragment
    private void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.mainContainer, fragment);
        transaction.commitAllowingStateLoss();
    }

        private void initView() {
        messageFragment = new MyFragment();
        messageFragment.setMessage(getResources().getString(R.string.message));
        findFragment = new MyFragment();
        findFragment.setMessage(getResources().getString(R.string.find));
        circleFragment = new MyFragment();
        circleFragment.setMessage(getResources().getString(R.string.circle));
        homeFragment = new MyFragment();
        homeFragment.setMessage(getResources().getString(R.string.home));
        mineFragment = new MyFragment();
        mineFragment.setMessage(getResources().getString(R.string.mine));
        fragments = new Fragment[]{messageFragment, findFragment, circleFragment, homeFragment, mineFragment};
        mainFrame = findViewById(R.id.mainContainer);
        //设置fragment到布局
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, messageFragment)
                .commit();

        bottomNavigation = findViewById(R.id.navigation);
        //这里是bottomnavigationview的点击事件
        bottomNavigation.setOnNavigationItemSelectedListener(listener);
    }

}
