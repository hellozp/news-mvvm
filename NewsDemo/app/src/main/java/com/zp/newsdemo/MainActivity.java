package com.zp.newsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.zp.library_base.activity.MvvmActivity;
import com.zp.library_base.viewmodel.MvvmBaseViewModel;
import com.zp.newsdemo.databinding.ActivityMainBinding;

import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends MvvmActivity<ActivityMainBinding, MvvmBaseViewModel> {

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

        //Set ToolBar
        setSupportActionBar(viewDataBinding.toolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.menu_home);

        //取消默认的tab图标填充效果
        viewDataBinding.bottomView.setItemIconTintList(null);

        //显示角标
        showBadgeView(2, 5);
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
}
