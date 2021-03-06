package com.pop.sean.androidtown.testcode;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.pop.sean.androidtown.util.ScreenUtils;

import java.util.Calendar;

public class AnimatView5 extends ImageView implements SensorEventListener {

    private static final int DELTA = 10;

    private SensorManager sensorManager;
    private Sensor sensor;
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
    private float currentDegree = 0;
    private float prioX = 0;


    public AnimatView5(Context context) {
        super(context);
        init();
    }

    public AnimatView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ORIENTATION);

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

//    @Override
//    protected void onDraw(Canvas canvas) {
//
//
////        getDrawable().setBounds(currentX, 0, currentX + width, height);
//
//        if (direction == INC){
//            drawLeft += X_SPEED;
//            if (drawLeft > currentLeft){
//                drawLeft = currentLeft;
//                direction = 0;
//            }
//        }else if (direction == DEC){
//            drawLeft -= X_SPEED;
//            if (drawLeft < currentLeft){
//                drawLeft = currentLeft;
//                direction = 0;
//            }
//        }
//
//        Log.d("ZZZ", "onDraw: direction: " + direction + ", drawLeft: " + drawLeft + ", currentLeft: "  +  currentLeft + ", base: " + mBase +
//                ", mDetal: " + mDelta + ", MaxOffset" + mMaxOffSet + "<----");
//        getDrawable().setBounds(drawLeft, 0, drawLeft + width, height);
//        getDrawable().draw(canvas);
//        invalidate();
//    }

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
        float degree = event.values[0];
        RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
//        //旋转过程持续时间
//        ra.setDuration(200);
//        //罗盘图片使用旋转动画
//        this.startAnimation(ra);
//
//        currentDegree = -degree;
        float x = degree/10;
        TranslateAnimation ta = new TranslateAnimation(currentDegree, -degree, 0, 0);
        ta.setDuration(200);
        startAnimation(ta);

        currentDegree = -degree;
        prioX = -degree / 10;

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