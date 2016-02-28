package com.pop.sean.androidtown.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
    @Bind(R.id.iv)
    ImageView iv;

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


    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_moment_detail_layout, this, true);
        ButterKnife.bind(this);

    }

    public void setRes(String s, int imageId){
        tv.setText(s);
        iv.setBackgroundResource(imageId);
    }

}
