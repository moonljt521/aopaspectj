package com.moon.aopsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import yundonghui.aop.library.trace.NoDoubleClickTrace;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    private static final String TAG = "AOPSamples" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.testAopBtn);
        button.setOnClickListener(this);




    }


    @NoDoubleClickTrace
    @Override
    public void onClick(View view) {

        Log.i(TAG,"click.........");

    }
}
