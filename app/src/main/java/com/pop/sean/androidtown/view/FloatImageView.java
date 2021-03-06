package com.pop.sean.androidtown.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.pop.sean.androidtown.ATownEnv;

public class FloatImageView extends ImageView implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor orientSensor;


    private static int width;
    private static int height;
    Drawable d;
    private double oldDegree = 0;
    private static int mCaculateMaxOffset = 0;
    private static float mCaculateXDetal = 0;

    public FloatImageView(Context context) {
        super(context);
        init();
    }

    public FloatImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        orientSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        d = getDrawable();
        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges
            //宽度根据使得图片的高度充满屏幕计算而得

            height = MeasureSpec.getSize(heightMeasureSpec);
            width = (int) (int) Math.ceil((float) height * (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight());
//            Log.d("BBB", "height: " + height + ", width: " + width + ", screenW: " + ATownEnv.getScreenWidth());
            mCaculateMaxOffset = width - ATownEnv.getScreenWidth();
            mCaculateXDetal = mCaculateMaxOffset / 80;
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void registerSensor() {
        sensorManager.registerListener(this, orientSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void unRegisterSensor() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor == null || event.sensor.getType() != Sensor.TYPE_ORIENTATION) {
            return;
        }

        double degree = event.values[2];
        //只选取一共80°的夹角范围
        if (degree > 40) degree = 40;
        if (degree < -40) degree = -40;
        degree = -degree;
        degree -= 40;

        //去抖
        if (Math.abs(degree - oldDegree) < 1.5) return;

        float fromX = (float) (oldDegree * mCaculateXDetal);
        float toX = (float) (degree * mCaculateXDetal);
        Log.d("BBB", ", olddegree: " + oldDegree + ", degree: " + degree + ", mCaculateXDetal: " + mCaculateXDetal
                + ", fromX: " + fromX + ", toX: " + toX);

        TranslateAnimation ta = new TranslateAnimation(fromX, toX, 0, 0);
        //GAME模式传感器频率是50Hz,这里每20ms刷新一次动画
        ta.setDuration(20);
//        ta.setInterpolator(new DecelerateInterpolator());
        ta.setFillAfter(true);
        startAnimation(ta);
        oldDegree = degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}