package com.pop.sean.androidtown.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.fragment.BaseFragment;

import java.util.Calendar;

import butterknife.Bind;

/**
 * Created by stanzhao on 2/28/16.
 */
public class GravitySensorFragment2 extends BaseFragment{

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private final static String TAG = "TOWN-SENSOR";
    private int mX, mY, mZ;
    private long lasttimestamp = 0;
    Calendar mCalendar;

    @Bind(R.id.tvX)
    TextView textviewX;

    @Bind(R.id.tvY)
    TextView textviewY;

    @Bind(R.id.tvZ)
    TextView textviewZ;

    @Bind(R.id.tv)
    TextView tv;

    @Bind(R.id.movingTv)
    TextView movingTv;
    private AnimationSet animationSet;
    private TranslateAnimation translateAnimation;


    public static GravitySensorFragment2 newInstance() {

        Bundle args = new Bundle();

        GravitySensorFragment2 fragment = new GravitySensorFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(myAccelerometerListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    /*
    * SensorEventListener接口的实现，需要实现两个方法
    * 方法1 onSensorChanged 当数据变化的时候被触发调用
    * 方法2 onAccuracyChanged 当获得数据的精度发生变化的时候被调用，比如突然无法获得数据时
    * */
    final SensorEventListener myAccelerometerListener = new SensorEventListener(){

        //复写onSensorChanged方法
        public void onSensorChanged(SensorEvent event){
            if (event.sensor == null) {
                return;
            }

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                int x = (int) event.values[0];
                int y = (int) event.values[1];
                int z = (int) event.values[2];
                mCalendar = Calendar.getInstance();
                long stamp = mCalendar.getTimeInMillis() / 1000l;// 1393844912

                textviewX.setText(String.valueOf(x));
                textviewY.setText(String.valueOf(y));
                textviewZ.setText(String.valueOf(z));

                int second = mCalendar.get(Calendar.SECOND);// 53

                int px = Math.abs(mX - x);
                int py = Math.abs(mY - y);
                int pz = Math.abs(mZ - z);
                Log.d(TAG, "pX:" + px + "  pY:" + py + "  pZ:" + pz + "    stamp:"
                        + stamp + "  second:" + second);
                int maxvalue = getMaxValue(px, py, pz);
                if (maxvalue > 2 && (stamp - lasttimestamp) > 30) {
                    lasttimestamp = stamp;
                    Log.d(TAG, " sensor isMoveorchanged....");
                    tv.setText("检测手机在移动..");
                }

                mX = x;
                mY = y;
                mZ = z;

            }
        }
        //复写onAccuracyChanged方法
        public void onAccuracyChanged(Sensor sensor , int accuracy){
            Log.i(TAG, "onAccuracyChanged");
        }
    };

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

    public void onPause(){
        /*
         * 很关键的部分：注意，说明文档中提到，即使activity不可见的时候，感应器依然会继续的工作，测试的时候可以发现，没有正常的刷新频率
         * 也会非常高，所以一定要在onPause方法中关闭触发器，否则讲耗费用户大量电量，很不负责。
         * */
        mSensorManager.unregisterListener(myAccelerometerListener);
        super.onPause();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gyroscope_sensor;
    }



    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

    }
}
