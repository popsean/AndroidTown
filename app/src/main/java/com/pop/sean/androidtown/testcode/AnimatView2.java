package com.pop.sean.androidtown.testcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.pop.sean.androidtown.util.ScreenUtils;

public class AnimatView2 extends ImageView implements SensorEventListener {

    private static final int DELTA = 10;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastTime = 0;
    private long nowTime = 0;

    AnimatView2 animatedView = null;
    private static int currentX = 0;

    private int mOldSensorX = 99999;
    private int mNowSensorX = 99999;
    private static int xMaxOffSet = 0;
    private static int xOffset = 0;


    private int dir = 0;
    private static int INC = 1;
    private static int DEC = 2;


    private static int width;
    private static int height;
    Drawable d;
    private static int mScreenWidth;
    private long mSensorTimeStamp;
    private long mCpuTimeStamp;

    public AnimatView2(Context context) {
        super(context);
        init();
    }

    public AnimatView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

//        lastUpdate = System.currentTimeMillis();

        mScreenWidth = ScreenUtils.getScreenRealWidth(getContext());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        d = getDrawable();
        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges
//                int width = MeasureSpec.getSize(widthMeasureSpec);
            //高度根据使得图片的宽度充满屏幕计算而得
//                int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());

            height = MeasureSpec.getSize(heightMeasureSpec);
            width = (int) (int) Math.ceil((float) height * (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight());
            xMaxOffSet = width - mScreenWidth;
            xOffset = xMaxOffSet / 100;
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (dir == DEC){
            currentX -= DELTA;
         }else if(dir == INC){
            currentX += DELTA;
        }

//        if (currentX < (width - mScreenWidth) * mNowSensorX / 20) {
//            currentX = (width - mScreenWidth) * mNowSensorX / 20;
//            if (currentX < mScreenWidth - width)
//                currentX = mScreenWidth - width;
//        }
//        if (currentX > (mScreenWidth - width) * mNowSensorX / 20) {
//            currentX = (mScreenWidth - width) * mNowSensorX / 20;
//            if (currentX > 0 ) currentX = 0;
//        }
//        if (currentX < (width - mScreenWidth) * mNowSensorX / 20) {
//            currentX = (width - mScreenWidth) * mNowSensorX / 20;
//            if (currentX < mScreenWidth - width)
//                currentX = mScreenWidth - width;
//        }
//        if (currentX > (mScreenWidth - width) * mNowSensorX / 20) {
//            currentX = (mScreenWidth - width) * mNowSensorX / 20;
//            if (currentX > 0) currentX = 0;
//        }

        getDrawable().setBounds(currentX, 0, currentX + width, height);
        getDrawable().draw(canvas);
        invalidate();
    }

    public void registerSensor() {
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void unRegisterSensor() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

//            if (mOldSensorX == 99999 && mNowSensorX == 99999) {
//                mNowSensorX = (int)(event.values[0]*1000);
//                mOldSensorX = mNowSensorX;
//                return;
//            }
//            mNowSensorX = (int)(event.values[0]*1000);
//
//            int temp = mNowSensorX - mOldSensorX;
//            if (Math.abs(mNowSensorX - mOldSensorX) >= 150) {
//                Log.d("ZZZAV", "----> mOldSensorX:" + mOldSensorX + ", mNowSensorX: " + mNowSensorX + ", temp: " + temp);
//                if (mNowSensorX - mOldSensorX > 0) {
////                    currentX  += xOffset;
//                    dir = INC;
//                }
//                else {
////                    currentX -= xOffset;
//                    dir = DEC;
//                }
//                mOldSensorX = mNowSensorX;
//            }else {
//                dir = 0;
//            }
//
//            Log.d("ZZZAV", "currentX: " + currentX + "<----");
//            mNowSensorX = (int)(event.values[0]);
//            currentX -= mNowSensorX;
//            Log.d("ZZZAV", "----> mNowSensorX: " + mNowSensorX );
//            if (mNowSensorX < -150){
//                inc = false;
//            }else if(mNowSensorX > 150) {
//                inc = true;
//            }

            if (lastTime == 0 && nowTime == 0) {
                nowTime = event.timestamp;
                lastTime = nowTime;
                mNowSensorX = (int) event.values[0] * 1000;
                mOldSensorX = mNowSensorX;
                dir = 0;
                return;
            }
            nowTime = event.timestamp;
            if (nowTime * 1000 * 1000 - lastTime * 1000 * 1000 < 30) return;

            mNowSensorX = (int) (event.values[0] * 1000);
            if (Math.abs(mNowSensorX - mOldSensorX) > 100) {
                if (mNowSensorX - mOldSensorX > 0) {
                    dir = INC;
                } else {
                    dir = DEC;
                }
                xOffset = (xMaxOffSet / 20 * mNowSensorX / 1000);
                mOldSensorX = mNowSensorX;
            }else {
                dir = 0;
            }
            lastTime = nowTime;

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}