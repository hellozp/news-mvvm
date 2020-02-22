package com.zp.newsdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zp.library_base.activity.MvvmActivity;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;
import com.zp.newsdemo.databinding.ActivityMainBinding;
import com.zp.newsdemo.fragment.AccountFragment;
import com.zp.newsdemo.fragment.CategotiesFragment;
import com.zp.newsdemo.fragment.ServiceFragment;

import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends MvvmActivity<ActivityMainBinding, MvvmBaseViewModel> {

    private Fragment homeFragment;
    private CategotiesFragment categotiesFragment = new CategotiesFragment();
    private ServiceFragment serviceFragment = new ServiceFragment();
    private AccountFragment accountFragment = new AccountFragment();

    private Fragment fromFragment;//default init fragment

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
        initBottomNavigationView();
        initFragment();

        viewDataBinding.bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        fragment = homeFragment;
                        break;
                    case R.id.menu_categories:
                        fragment = categotiesFragment;
                        break;
                    case R.id.menu_service:
                        fragment = serviceFragment;
                        break;
                    case R.id.menu_account:
                        fragment = accountFragment;
                        break;
                }

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(menuItem.getTitle());
                }
                //切换Fragment
                switchFragment(fromFragment, fragment);

                return true;
            }
        });
    }

    private void initToolBar() {
        //Set ToolBar
        setSupportActionBar(viewDataBinding.toolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.menu_home);
    }

    private void initBottomNavigationView() {
        //取消默认的tab图标填充效果
        viewDataBinding.bottomView.setItemIconTintList(null);

        //显示角标
        showBadgeView(2, 5);
    }

    private void initFragment() {
        //同步调用，直接返回结果
        CCResult result = CC.obtainBuilder("News")
                .setActionName("getHeadlineNewsFragment")
                .build()
                .call();
        homeFragment = (Fragment) result.getDataMap().get("fragment");
        fromFragment = homeFragment;

        //init fragment show
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(viewDataBinding.container.getId(), homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onRetryBtnClickLoad() {

    }

    //设置菜单按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //添加tab角标
    private void showBadgeView(int viewIndex, int showNumber) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) viewDataBinding.bottomView.getChildAt(0);
        if (viewIndex < bottomNavigationMenuView.getChildCount()) {
            //获取子tab view
            View view = bottomNavigationMenuView.getChildAt(viewIndex);
            //从子tab中获取显示图片对应的imageview
            View icon = view.findViewById(com.google.android.material.R.id.icon);
            int spaceWidth = view.getWidth() / 2 - icon.getWidth();

            new QBadgeView(this)
                    .bindTarget(view)
                    .setGravityOffset(spaceWidth + 50, 13, false)
                    .setBadgeText(showNumber + "");
        }
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (from != to) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                if (from != null) {
                    fragmentTransaction.hide(from);
                }
                if (to != null) {
                    fragmentTransaction.add(viewDataBinding.container.getId(), to).commit();
                }
            } else {
                if (from != null) {
                    fragmentTransaction.hide(from);
                }
                if (to != null) {
                    fragmentTransaction.show(to).commit();
                }
            }
        }
        fromFragment = to;
    }
}
