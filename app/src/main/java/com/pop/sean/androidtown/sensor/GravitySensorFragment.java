package com.pop.sean.androidtown.sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.AnimatView;
import com.pop.sean.androidtown.view.fragment.BaseFragment;

import butterknife.Bind;

/**
 * Created by stanzhao on 2/28/16.
 */
public class GravitySensorFragment extends BaseFragment{
//    private SensorManager sensorManager;
//    private Sensor accelerometer;
//    private long lastUpdate;

    @Bind(R.id.av)
    AnimatView animatedView;
    public static int x;
    public static int y = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

//        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
//        accelerometer = sensorManager
//                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        lastUpdate = System.currentTimeMillis();

//        animatedView = new AnimatedView(getContext());
//        ViewGroup.LayoutParams parms = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        animatedView.setLayoutParams(parms);
//        animatedView.setScaleType(ImageView.ScaleType.FIT_XY);
//        animatedView.setAdjustViewBounds(true);
//        animatedView.setImageResource(R.mipmap.test_item1);
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
        animatedView.registerSensor();
    }

    @Override
    public void onPause() {
        super.onPause();
        animatedView.unRegisterSensor();
    }
//
//    @Override
//    public void onAccuracyChanged(Sensor arg0, int arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        // TODO Auto-generated method stub
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//
//            x -= (int) event.values[0];
////            y += (int) event.values[1];
//
//        }
//    }

}
