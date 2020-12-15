package com.shtokal.tools.vibration;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.shtokal.tools.R;

public class VibrationMeterActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor vibrationSensor;
    TextView vibration;
    TextView vibration2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibration_meter_main);
        vibration = (TextView) findViewById(R.id.vibration);
        vibration2 = (TextView) findViewById(R.id.vibration2);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, vibrationSensor, SensorManager.SENSOR_DELAY_FASTEST);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.i("Sensor Changed", "Accuracy :" + accuracy);
        }
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float vibrationValuef = (float) Math.sqrt((x * x) + (y * y)+ (z * z));
            float vibrationValuef2 = (float) ((float) Math.sqrt((x * x) + (y * y)+ (z * z))-9.80665);

            String vibrationValue = String.format("%.2f", vibrationValuef);
            String vibrationValue2 = String.format("%.2f", vibrationValuef2);

            vibration.setText(String.valueOf(vibrationValue));
            vibration2.setText(String.valueOf(vibrationValue2));
//            TextView xValue = (TextView) findViewById(R.id.x);
//            TextView yValue = (TextView) findViewById(R.id.y);
//            TextView zValue = (TextView) findViewById(R.id.z);
//
//            xValue.setText(String.valueOf(x));
//            yValue.setText(String.valueOf(y));
//            zValue.setText(String.valueOf(z));

        }
    }
}
