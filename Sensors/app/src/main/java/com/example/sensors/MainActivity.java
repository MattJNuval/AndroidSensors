package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SensorEventListener {

    private TextView textView;
    private SensorManager sensorManager;
    private Context ctx;
    private TextView content;
    private String ver;
    private String output = "";
    private Sensor sensor;
    private int type = Sensor.TYPE_ACCELEROMETER;
    private int pos;


    float x =0;
    float y =0;
    float z =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sensor_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ctx     = getApplicationContext();
        content = (TextView) findViewById(R.id.content);
        ver     = new Build.VERSION().RELEASE;

        SensorManager sm          = (SensorManager)ctx.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorsList = (List) sm.getSensorList(Sensor.TYPE_ALL);
        ArrayList<Sensor> sensors = new ArrayList(sensorsList);

        // for API < 9
        Method check;
        boolean hasMinDelay = false;

        try {
            check = Sensor.class.getMethod(
                        "getMinDelay", new Class[] { String.class } );
            hasMinDelay = true;
        } catch (NoSuchMethodException nsme) {
            // API < 9, no getMinDelay method available
        }

        output = new String("MANUFACTURE: "+android.os.Build.MANUFACTURER + "\n" +
                "DEVICE: "+ android.os.Build.DEVICE       + "\n" +
                "MODEL: "+ android.os.Build.MODEL        + "\n" +
                "PRODUCT: "+android.os.Build.PRODUCT      + "\n" +
                "VERSION: "+ver+"\n");
        if(hasMinDelay == true) {
            for(Sensor s: sensors){
                output +=
                            "\nName: "       + s.getName()         +
                                    "\nMax range: "  + s.getMaximumRange() +
                                    "\nMin delay: "  + s.getMinDelay()     +
                                    "\nPower: " 	 + s.getPower()        +
                                    "\nResolution: " + s.getResolution()   +
                                    "\nVendor: "     + s.getVendor()       +
                                    "\nVersion: "    + s.getVersion()      +
                                    "\n\n";
                }
            } else {
            for(Sensor s: sensors){
                output +=
                        "\nName: "       + s.getName()         +
                                "\nMax range: "  + s.getMaximumRange() +
                                // no minDelay
                                "\nPower: " 	 + s.getPower()        +
                                "\nResolution: " + s.getResolution()   +
                                "\nVendor: "     + s.getVendor()       +
                                "\nVersion: "    + s.getVersion()      +
                                "\n\n";
            }
        }
        content.setText(output);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        pos = position;

        if (position == 1) {
            //Accelerometer

            textView.setText("POS: " + pos);
            type = Sensor.TYPE_ACCELEROMETER;
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



            content.setText("X: " + x + "\n" +
                    "Y: " + y + "\n" +
                    "Z: " + z + "\n");

        } else if (position == 2) {
            //Linear Accelerometer
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_LINEAR_ACCELERATION;
            sensor = sensorManager.getDefaultSensor(type);

        } else if (position == 3) {
            //Gravity Sensor
            textView.setText("POS: " + pos);

            type = Sensor.TYPE_GRAVITY;
            sensor = sensorManager.getDefaultSensor(type);

        } else if (position == 4) {
            //Gyroscope
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_GYROSCOPE;
            sensor = sensorManager.getDefaultSensor(type);


        } else if (position == 5) {
            //Ambient Light Sensor
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_LIGHT;
            sensor = sensorManager.getDefaultSensor(type);


        } else if (position == 6) {
            //Ambient Magnetic Field Sensor
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_MAGNETIC_FIELD;
            sensor = sensorManager.getDefaultSensor(type);


        } else if (position == 7) {
            //Proximity Sensor
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_PROXIMITY;
            sensor = sensorManager.getDefaultSensor(type);

        } else if (position == 8) {
            //Pressure Sensor
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_PRESSURE;
            sensor = sensorManager.getDefaultSensor(type);

        } else if (position == 9) {
            //Ambient Temperature Sensor
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_AMBIENT_TEMPERATURE;
            sensor = sensorManager.getDefaultSensor(type);

        } else {
            //All
            textView.setText("POS: " + pos);
            sensor = null;
            content.setText(output);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

       if (pos == 1) {
            //Accelerometer
           x = event.values[0];
           y = event.values[0];
           z = event.values[0];




        } else if (pos == 2) {
            //Linear Accelerometer


        } else if (pos == 3) {
            //Gravity Sensor


        } else if (pos == 4) {
            //Gyroscope



        } else if (pos == 5) {
            //Ambient Light Sensor


        } else if (pos == 6) {
            //Ambient Magnetic Field Sensor



        } else if (pos == 7) {
            //Proximity Sensor


        } else if (pos == 8) {
           //Pressure Sensor


        } else if (pos == 9) {
            //Ambient Temperature Sensor


        } else {
            //All
            content.setText(output);
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}
