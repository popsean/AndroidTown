package com.pop.sean.androidtown.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.pop.sean.androidtown.view.MomentsView;
import com.pop.sean.androidtown.view.adapter.MomentListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentsListFragment extends BaseListFragment implements MomentsView {

//    MomentsPresenter momentsPresenter;

    private String mTitle = "empty";
    private MomentListAdapter adapter;

    public MomentsListFragment() {
        // Required empty public constructor
    }


    public static final MomentsListFragment newInstance(String title) {
        MomentsListFragment momentsFragment = new MomentsListFragment(/*new MomentsPresenter()*/);
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
    protected void initView() {
//        ((BaseActivity)getActivity()).setToolBarTitle(mTitle);
//        ((TextView)getActivity().findViewById(R.id.tv)).setText(mTitle);
        super.initView();
        ArrayList<String> list = new ArrayList<>();
        list.add("111111");
        list.add("122222");
        list.add("13333");
        list.add("13333");
        list.add("13333");
        list.add("13333");
        list.add("13333");
        list.add("13333");
        list.add("13333");
        adapter = new MomentListAdapter(getContext(), list);
        mListView.setAdapter(adapter);
    }

    @Override
    protected int getItemsCount() {
        return adapter.getCount();
    }

    @Override
    protected void updateData() {

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
