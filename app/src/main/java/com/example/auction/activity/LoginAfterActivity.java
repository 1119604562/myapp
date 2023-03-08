package com.example.auction.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.auction.R;
import com.example.auction.fragment.MyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;


import androidx.annotation.NonNull;


/**
 * 登录成功后的界面
 */
public class LoginAfterActivity extends AppCompatActivity {


    private FrameLayout mainFrame;
    private BottomNavigationView bottomNavigation;
    private MyFragment messageFragment, findFragment, circleFragment, homeFragment, mineFragment;
    private Fragment[] fragments;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_after);
        initView();

        //---------------------------------------------------------------------------------
//   底部栏
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
                //添加消息第一种方式
//        //获取整个的NavigationView
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
//        //这里就是获取所添加的每一个Tab(或者叫menu)，
//        View tab = menuView.getChildAt(0);
//        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
//        //加载我们的角标View，新创建的一个布局
//        View badge = LayoutInflater.from(this).inflate(R.layout.item_red_dot, menuView, false);
//        //添加到Tab上
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(badge.getLayoutParams());
//        lp.gravity = Gravity.TOP | Gravity.END;
//        itemView.addView(badge, lp);

                //添加消息红点第二种方式
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
//        View tab = menuView.getChildAt(0);
//        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
//        //从系统里面获取图标的ImageView
//        ImageView iv = itemView.findViewById(R.id.icon);
//        //加载我们的角标View，新创建的一个布局
//        final View badge = LayoutInflater.from(this).inflate(R.layout.item_red_dot, menuView, false);
//        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(badge.getLayoutParams());
//        //等到有高度以后在布局
//        iv.post(new Runnable() {
//            @Override
//            public void run() {
//                lp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
//                lp.topMargin = iv.getTop() - lp.height / 2;//图片top - 消息点的高度一半
//                lp.leftMargin = iv.getWidth() / 2;//图片的宽度的一半
//                itemView.addView(badge, lp);
//
//            }
//        });

        BottomNavigationMenuView   menuView2 = (BottomNavigationMenuView) navigation.getChildAt(0);
        //拿到中间的那个tab
        View tab2 = menuView2.getChildAt(2);
        BottomNavigationItemView itemView2 = (BottomNavigationItemView) tab2;
        //从系统里面获取图标的ImageView
        ImageView iv2 = itemView2.findViewById(R.id.icon);//也可以这样获取 itemView2.getChildAt(0);

        //设置文字居中
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        iv2.setLayoutParams(lp);

        View tv2 = itemView2.getChildAt(1);
        tv2.setVisibility(View.GONE);


        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("SubTitle");


        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.icon_home);
        //设置导航图标、添加菜单点击事件要在setSupportActionBar方法之后
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_classify) {
                    Toast.makeText(LoginAfterActivity.this,
                            "Search !", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}



































//    /**
//     * 返回登录界面，不消除用户和密码
//     */
//    public void toLogin(View view) {
//        //不用自动登录就可
//        //获取SharedPreferences对象，使用自定义类的方法来获取对象
//        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
//        //创建记住密码和自动登录是默认不选,密码为空
//        helper.putValues(new SharedPreferencesUtils.ContentValue("autoLogin", false));
//        startActivity(new Intent(this, LoginActivity.class));
//
//    }
//
//    /**
//     * 返回登录界面，消除用密码
//     */
//    public void toLogin2(View view) {
//        //置空密码即可
//        //获取SharedPreferences对象，使用自定义类的方法来获取对象
//        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
//        //创建记住密码和自动登录是默认不选,密码为空
//        helper.putValues(
//                new SharedPreferencesUtils.ContentValue("remenberPassword", false),
//                new SharedPreferencesUtils.ContentValue("autoLogin", false),
//                new SharedPreferencesUtils.ContentValue("password", ""));
//        startActivity(new Intent(this, LoginActivity.class));
//    }


//
////  旧搜索框
//        listView = (ListView) findViewById(R.id.lv);
//final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings);
//        listView.setAdapter(adapter);
//        //为ListView启动过滤
//        listView.setTextFilterEnabled(true);
//        searchView = (SearchView) findViewById(R.id.sv);
//        //设置SearchView自动缩小为图标
//        searchView.setIconifiedByDefault(false);//设为true则搜索栏 缩小成俄日一个图标点击展开
//        //设置该SearchView显示搜索按钮
//        searchView.setSubmitButtonEnabled(true);
//        //设置默认提示文字
//        searchView.setQueryHint("输入您想查找的内容");
//        //配置监听器
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////点击搜索按钮时触发
//@Override
//public boolean onQueryTextSubmit(String query) {
//        //此处添加查询开始后的具体时间和方法
//        Toast.makeText(LoginAfterActivity.this, "you choose:" + query, Toast.LENGTH_SHORT).show();
//        return false;
//        }
//
//@Override
//public boolean onQueryTextChange(String newText) {
//        //如果newText长度不为0
//        if (TextUtils.isEmpty(newText)) {
//        listView.clearTextFilter();
//        } else {
//        listView.setFilterText(newText);
////          adapter.getFilter().filter(newText.toString());//替换成本句后消失黑框！！！
//        }
//        return true;
//        }
//        });
//        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
//@Override
//public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Object string = adapter.getItem(position);
//        searchView.setQuery(string.toString(), true);
//        }
//        });