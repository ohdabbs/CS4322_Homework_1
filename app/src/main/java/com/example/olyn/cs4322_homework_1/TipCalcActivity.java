/*
  Class: 	 	          CS 4322/section01
  Term:		              Fall 2017
  Name: 		          Olyn Dabbs
  Instructor:             Dr. Selena He
  Homework 1:             Tip Calculator
 */

package com.example.olyn.cs4322_homework_1;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TipCalcActivity extends AppCompatActivity {

    //View Definitions
    TextView tenTip, fifteenTip, twentyTip, tenTotal, fifteenTotal, twentyTotal, customTip,
            customPercent, customTotal;
    SeekBar custom;
    EditText bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);

        //View Assignments
        tenTip = (TextView)findViewById(R.id.tenTip);
        fifteenTip = (TextView)findViewById(R.id.fifteenTip);
        twentyTip = (TextView)findViewById(R.id.twentyTip);
        tenTotal = (TextView)findViewById(R.id.tenTotal);
        fifteenTotal = (TextView)findViewById(R.id.fifteenTotal);
        twentyTotal = (TextView)findViewById(R.id.twentyTotal);
        customPercent = (TextView)findViewById(R.id.customTipPercent);
        customTip = (TextView)findViewById(R.id.tipAmountCustom);
        customTotal = (TextView)findViewById(R.id.totalAmountCustom);
        custom = (SeekBar)findViewById(R.id.tipBar);
        bill = (EditText)findViewById(R.id.billTotalNoTip);

        customPercent.setText(String.valueOf(custom.getProgress()) + "%");

        bill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().length()==0){
                    return;
                }

                String amountS = editable.toString();
                double amount = Double.parseDouble(amountS);
                updateStandard(amount);
                updateCustom(amount);


            }
        });

        custom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                customPercent.setText(String.valueOf(custom.getProgress()) + "%");
                if(bill.getText().toString().trim().length()==0){
                    return;
                }

                String amountS = bill.getText().toString();
                double amount = Double.parseDouble(amountS);
                updateCustom(amount);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void updateStandard(double amount){
        DecimalFormat df = new DecimalFormat("#####.00");

        double ten = .1;
        double fifteen = .15;
        double twenty = .2;

        String tenTipS = df.format(ten * amount);
        String fifteenTipS = df.format(fifteen * amount);
        String twentyTipS = df.format(twenty * amount);

        String tenAmountS = df.format((1 + ten) * amount);
        String fifteenAmountS = df.format((1 + fifteen) * amount);
        String twentyAmountS = df.format((1 + twenty) * amount);

        tenTip.setText((tenTipS));
        fifteenTip.setText(fifteenTipS);
        twentyTip.setText(twentyTipS);

        tenTotal.setText(tenAmountS);
        fifteenTotal.setText(fifteenAmountS);
        twentyTotal.setText(twentyAmountS);

    }

    public void updateCustom(double amount){
        DecimalFormat df = new DecimalFormat("#####.00");
        double percent = custom.getProgress() / 100.0;

        String tip = df.format(amount * percent);
        String total = df.format((percent + 1) * amount);

        customTip.setText(tip);
        customTotal.setText(total);

    }

}
