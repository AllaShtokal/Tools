
package com.shtokal.tools.level;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;

import com.shtokal.tools.R;

public class LevelActivity extends Activity implements SensorEventListener {

    private SensorManager manager;
    private Sensor mag;
    private Sensor acc;
    private LevelView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        view = (LevelView) findViewById(R.id.levelView1);

        manager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mag = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.levelmain, menu);
        return true;
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    float[] acceleration = new float[3];
    float[] magnetism = new float[3];
    float[] rotation = new float[9];
    float[] incline = new float[9];
    float[] orientation = new float[3];

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            acceleration = event.values;
        } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetism = event.values;
        }
        SensorManager.getRotationMatrix(rotation, incline, acceleration, magnetism);
        SensorManager.getOrientation(rotation, orientation);
        view.setXY(orientation);
       view.invalidate();
    }
}
