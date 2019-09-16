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

    TextView textView;
    private SensorManager sensorManager;
    Context ctx;
    TextView content;
    //Button share;
    String ver;
    String output = "";

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
        //share   = (Button) findViewById(R.id.share);
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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position==1) {
            content.setText(" ");
        }
        else {
            content.setText(output);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
