package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    EditText op1, op2;
    int num1, num2;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        tvResult = findViewById(R.id.tvResult);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);

        op1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            // hasFocus = true- 포커스 받은 경우 /false - 포커스 잃은 경우
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                } else {
                    //투명색
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });

        op2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            // hasFocus = true- 포커스 받은 경우 /false - 포커스 잃은 경우
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                } else {
                    //투명색
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });

        op1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            // 타이핑 완료 되었을때 호출되는 메소드
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                tvResult.setText("첫번째 숫자 입력완료");
                return false;
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(op1.getText().toString());
                num2 = Integer.parseInt(op2.getText().toString());

                tvResult.setText(String.format("%d", (num1+num2)));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(op1.getText().toString());
                num2 = Integer.parseInt(op2.getText().toString());

                tvResult.setText(String.format("%d", (num1-num2)));
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(op1.getText().toString());
                num2 = Integer.parseInt(op2.getText().toString());

                tvResult.setText(String.format("%d", (num1*num2)));
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(op1.getText().toString());
                num2 = Integer.parseInt(op2.getText().toString());

                tvResult.setText(String.format("%d", (num1/num2)));
            }
        });
    }
}
