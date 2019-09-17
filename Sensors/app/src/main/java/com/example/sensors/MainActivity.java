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
    private TextView desc;
    private SensorManager sensorManager;
    private Context ctx;
    private TextView content;
    private String ver;
    private String output = "";
    private Sensor sensor;
    private int type = 0;
    private int pos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);
        desc = (TextView) findViewById(R.id.desc);


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

        desc.setText("MANUFACTURE: "+android.os.Build.MANUFACTURER + "\n" +
                "DEVICE: "+ android.os.Build.DEVICE       + "\n" +
                "MODEL: "+ android.os.Build.MODEL        + "\n" +
                "PRODUCT: "+android.os.Build.PRODUCT      + "\n" +
                "VERSION: "+ver+"\n");

        output = "AVAILABLE SENSORS IN DEVICE: \n";
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
        //sensor = sensorManager.getDefaultSensor(type);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        pos = position;

        if (position == 1) {
            //Accelerometer
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }

            textView.setText("POS: " + pos);
            type = Sensor.TYPE_ACCELEROMETER;
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);



        } else if (position == 2) {
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            //Linear Accelerometer
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_LINEAR_ACCELERATION;
            sensor = sensorManager.getDefaultSensor(type);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);


        } else if (position == 3) {
            //Gravity Sensor
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_GRAVITY;
            sensor = sensorManager.getDefaultSensor(type);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);


        } else if (position == 4) {
            //Gyroscope
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_GYROSCOPE;
            sensor = sensorManager.getDefaultSensor(type);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);


        } else if (position == 5) {
            //Ambient Light Sensor
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_LIGHT;
            sensor = sensorManager.getDefaultSensor(type);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);


        } else if (position == 6) {
            //Ambient Magnetic Field Sensor
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_MAGNETIC_FIELD;
            sensor = sensorManager.getDefaultSensor(type);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);


        } else if (position == 7) {
            //Proximity Sensor
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_PROXIMITY;
            sensor = sensorManager.getDefaultSensor(type);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);

        } else if (position == 8) {
            //Pressure Sensor
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_PRESSURE;
            sensor = sensorManager.getDefaultSensor(type);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);;

        } else if (position == 9) {
            //Ambient Temperature Sensor
            if(sensor !=null) {
                sensorManager.unregisterListener(this);
            }
            textView.setText("POS: " + pos);
            type = Sensor.TYPE_AMBIENT_TEMPERATURE;
            sensor = sensorManager.getDefaultSensor(type);
            if(sensor !=null) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
            } else {
                content.setText("SENSOR IS UNAVAILABLE");
            }



        } else {
            //All
            sensorManager.unregisterListener(this);
            textView.setText("POS: " + pos);
            //sensor = null;
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
           float x = event.values[0];
           float y = event.values[1];
           float z = event.values[2];
           String max = "";
           String min = "";

           if(x > y && x > z) {
               max = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y > z && y > x) {
               max = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z > y && z > x) {
               max = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }

           if(x < y && x < z) {
               min = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y < z && y < x) {
               min = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z < y && z < x) {
               min = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }

           //System.out.println("TYPE: "+type);

           content.setText("X: " + x + " m/s^2"+"\n"+
                   "Y: " + y + " m/s^2"+"\n"+
                   "Z: " + z + " m/s^2"+"\n"+
                   "MAX: " + max  + "\n"+
                   "MIN: " + min);

        } else if (pos == 2) {
            //Linear Accelerometer
           float x = event.values[0];
           float y = event.values[1];
           float z = event.values[2];
           String max = "";
           String min = "";

           if(x > y && x > z) {
               max = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y > z && y > x) {
               max = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z > y && z > x) {
               max = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }

           if(x < y && x < z) {
               min = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y < z && y < x) {
               min = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z < y && z < x) {
               min = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }


           //System.out.println("TYPE: "+type);


           content.setText("X: " + x + " m/s^2"+"\n"+
                   "Y: " + y + " m/s^2"+"\n"+
                   "Z: " + z + " m/s^2"+"\n"+
                   "MAX: " + max  + "\n"+
                   "MIN: " + min);


        } else if (pos == 3) {
            //Gravity Sensor
           float x = event.values[0];
           float y = event.values[1];
           float z = event.values[2];
           String max = "";
           String min = "";

           if(x > y && x > z) {
               max = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y > z && y > x) {
               max = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z > y && z > x) {
               max = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }

           if(x < y && x < z) {
               min = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y < z && y < x) {
               min = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z < y && z < x) {
               min = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }

           content.setText("X: " + x + " m/s^2"+"\n"+
                   "Y: " + y + " m/s^2"+"\n"+
                   "Z: " + z + " m/s^2"+"\n"+
                   "MAX: " + max  + "\n"+
                   "MIN: " + min);

       } else if (pos == 2) {
           //Gyroscope
           float x = event.values[0];
           float y = event.values[1];
           float z = event.values[2];
           String max = "";
           String min = "";

           if(x > y && x > z) {
               max = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y > z && y > x) {
               max = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z > y && z > x) {
               max = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }

           if(x < y && x < z) {
               min = String.valueOf(x) +" m/s^2"+ " (X-axis)";
           } else if(y < z && y < x) {
               min = String.valueOf(y) +" m/s^2"+ " (Y-axis)";
           } else if(z < y && z < x) {
               min = String.valueOf(z) +" m/s^2"+ " (Z-axis)";
           }


           //   System.out.println("TYPE: "+type);

           content.setText("X: " + x + " m/s^2"+"\n"+
                   "Y: " + y + " m/s^2"+"\n"+
                   "Z: " + z + " m/s^2"+"\n"+
                   "MAX: " + max  + "\n"+
                   "MIN: " + min);


        } else if (pos == 4) {
            //Gyroscope

           float x = event.values[0];
           float y = event.values[1];
           float z = event.values[2];
           String max = "";
           String min = "";

           if(x > y && x > z) {
               max = String.valueOf(x) +" rad/s"+ " (X-axis)";
           } else if(y > z && y > x) {
               max = String.valueOf(y) +" rad/s"+ " (Y-axis)";
           } else if(z > y && z > x) {
               max = String.valueOf(z) +" rad/s"+ " (Z-axis)";
           }

           if(x < y && x < z) {
               min = String.valueOf(x) +" rad/s"+ " (X-axis)";
           } else if(y < z && y < x) {
               min = String.valueOf(y) +" rad/s"+ " (Y-axis)";
           } else if(z < y && z < x) {
               min = String.valueOf(z) +" rad/s"+ " (Z-axis)";
           }

           System.out.println("TYPE: "+type);



           content.setText("X: " + x + " rad/s"+"\n"+
                   "Y: " + y + " rad/s"+"\n"+
                   "Z: " + z + " rad/s"+"\n"+
                   "MAX: " + max  + "\n"+
                   "MIN: " + min);



        } else if (pos == 5) {
            //Ambient Light Sensor
           float lux = event.values[0];

           content.setText("LUX: " + lux);



        } else if (pos == 6) {
            //Ambient Magnetic Field Sensor



        } else if (pos == 7) {
            //Proximity Sensor
           float cm = event.values[0];

           content.setText("cm: " + cm);


        } else if (pos == 8) {
           //Pressure Sensor
           float hPa = event.values[0];

           content.setText("hPa: " + hPa);


        } else if (pos == 9) {
            //Ambient Temperature Sensor
           float C = event.values[0];

           content.setText("Deg: "+C);


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

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}
