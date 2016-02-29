package com.pop.sean.androidtown.sensor;

import android.os.Bundle;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.FloatImageView;
import com.pop.sean.androidtown.view.fragment.BaseFragment;

import butterknife.Bind;

/**
 * Created by stanzhao on 2/28/16.
 */
public class GravitySensorFragment extends BaseFragment{

    @Bind(R.id.av)
    FloatImageView floatIv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static GravitySensorFragment newInstance() {

        Bundle args = new Bundle();

        GravitySensorFragment fragment = new GravitySensorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gtravity_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        floatIv.registerSensor();
    }

    @Override
    public void onPause() {
        super.onPause();
        floatIv.unRegisterSensor();
    }

}
