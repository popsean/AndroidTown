package com.pop.sean.androidtown.view.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.pop.sean.androidtown.R;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseListFragment extends BaseFragment {

    @Bind(R.id.list_view)
    protected  ListView mListView;
    @Bind(R.id.view_pager_ptr_frame)
    PtrFrameLayout ptrFrameLayout;

    public BaseListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    protected void initView() {
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return BaseListFragment.this.checkCanDoRefresh();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                BaseListFragment.this.updateData();
                ptrFrameLayout.refreshComplete();
            }
        });
    }

    @Override
    protected void initData() {

    }

    public boolean checkCanDoRefresh() {
        if (this.getItemsCount() == 0 || mListView == null) {
            return true;
        }
        Log.d("test", String.format("checkCanDoRefresh: %s %s", mListView.getFirstVisiblePosition(), mListView.getChildAt(0).getTop()));
        return mListView.getFirstVisiblePosition() == 0 && mListView.getChildAt(0).getTop() == 0;
    }

    protected abstract int getItemsCount();
    protected abstract void updateData();

}
