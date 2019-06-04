package cn.weipan.fuba.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.weipan.fuba.R;
import cn.weipan.fuba.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.lineyout1)
    LinearLayout lineyout1;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.lineyout2)
    LinearLayout lineyout2;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_ratio)
    TextView tvRatio;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.head_view)
    RelativeLayout headView;
    List<String> head_list ;
    //TodayCount.AllTotalBean allTotal;
    @Override
    protected int initLayout() {
        return R.layout.avtivity_homefragment;
    }

    @Override
    protected void initView(View view) {
        head_list = new ArrayList<>();
        head_list.add("今日收款");
        head_list.add("本月收款");
        topbar.setBackground(getResources().getDrawable(R.color.mycolor));
        topbar.setTitle("收银首页").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QMUIDialog qmuiDialog = new QMUIDialog(getContext());
                qmuiDialog.show();
            }
        });
        /*topbar.addLeftTextButton("返回",R.id.topbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"vvvvvv",Toast.LENGTH_LONG).show();
            }
        });
        topbar.addRightImageButton(R.mipmap.tab1_1,R.id.topbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"cxxxxxxx",Toast.LENGTH_LONG).show();
            }
        });*/

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        //banner.setImageLoader(new GlideImageHomeLoader());
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                Log.d("ccc",i+" "+v+"  "+i1);
            }

            @Override
            public void onPageSelected(int i) {
                if (tvDesc != null)
                    tvDesc.setText(head_list.get(i));
                if (tvRatio != null && tvTotal != null) {
                    switch (i) {
                        case 0:
                            tvRatio.setText(/*allTotal.getTotalDayMoney()*/"1212");
                            tvTotal.setText("（合计" +/* allTotal.getTotalDayCount()*/"22" + "笔)");
                            break;
                        case 1:
                            tvRatio.setText(/*allTotal.getTotalMonthMoney() +*/ "3250");
                            tvTotal.setText("（合计" + /*allTotal.getTotalMonthCount()*/ "11"+ "笔）");
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                Log.d("aaa",i+"");
            }
        });
        banner.setImages(head_list);
        banner.start();
    }

    @Override
    protected void initData() {
        lineyout1.setBackgroundColor(getResources().getColor(R.color.mycolor));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
