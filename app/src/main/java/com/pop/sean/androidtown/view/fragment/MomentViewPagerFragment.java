package com.pop.sean.androidtown.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.MomentDetailView;
import com.pop.sean.androidtown.view.activity.MainActivity;
import com.pop.sean.androidtown.view.adapter.MomentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentViewPagerFragment extends BaseFragment {

    @Bind(R.id.view_pager)
    ViewPager pager;
    private MomentViewPagerAdapter adapter;


    public MomentViewPagerFragment() {
        // Required empty public constructor
    }

    public static MomentViewPagerFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MomentViewPagerFragment fragment = new MomentViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_moment_viewpager_layout;
    }

    @Override
    protected void initView() {

        String s1 = "我仿佛又突然置身在那座空旷的大厦里，我一在那儿，惊惶的感觉便无可名状的淹了上来，没有什么东西害我，可是那无边无际的惧怕，却是渗透到皮肤里，几乎彻骨。我并不是一个人，四周围着我的是一群影子似的亲人，知道他们爱我，我却仍是说不出的不安，我感觉到他们，可是看不清谁是谁，其中没有荷西，因为没有他在的感觉。";
        String s2 = "我几乎忍不住想问问女友，是不是，是不是洛桑车站的六号月台由大门进去，下楼梯，左转经过通道，再左转上楼梯，便是那儿?是不是入口处正面有一个小小的书报摊?是不是月台上挂着阿拉伯字?是不是卖票的窗口在右边，询问台在左边?还有一个换钱币的地方也在那儿，是不是?";
        String s3 = "我要印证一些事情，在我印证之前，其实已很了然了。因为那不是似曾相识的感觉，那个车站，虽然今生第一次醒着进去，可是梦中所见，都得到了解释，是它，不会再有二个可能了，我真的去了，看了，也完全确定了这件事。";


        List<MomentDetailView> list = new ArrayList<>();
        MomentDetailView mv1 =  new MomentDetailView(getContext());
        mv1.setRes(s1, R.mipmap.moment_detail1);
        list.add(mv1);

        MomentDetailView mv2 =  new MomentDetailView(getContext());
        mv2.setRes(s2, R.mipmap.moment_detail2);
        list.add(mv2);

        MomentDetailView mv3 =  new MomentDetailView(getContext());
        mv3.setRes(s3, R.mipmap.moment_detail3);
        list.add(mv3);


        adapter = new MomentViewPagerAdapter(list);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);

    }

    @Override
    protected void initData() {

    }

}
