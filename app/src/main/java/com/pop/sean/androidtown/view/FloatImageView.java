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
    private Sensor sensor;


    private static int width;
    private static int height;
    Drawable d;
    private float oldDegree = 0;
    private static int mCaculateMaxOffset = 0;


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
        sensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ORIENTATION);
//        Log.d("AAA", " AAAAA  currentDegree: " + degree + ", oldDegree: " + oldDegree + ", fromX: " + fromX + ", toX:" + toX);
//        TranslateAnimation ta = new TranslateAnimation(-400, 0, 0, -0);
//        TranslateAnimation ta = new TranslateAnimation(oldDegree, -degree, 0, 0);
//        ta.setDuration(2000);
//        ta.setFillAfter(true);
//        startAnimation(ta);
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
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void registerSensor() {
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_GAME);
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
//        getDrawable().setAlpha(255);
        float degree = event.values[0];
        Log.d("AAA", "  ==== > currentDegree: " + degree + ", oldDegree: " + oldDegree);
        if (degree > 180){degree = 180;}
//        if (degree < 3) return;
//        if (degree > 87) return;
        float fromX = oldDegree * 4; // [-964, 0]
        float toX = -degree * 4;
//        Log.d("AAA", "x:" + event.values[0] + ", y: " + event.values[1] + ", z: " + event.values[2]);
//        TranslateAnimation ta = new TranslateAnimation(0, -mCaculateMaxOffset, 0,
//        TranslateAnimation ta = new TranslateAnimation(--mCaculateMaxOffset /2, ATownEnv.getScreenWidth() / 2, 0, 0);
        if (fromX > 0) fromX = 0;
        if (toX > 0) toX = 0;
        if (fromX < -mCaculateMaxOffset) fromX = -mCaculateMaxOffset;
        if (toX < -mCaculateMaxOffset) toX = -mCaculateMaxOffset;


        Log.d("AAA", " currentDegree: " + degree + ", oldDegree: " + oldDegree + ", fromX: " + fromX + ", toX:" + toX + "<====");
        TranslateAnimation ta = new TranslateAnimation(fromX, toX, 0, 0);
//        TranslateAnimation ta = new TranslateAnimation(oldDegree, -degree, 0, 0);
        ta.setDuration(18);
        ta.setFillAfter(true);
        startAnimation(ta);
        oldDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}