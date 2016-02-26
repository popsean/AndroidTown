package com.pop.sean.androidtown.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.MomentsView;
import com.pop.sean.androidtown.view.activity.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentsFragment extends BaseFragment implements MomentsView {

//    MomentsPresenter momentsPresenter;
    private String mTitle = "empty";

    TextView tv;

    public MomentsFragment() {
        // Required empty public constructor
    }

//    public MomentsFragment(MomentsPresenter mp) {
//        // Required empty public constructor
//        this.momentsPresenter = mp;
//        mp.setView(this);
//    }

    public static final MomentsFragment newInstance(String title) {
        MomentsFragment momentsFragment = new MomentsFragment(/*new MomentsPresenter()*/);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        momentsFragment.setArguments(bundle);
        return momentsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getString("title");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_moments_layout;
    }

    @Override
    protected void initView() {
        ((BaseActivity)getActivity()).setToolBarTitle(mTitle);
//        ((TextView)getActivity().findViewById(R.id.tv)).setText(mTitle);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
        this.loadMoments();
    }

    private void initialize() {
//        this.momentsPresenter.setView(this);
    }

    private void loadMoments() {
//        this.momentsPresenter.initialize();
    }


    @Override
    public void viewMoment() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }
}
