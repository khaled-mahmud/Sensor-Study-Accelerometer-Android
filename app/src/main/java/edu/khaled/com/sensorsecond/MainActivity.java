package edu.khaled.com.sensorsecond;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener, View.OnTouchListener{

    private  float mLastX, mLastY, mLastZ;

    private float deltaX, deltaY, deltaZ;

    private Button time_spent = null;

    private boolean mInitialized;

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;

    private  final float NOISE = (float) 2.0f;

    private  float calculatedDistance = 0;

    private long startTime = 0;

    private long endTime = 0;

    private long deltaTime = 0;

    TextView tvX, tvY, tvZ, distance, _time, _acceleration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitialized = false;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        time_spent = findViewById(R.id.time_spent);

        time_spent.setOnTouchListener(this);

        tvX = findViewById(R.id.x_axis);
        tvY = findViewById(R.id.y_axis);
        tvZ = findViewById(R.id.z_axis);

        distance = findViewById(R.id.distance);
        _time = findViewById(R.id.time);
        _acceleration = findViewById(R.id.acceleration);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        tvX.setText(x + "");
        tvY.setText(y + "");
        tvZ.setText(z + "");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            mSensorManager.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
        return false;
    }
}
