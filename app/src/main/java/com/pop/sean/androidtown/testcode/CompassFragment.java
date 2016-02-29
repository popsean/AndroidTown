package com.pop.sean.androidtown.testcode;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.fragment.BaseFragment;

import butterknife.Bind;

/**
 * Created by sean on 2/29/16.
 */
public class CompassFragment extends BaseFragment implements SensorEventListener {

    @Bind(R.id.compass_imageView)
    ImageView imageView;
    private float currentDegree = 0f;
    SensorManager sm;
    Sensor sensor;


    @Override
    protected int getLayoutId() {
        return R.layout.compass_view;
    }

    public static CompassFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CompassFragment fragment = new CompassFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //传感器报告新的值(方向改变)
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float degree = event.values[0];

            /*
            RotateAnimation类：旋转变化动画类

            参数说明:

            fromDegrees：旋转的开始角度。
            toDegrees：旋转的结束角度。
            pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            pivotXValue：X坐标的伸缩值。
            pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            pivotYValue：Y坐标的伸缩值
            */
            RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            //旋转过程持续时间
            ra.setDuration(200);
            //罗盘图片使用旋转动画
            imageView.startAnimation(ra);

            currentDegree = -degree;
        }
    }
    //传感器精度的改变
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void initView() {

        // 传感器管理器
        sm = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        // 注册传感器(Sensor.TYPE_ORIENTATION(方向传感器);SENSOR_DELAY_FASTEST(0毫秒延迟);
        // SENSOR_DELAY_GAME(20,000毫秒延迟)、SENSOR_DELAY_UI(60,000毫秒延迟))
        sensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }

    public void onPause() {
// TODO Auto-generated method stub
        super.onPause();
        sm.unregisterListener(this); // 解除监听器注册
    }

    @Override
    public void onResume() {
// TODO Auto-generated method stub
        super.onResume();
        sm.registerListener((SensorEventListener) this, sensor,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void initData() {

    }
}
