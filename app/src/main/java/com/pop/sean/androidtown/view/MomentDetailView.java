package com.pop.sean.androidtown.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pop.sean.androidtown.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO: document your custom view class.
 */
public class MomentDetailView extends FrameLayout {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.fiv)
    FloatImageView fIv;

    public MomentDetailView(Context context) {
        super(context);
        init();
    }

    public MomentDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MomentDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MomentDetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        fIv.registerSensor();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        fIv.unRegisterSensor();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_moment_detail_layout, this, true);
        ButterKnife.bind(this);

    }

    public void setRes(String s, int imageId){
        tv.setText(s);
        fIv.setImageResource(imageId);
    }

}
