package com.pop.sean.androidtown.testcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.pop.sean.androidtown.util.ScreenUtils;

import java.util.Calendar;

public class AnimatView3 extends ImageView implements SensorEventListener {

    private static final int DELTA = 10;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastTime = 0;
    private long nowTime = 0;

    AnimatView5 animatedView = null;


//    private int mOldSensorX = 99999;
//    private int mNowSensorX = 99999;
//    private static int xMaxOffSet = 0;
//    private static int xOffset = 0;



    private static int width;
    private static int height;
    Drawable d;
    private static int mScreenWidth;
    private long mSensorTimeStamp;
    private long mCpuTimeStamp;




    private Calendar mCalendar;
    private int mY, mZ;
    private long lasttimestamp = 0;



    //x轴翻转角度为0时，x坐标的原点
    private int mBase = 0;

    //取整个翻转角度跨度为[-9, 10],每个角度等分X轴上最大偏移量
    private int mDelta = 0;

    //根据相应图片的宽度算出最大偏移量:  图片宽度 - 屏幕宽度
    private int mMaxOffSet = 0;

    //每次重绘时X轴图像偏移速度
    private static final int X_SPEED = 3;

    //X轴偏移方向
    private int direction = 0;
    //从左向右 X要加正数
    private static int INC = 1;
    //从右向左 X要加负数
    private static int DEC = 2;

    //当前x轴坐标
    private static int currentX = 0;

    //上次的x轴角度
    private int mOldSensorX;
    //这次的x轴角度
    private int mNowSensorX;
    //这次需要draw的左边界
    private int currentLeft;
    //这次需要draw的右边界
    private int currentRight;

    //当前正在画的左右边界
    private int drawLeft;
    private int drawRgiht;


    public AnimatView3(Context context) {
        super(context);
        init();
    }

    public AnimatView3(Context context, AttributeSet attrs) {
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
            mMaxOffSet = mScreenWidth - width; //e.g: 720 - 1420= -700
            mDelta = Math.abs(mMaxOffSet / 20); //   700/20 = 35
            mBase = mMaxOffSet / 2; //    -700/2=-350
            drawLeft = mBase;
            drawRgiht = drawLeft + width;
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {


//        getDrawable().setBounds(currentX, 0, currentX + width, height);

        if (direction == INC){
            drawLeft += X_SPEED;
            if (drawLeft > currentLeft){
                drawLeft = currentLeft;
                direction = 0;
            }
        }else if (direction == DEC){
            drawLeft -= X_SPEED;
            if (drawLeft < currentLeft){
                drawLeft = currentLeft;
                direction = 0;
            }
        }

        Log.d("ZZZ", "onDraw: direction: " + direction + ", drawLeft: " + drawLeft + ", currentLeft: "  +  currentLeft + ", base: " + mBase +
                ", mDetal: " + mDelta + ", MaxOffset" + mMaxOffSet + "<----");
        getDrawable().setBounds(drawLeft, 0, drawLeft + width, height);
        getDrawable().draw(canvas);
        invalidate();
    }

    public void registerSensor() {
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }

    public void unRegisterSensor() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor == null) {
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mNowSensorX = (int) event.values[0];
            int y = (int) event.values[1];
            int z = (int) event.values[2];
            mCalendar = Calendar.getInstance();
//            long stamp = mCalendar.getTimeInMillis() / 1000l;// 1393844912
            long stamp = event.timestamp /1000 / 1000;// 1393844912

            int second = mCalendar.get(Calendar.SECOND);// 53

            int px = Math.abs(mOldSensorX - mNowSensorX);
            int py = Math.abs(mY - y);
            int pz = Math.abs(mZ - z);
            Log.d("ZZZ", "--->pX:" + px + "  mNowSensorX:" + mNowSensorX + "  mOldSensorX:" + mOldSensorX + "    stamp:"
                    + stamp + ", lasttimestamp: " + lasttimestamp + ",  second:" + second);
//            int maxvalue = getMaxValue(px, py, pz);
            if (mNowSensorX > 10 )  mNowSensorX = 10;
            if (mNowSensorX < -9 )  mNowSensorX = -9;
            if (px >= 2  && (stamp - lasttimestamp) > 50) {
                lasttimestamp = stamp;
//                Log.d("ZZZ", " sensor isMoveorchanged.... ");
                if (mNowSensorX - mOldSensorX > 0){
                    //左向右
                    direction = INC;
                }else {
                    direction = DEC;
                }
                currentLeft = mBase - (mNowSensorX * mDelta);
                currentRight = currentLeft + mScreenWidth;

//                Log.d("ZZZ", "drawLeft > currenetLeft: " + (drawLeft>currentLeft?1:0));
//                if (direction == INC){
//                    for (int i=0;i<50;i++) {
//                        drawLeft += X_SPEED;
//                        invalidate();
//                    }
//                            direction = 0;
//                }else if (direction == DEC){
//                    for (int i=0;i<50;i++) {
//                        drawLeft -= X_SPEED;
//                        invalidate();
//                    }
//                    direction = 0;
//                }
            }
//            drawLeft = mBase - (mNowSensorX * mDelta);

            mOldSensorX = mNowSensorX;
            mY = y;
            mZ = z;

        }

    }

    public int getMaxValue(int px, int py, int pz) {
        int max = 0;
        if (px > py && px > pz) {
            max = px;
        } else if (py > px && py > pz) {
            max = py;
        } else if (pz > px && pz > py) {
            max = pz;
        }

        return max;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}