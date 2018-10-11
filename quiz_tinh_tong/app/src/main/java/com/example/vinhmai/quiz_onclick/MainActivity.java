package com.example.vinhmai.quiz_onclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSum;
    private TextView soA,soB,tvKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soA = findViewById(R.id.soA);
        soB = findViewById(R.id.soB);
        tvKQ = findViewById(R.id.tvKQ);
        btnSum = findViewById(R.id.btnSum);
        btnSum.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        int a = Integer.parseInt(soA.getText().toString()); //dổi kiểu dữ liệu từ string sang
        int b = Integer.parseInt(soB.getText().toString());//doi kieu du lieu tu string sang
        int sum ;

        sum = a+b;
        tvKQ.setText(" " + sum); //ep kiểu từ int sang int

    }
}
