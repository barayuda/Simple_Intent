package com.barayuda.simple_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sub2Activity extends AppCompatActivity {

    public static String KEY_DATA = "data";
    private String receivedData = null;
    private TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        getSupportActionBar().setTitle("Ini Sub Activity 2");

        txtData = (TextView)findViewById(R.id.txtData);
        receivedData = getIntent().getStringExtra(KEY_DATA);
        txtData.setText(receivedData);
    }
}
