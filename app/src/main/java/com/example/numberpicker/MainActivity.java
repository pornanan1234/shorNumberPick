package com.example.numberpicker;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.graphics.Color;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.NumberPicker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    int PM1 ,PM2,rsa,q,r,k,p, newQ,key1,key2;
    Log log;
//    BigInteger pPlus ,pMinus,key1,key2,RSA;
    List<Integer> answer = new ArrayList<Integer>();
    //Get the widgets reference from XML layout
    TextView tv ,tv2,tv3,N,mod1,mod2,mod3,mod4,mod5,mod6,q1,q2,q3,q5,q6,rLast,rSecondToLast,q4,r4;
    TextView shorStep2,shorStep3,shorStep3_1,shorStep3_2, shorStep3_2_1,shorStep3_3,shorStep4,rsummarytext;
    String  defaultTextshor3_2_1,defaultTextRsummaryText,defaultTextShor4,defaultTextShor5,defaultTextShor6;
    TextView shorStep5,shorStep6,summaryText;
    ImageView step3image;

    NumberPicker np, np2;

    EditText K ;
    TableLayout tablelayout;
    final String[]  values= { "13" ,"17" ,"19","23","29","31","37","41","43","47"
            ,"53","59","61","67","71","73","79","83","89","97","101","103","107"
    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        loadObject();
        hideAll();
        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected value from picker
                hideAll();
                PM1=Integer.valueOf(values[newVal]);
                tv.setText("Selected Fisrst Prime number  " + values[newVal]);
                if(PM1!=0 && PM2!=0){
                    rsa= PM1 * PM2;
                    tv3.setText("RSA Key is generated : " + rsa);
                    N.setText( "\n\n        "+String.valueOf(rsa));
                    shorStep2.setText("Step 2 \n \n Choose randomly a number(K) between 1 and "+ (rsa));

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
                    rsa= PM1 * PM2;
                    tv3.setText("YRSA Key is generated : " + rsa );
                    N.setText( "\n\n        "+String.valueOf(rsa));
                    shorStep2.setText("Step 2 \n \n Choose randomly a number(K) between 1 and "+ (rsa));

                }
                hideAll();
            }
        });

        K.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Do nothing
            }
            @Override
            public void afterTextChanged(Editable s) {
                //reset comment
                summaryText.setText("");
                hideAll();
                shorStep3_2_1.setText(defaultTextshor3_2_1);
                rsummarytext.setText(defaultTextRsummaryText);
                shorStep5.setText(defaultTextShor5);
                shorStep4.setText(defaultTextShor4);

                shorStep6.setText(defaultTextShor6);
                //check input value
                //1.) length not 0
                //2.) Value not more than RSA key
                if(s.length() > 0 && (Integer.valueOf(s.toString()) >= rsa) )  {
                    //
                    // reset K to N-1 && display comment to user
                    summaryText.setText("Your K value > N, Let me set K value to : "+ (rsa-1));
                    K.setText(String.valueOf(rsa-1));

                    //step 3 find r
                    //remove old data
                    answer.clear();
                    step3();


                }else if (s.length() != 0){
                    //step 3 find r
                    //remove old data
                    answer.clear();
                    step3();
                }else {
                    summaryText.setText("Please Enter K value");
                    hideAll();
                }

            }
        });

    }

    private void  loadObject(){

        tv = (TextView) findViewById(R.id.tv);
        np = (NumberPicker) findViewById(R.id.np);
        tv2 = (TextView) findViewById(R.id.tv2);
        np2 = (NumberPicker) findViewById(R.id.np2);
        N = (TextView) findViewById(R.id.N);
        shorStep2 = (TextView) findViewById(R.id.ShorStep2);
        shorStep3 = (TextView) findViewById(R.id.ShorStep3);
        shorStep3_1 = (TextView) findViewById(R.id.ShorStep3_1);
        shorStep3_2 = (TextView) findViewById(R.id.ShorStep3_2);
        shorStep3_2_1=(TextView)  findViewById(R.id.ShorStep3_2_1);
        defaultTextshor3_2_1=shorStep3_2_1.getText().toString();
        defaultTextshor3_2_1=context.getString(R.string.Shor4);

        shorStep3_3=(TextView)  findViewById(R.id.ShorStep3_3);
        shorStep4 = (TextView) findViewById(R.id.ShorStep4);
        defaultTextShor4=shorStep4.getText().toString();
        shorStep5 = (TextView) findViewById(R.id.ShorStep5);
        defaultTextShor5=shorStep5.getText().toString();
        shorStep6 = (TextView) findViewById(R.id.ShorStep6);
        defaultTextShor6=shorStep6.getText().toString();
        rsummarytext= (TextView) findViewById(R.id.rsummarytext);
        defaultTextRsummaryText=rsummarytext.getText().toString();

        step3image = (ImageView) findViewById(R.id.step3image);
        K = (EditText) findViewById(R.id.K);
        tv3 = (TextView) findViewById(R.id.tv3);
        mod1 =(TextView) findViewById(R.id.mod1);
        mod2 =(TextView) findViewById(R.id.mod2);
        mod3 =(TextView) findViewById(R.id.mod3);
        mod4 =(TextView) findViewById(R.id.mod4);
        mod5 =(TextView) findViewById(R.id.mod5);
        mod6 =(TextView) findViewById(R.id.mod6);
        q1 =(TextView) findViewById(R.id.q1);
        q2 =(TextView) findViewById(R.id.q2);
        q3 =(TextView) findViewById(R.id.q3);
        q4 =(TextView) findViewById(R.id.q4);
        q5 =(TextView) findViewById(R.id.q5);
        q6 =(TextView) findViewById(R.id.q6);
        summaryText =(TextView) findViewById(R.id.summaryText);
        tablelayout =(TableLayout) findViewById(R.id.tablelayout);
        r4 =(TextView) findViewById(R.id.r4);
        rLast =(TextView) findViewById(R.id.r_last);
        rSecondToLast =(TextView) findViewById(R.id.r_second_to_last);
        tv.setTextColor(Color.parseColor("#FF2C834F"));
        tv2.setTextColor(Color.parseColor("#FF2C834F"));
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
    }
    private void  hideAll(){
        // hide all layout
        // Step 3 ShorStep3,ShorStep3_2, step3image,ShorStep3_2_1,ShorStep3_3,ShorStep4,Shor3error
        // ,ShorStep5,Shor3errorN,ShorStep6
        shorStep3.setVisibility(View.GONE);
        shorStep3_1.setVisibility(View.GONE);
        shorStep3_2.setVisibility(View.GONE);
        step3image.setVisibility(View.GONE);
        shorStep3_2_1.setVisibility(View.GONE);
        shorStep3_3.setVisibility(View.GONE);
        shorStep4.setVisibility(View.GONE);
        shorStep5.setVisibility(View.GONE);
        shorStep6.setVisibility(View.GONE);
        tablelayout.setVisibility(View.GONE);
        rsummarytext.setVisibility(View.GONE);
    }

    private int gcd(int n, int k){
        BigInteger N =new BigInteger(String.valueOf(n));
        BigInteger K = new BigInteger(String.valueOf(k));
        log.d(TAG,"n "+n );
        log.d(TAG,"k "+k );
        log.d(TAG,"N "+N.intValue() );
        log.d(TAG,"K "+ K.intValue() );
        log.d(TAG,"gcd "+ N.gcd(K).intValue());


        return N.gcd(K).intValue();
    }

    // validate if K is already the prime number
    private void step3(){

        k = Integer.valueOf(K.getText().toString());
        if (gcd(rsa,k)!= 1) {

            summaryText.setText("Lucky you, but can you select new K value please");

        }else {
            shorStep3.setVisibility(View.VISIBLE);
            shorStep3_1.setVisibility(View.VISIBLE);
            shorStep3_2.setVisibility(View.VISIBLE);

            updateContent(shorStep3_2_1,"ToBeReplace1","(q"+"×"+k+") mod "+rsa +"\n\n");
            shorStep3_2_1.setVisibility(View.VISIBLE);
            shorStep3_3.setVisibility(View.VISIBLE);
            tablelayout.setVisibility(View.VISIBLE);
            step3_2();

            updateContent(rsummarytext,"ToBeReplace1","Your 'r' is:" +r);
            rsummarytext.setVisibility(View.VISIBLE);
        }

    }

    //step 4 , build r table (answer)
    private void step3_2(){
        q = 0;
        r = 1;
        answer.clear();

        q = k % rsa;
        log.d(TAG, "round: " + r + "  :" + 1 + "x" + k + " mod " + rsa + " =:" + q);
        answer.add(Integer.valueOf(q));
        while (q != 1) {
            r++;
            newQ = (q * k) % rsa;
            log.d(TAG, "round: " + r + "  :" + q + "x" + k + " mod " + rsa + " =:" + newQ);
            answer.add(Integer.valueOf(newQ));
            q = newQ;
        }
        setTable();
        if(isREvenNumber(r)){
            p = answer.get((r / 2) - 1).intValue();

            if(!isPPlusOneEqualN(p,rsa)){

                key1=gcd(p+1,rsa);
                key2=gcd(p-1,rsa);


                shorStep4.setVisibility(View.VISIBLE);
                updateContent(shorStep4,"ToBeReplace1",String.valueOf(r));

                shorStep5.setVisibility(View.VISIBLE);

                updateContent(shorStep5,"ToBeReplace1",String.valueOf(r));
                updateContent(shorStep5,"ToBeReplace2",String.valueOf(p));
                updateContent(shorStep5,"ToBeReplace3",String.valueOf(p+1));
                updateContent(shorStep5,"ToBeReplace4",String.valueOf(rsa));


                shorStep6.setVisibility(View.VISIBLE);
                updateContent(shorStep6,"ToBeReplace1",String.valueOf(p+1));
                updateContent(shorStep6,"ToBeReplace2",String.valueOf(key1));
                updateContent(shorStep6,"ToBeReplace3",String.valueOf(p-1));
                updateContent(shorStep6,"ToBeReplace4",String.valueOf(rsa));
                updateContent(shorStep6,"ToBeReplace5",String.valueOf(key2));

                summaryText.setText("Your r number:" + r +" Key1 : "+key1+" Key2: " + key2);
            }else{
                summaryText.setText("P+1 = RSA(N) , please select new K number");
                shorStep4.setText(context.getString(R.string.Shor4));
                shorStep5.setText(context.getString(R.string.Shor5));

                shorStep4.setVisibility(View.VISIBLE);
                shorStep5.setVisibility(View.VISIBLE);
            }

        }else {
            summaryText.setText("R is Odd number , please select new K number");
            shorStep4.setText(context.getString(R.string.Shor4));
            shorStep4.setVisibility(View.VISIBLE);
        }

    }

    private boolean isREvenNumber(int r){
        return r%2 == 0;
    }
    private boolean isPPlusOneEqualN(int p,int N){
        return p+1==N;
    }

    private void setTable(){
        mod1.setText("1x" + k + " mod " + rsa);
        mod2.setText(answer.get(0).toString() + "x" + k + " mod " + rsa);
        mod3.setText(answer.get(1).toString() + "x" + k + " mod " + rsa);
        mod5.setText(answer.get(answer.size() - 2) + "x" + k + " mod " + rsa);
        mod6.setText(answer.get(answer.size() - 1) + "x" + k + " mod " + rsa);
        q1.setText(answer.get(0).toString());
        q2.setText(answer.get(1).toString());
        if(answer.size()>6) {

            q3.setText(answer.get(2).toString());
        }
        q5.setText(answer.get(answer.size()-2).toString());
        q6.setText(answer.get(answer.size()-1).toString());
        rSecondToLast.setText("    ---" + (answer.size() -1 ));
        rLast.setText("    ---" + (answer.size() ));
        if(isREvenNumber(r)) {
            q4.setText(answer.get((r / 2) - 1).toString());
            r4.setText("    ..." + r / 2);

        }

    }
    private void updateContent(TextView text, String oldText,String newText){

        if(text.getText().toString().length()>0 && oldText.toString().length()>0 && newText.toString().length()>0){
            text.setText(text.getText().toString().replace(oldText,newText));
        }


    }

}