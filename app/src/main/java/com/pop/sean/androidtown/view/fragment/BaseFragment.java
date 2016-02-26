package com.pop.sean.androidtown.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pop.sean.androidtown.ATownApplication;
import com.pop.sean.androidtown.interfaces.BaseFragmentInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements BaseFragmentInterface{

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
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void initData() {

    }
}
