package com.pop.sean.androidtown.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class FloatImageView extends ImageView implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    private static int width;
    private static int height;
    Drawable d;
    private float oldDegree = 0;


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
//        getDrawable().setAlpha(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        d = getDrawable();
        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges
            //宽度根据使得图片的高度充满屏幕计算而得

            height = MeasureSpec.getSize(heightMeasureSpec);
            width = (int) (int) Math.ceil((float) height * (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight());
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
        if (degree < 3) return;
        if (degree > 87) return;
        float fromX = oldDegree *10;
        float toX = -degree *10;
//        Log.d("AAA", "currentDegree: " + degree + ", oldDegree: " + oldDegree + ", fromX: " + fromX + ", toX:" + toX);
        TranslateAnimation ta = new TranslateAnimation(fromX, toX, 0, 0);
        ta.setDuration(100);
        ta.setFillAfter(true);
        startAnimation(ta);
        oldDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}