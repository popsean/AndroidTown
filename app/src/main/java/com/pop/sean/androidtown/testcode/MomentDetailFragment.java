package com.pop.sean.androidtown.testcode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.fragment.BaseFragment;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentDetailFragment extends BaseFragment {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.iv)
    ImageView iv;

    private String desc;
    private int ivId;
    public MomentDetailFragment() {
        // Required empty public constructor
    }

    public static MomentDetailFragment newInstance(String s, int imageId) {

        Bundle args = new Bundle();
        args.putString("desc", s);
        args.putInt("iv", imageId);
        MomentDetailFragment fragment = new MomentDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        desc = getArguments().getString("desc");
        ivId = getArguments().getInt("iv");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_moment_detail_layout;
    }

    @Override
    protected void initView() {
        tv.setText(desc);
        iv.setBackgroundResource(ivId);
    }

    @Override
    protected void initData() {

    }

}
