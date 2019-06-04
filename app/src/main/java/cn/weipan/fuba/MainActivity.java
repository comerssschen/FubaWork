package cn.weipan.fuba;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.weipan.fuba.base.BaseActivity;
import cn.weipan.fuba.fragment.BossFragment;
import cn.weipan.fuba.fragment.CensusFragment;
import cn.weipan.fuba.fragment.HomeFragment;
import cn.weipan.fuba.fragment.MessageFragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.iv_personal)
    ImageView ivPersonal;
    @BindView(R.id.tv_nicheng)
    TextView tvNicheng;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.ll_personal)
    LinearLayout llPersonal;
    @BindView(R.id.iv_cotent)
    ImageView ivCotent;
    @BindView(R.id.tv_network)
    TextView tvNetwork;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;
    @BindView(R.id.rl_updata)
    RelativeLayout rlUpdata;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;
    @BindView(R.id.rl_login_out)
    RelativeLayout rlLoginOut;
    @BindView(R.id.rl_drawerlayout)
    RelativeLayout rlDrawerlayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager(), 720, 1280)
                .setImgSize(45, 45)
                .setFontSize(22)
                .setTabPadding(5, 0, 5)
                .setChangeColor(Color.parseColor("#0f9ee2"), Color.parseColor("#CCCCCC"))
                .addTabItem("收银首页", R.mipmap.tab1_1, R.mipmap.tab1_2, HomeFragment.class)
                .addTabItem("账单统计", R.mipmap.tab2_1, R.mipmap.tab2_2, CensusFragment.class)
                .addTabItem("老板赚钱", R.mipmap.tab3_1, R.mipmap.tab3_2, BossFragment.class)
                .addTabItem("通知信息", R.mipmap.tab4_1, R.mipmap.tab4_2, MessageFragment.class)
                .isShowDivider(true)
                .setDividerColor(Color.parseColor("#0f9ee2"))
                .setTabBarBackgroundColor(Color.parseColor("#00FF0000"))
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        if (position == 1)
                            bottomTabBar.setSpot(1, false);
                    }
                })
                .setSpot(1, true)
                .setSpot(2, true);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }
}
