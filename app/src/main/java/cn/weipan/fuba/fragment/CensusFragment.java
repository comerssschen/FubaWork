package cn.weipan.fuba.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.weipan.fuba.R;
import cn.weipan.fuba.base.BaseFragment;

public class CensusFragment extends BaseFragment {
    @BindView(R.id.census_topbar)
    QMUITopBar censusTopbar;
    Unbinder unbinder;

    @Override
    protected int initLayout() {
        return R.layout.avtivity_censusfragment;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {

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
