package com.pop.sean.androidtown.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pop.sean.androidtown.ATownApplication;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment{

    protected LayoutInflater mInflater;
    protected Activity mActivity;

    public ATownApplication getApplication() {
        return (ATownApplication) getActivity().getApplication();
    }

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    protected int getLayoutId() {
        return 0;
    }
    protected abstract void initView();
    protected abstract void initData();

}
