package com.example.numberpicker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    int PM1 ,PM2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the widgets reference from XML layout
        final TextView tv = (TextView) findViewById(R.id.tv);
        NumberPicker np = (NumberPicker) findViewById(R.id.np);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);
        NumberPicker np2 = (NumberPicker) findViewById(R.id.np2);

        final TextView tv3 = (TextView) findViewById(R.id.tv3);
        //Set TextView text color
        tv.setTextColor(Color.parseColor("#FF2C834F"));
        tv2.setTextColor(Color.parseColor("#FF2C834F"));

        //Initializing a new string array with elements
        final String[] values= {"151","157","163","167","173","179","181",
                "191","193","197","199","211","223","227","229","233","239","241","251",
                "257","263","269","271","277","281","283","293","307","311","313","317","331","337",
                "347","349","353","359","367","373","379","383","389","397","401","409","419","421",
                "431","433","439","443","449","457","461","463","467","479","487","491","499","503",
                "509","521","523","541","547","557","563","569","571","577","587","593","599","601",
                "607","613","617","619","631","641","643","647","653","659","661","673","677","683","691",
                "701","709","719","727","733","739","743","751","757","761","769","773","787","797","809",
                "811","821","823","827","829","839","853","857","859","863","877","881","883","887","907",
                "911","919","929","937","941","947","953","967","971","977"
        };
        PM1  =0;
        PM2 = 0;
        //Populate NumberPicker values from String array values
        //Set the minimum value of NumberPicker
        np.setMinValue(0); //from array first value
        np2.setMinValue(0); //from array first value
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(values.length-1); //to array last value
        np2.setMaxValue(values.length-1); //to array last value

        //Specify the NumberPicker data source as array elements
        np.setDisplayedValues(values);
        np2.setDisplayedValues(values);
        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);
        np2.setWrapSelectorWheel(true);

        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected value from picker
                PM1=Integer.valueOf(values[newVal]);
                tv.setText("Selected Fisrst Prime number  " + values[newVal]);
                if(PM1!=0 && PM2!=0){
                    tv3.setText("Your RSA Key is" + PM1 * PM2);
                }
            }
        });

        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected value from picker
                PM2=Integer.valueOf(values[newVal]);
                tv2.setText("Selected Second Prime number : " + values[newVal]);
                if(PM1!=0 && PM2!=0){
                    tv3.setText("Your RSA Key is : " + PM1 * PM2 +"\n Now let Hack!!!");
                }
            }
        });

    }
}