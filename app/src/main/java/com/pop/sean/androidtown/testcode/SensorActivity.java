package com.pop.sean.androidtown.testcode;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by stanzhao on 3/1/16.
 */
public class SensorActivity extends Activity implements SensorEventListener {

    private Sensor accelSensor = null, compassSensor = null, orientSensor = null;
    private float[] accelValues = new float[3], compassValues = new float[3],orientValues = new float[3];
    private boolean ready = false; //检查传感器是否正常工作，即是否同时具有加速传感器和磁场传感器。
    private float[] inR = new float[9];
    private float[] inclineMatrix = new float[9];
    private float[] prefValues = new float[3];
    private double mInclination;
    private int count = 1;
    TextView nowOne;
    TextView oldOne;
    SensorManager sm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oldOne =  new TextView(this);
        nowOne =  new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(lp);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(nowOne);
        ll.addView(oldOne);
        setContentView(ll);

        sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        accelSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        compassSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientSensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, accelSensor,SensorManager.SENSOR_DELAY_GAME);
        sm.registerListener(this, compassSensor,SensorManager.SENSOR_DELAY_GAME);
        sm.registerListener(this, orientSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        //【1】将相关传感器的数值分别读入accelValues，compassValues（磁力感应器的数值）和orientValues数组中
        switch(event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                for(int i = 0 ; i < 3 ; i ++){
                    accelValues[i] = event.values[i];
                }
                if(compassValues[0] != 0) //如果accelerator和magnetic传感器都有数值，设置为真
                    ready = true;
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                for(int i = 0 ; i < 3 ; i ++){
                    compassValues[i] = event.values[i];
                }
                if(accelValues[2] != 0) //检查accelerator和magnetic传感器都有数值，只是换一个轴向检查
                    ready = true;
                break;

            case Sensor.TYPE_ORIENTATION:
                for(int i = 0 ; i < 3 ; i ++){
                    orientValues[i] = event.values[i];
                }
                break;
        }

        if(!ready)
            return;

        //【2】根据加速传感器的数值accelValues[3]和磁力感应器的数值compassValues[3]，进行矩阵计算，获得方位
        //【2.1】计算rotation matrix R(inR)和inclination matrix I(inclineMatrix)
        if(SensorManager.getRotationMatrix(inR, inclineMatrix, accelValues, compassValues)){
            /* 【2.2】根据rotation matrix计算设备的方位。，范围数组：
            values[0]: azimuth, rotation around the Z axis.
            values[1]: pitch, rotation around the X axis.
            values[2]: roll, rotation around the Y axis.*/
            SensorManager.getOrientation(inR, prefValues);
            //【2.2】根据inclination matrix计算磁仰角，地球表面任一点的地磁场总强度的矢量方向与水平面的夹角。
            mInclination = SensorManager.getInclination(inclineMatrix);

            //【3】显示测量值
            if(count++ % 100 == 0){
                doUpdate(null);
                count = 1;
            }

        }else{
            Toast.makeText(this, "无法获得矩阵（SensorManager.getRotationMatrix）", Toast.LENGTH_LONG);
            finish();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //【3】显示测量值
    public void doUpdate(View v){
        if(!ready)
            return;
        //preValues[0]是方位角，单位是弧度，范围是-pi到pi，通过Math.toDegrees()转换为角度
        float mAzimuth = (float)Math.toDegrees(prefValues[0]);
        /*//纠正为orientation的数值。
         * if(mAzimuth < 0)
            mAzimuth += 360.0;*/

        String msg = String.format("推荐方式：\n方位角：%7.3f\npitch: %7.3f\nroll: %7.3f\n地磁仰角：%7.3f\n",
                mAzimuth,Math.toDegrees(prefValues[1]),Math.toDegrees(prefValues[2]),
                Math.toDegrees(mInclination));
        nowOne.setText(msg);

        msg = String.format("老方式：\n方位角：%7.3f\npitch: %7.3f\nroll: %7.3f",
                orientValues[0],orientValues[1],orientValues[2]);
        oldOne.setText(msg);
    }

}
