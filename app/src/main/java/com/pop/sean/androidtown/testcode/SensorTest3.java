package com.pop.sean.androidtown.testcode;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.fragment.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class SensorTest3 extends BaseFragment implements SensorEventListener {


    private SensorManager sensorManager = null;
    private Sensor gyroSensor = null;
    @Bind(R.id.vx)
    TextView vX;
    @Bind(R.id.vy)
    TextView vY;
    @Bind(R.id.vz)
    TextView vZ;
    @Bind(R.id.v)
    TextView v;
    @Bind(R.id.button)
    Button button;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp;
    private float[] angle = new float[3];

    @SuppressWarnings("deprecation")


    public static SensorTest3 newInstance() {

        Bundle args = new Bundle();

        SensorTest3 fragment = new SensorTest3();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sensor_test;
    }

    @Override
    protected void initView() {
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        gyroSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ORIENTATION);
        vX.setText("!!!!!!");
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //声明可变字符串
                StringBuffer sb = new StringBuffer();
                //获取手机全部的传感器
                List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
                //迭代输出获得上的传感器
                for (Sensor sensor : sensors) {
                    //System.out.println(sensor.getName().toString());
                    sb.append(sensor.getName().toString());
                    sb.append("\n");
                    Log.i("Sensor", sensor.getName().toString());
                }
                //给文本控件赋值
                v.setText(sb.toString());
            }
        });
    }

    @Override
    protected void initData() {

    }

    public SensorTest3() {
// TODO Auto-generated constructor stub
        angle[0] = 0;
        angle[1] = 0;
        angle[2] = 0;
        timestamp = 0;
    }

    @Override
    public void onPause() {
// TODO Auto-generated method stub
        super.onPause();
        sensorManager.unregisterListener(this); // 解除监听器注册
    }

    @Override
    public void onResume() {
// TODO Auto-generated method stub
        super.onResume();
        sensorManager.registerListener(this, gyroSensor,
                SensorManager.SENSOR_DELAY_NORMAL); //为传感器注册监听器
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
// TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
// TODO Auto-generated method stub
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            return;
        }

        if (timestamp != 0) {
            final float dT = (event.timestamp - timestamp) * NS2S;
            angle[0] += event.values[0] * dT * 100;
            angle[1] += event.values[1] * dT * 100;
            angle[2] += event.values[2] * dT * 100;
        }
        timestamp = event.timestamp;


        vX.setText("X: " + Float.toString(angle[0]));
        vY.setText("Y: " + Float.toString(angle[1]));
        vZ.setText("Z: " + Float.toString(angle[2]));

        // 方向传感器提供三个数据，分别为azimuth、pitch和roll。
        //
        // azimuth：方位，返回水平时磁北极和Y轴的夹角，范围为0°至360°。
        // 0°=北，90°=东，180°=南，270°=西。
        //
        // pitch：x轴和水平面的夹角，范围为-180°至180°。
        // 当z轴向y轴转动时，角度为正值。
        //
        // roll：y轴和水平面的夹角，由于历史原因，范围为-90°至90°。
        // 当x轴向z轴移动时，角度为正值。

        vX.setText("Orientation X: " + event.values[0]);
        vY.setText("Orientation Y: " + event.values[1]);
        vZ.setText("Orientation Z: " + event.values[2]);

    }

}
