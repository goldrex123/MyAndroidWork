package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

         import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    //계산기 앱 만들기
    EditText etResult;

    int clickNum = 0;
    int num1;
    int num2 = 1;
    int pmmd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etResult = findViewById(R.id.etCalc);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnResult = findViewById(R.id.btnResult);
        Button btnClear = findViewById(R.id.btnClear);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(etResult.getText().toString());
                etResult.setText("+");
                clickNum = 0;
                pmmd = 1;
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(etResult.getText().toString());
                etResult.setText("-");
                clickNum = 0;
                pmmd = 2;
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(etResult.getText().toString());
                etResult.setText("*");
                clickNum = 0;
                pmmd = 3;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(etResult.getText().toString());
                etResult.setText("%");
                clickNum = 0;
                pmmd = 4;
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2 = Integer.parseInt(etResult.getText().toString());


                switch (pmmd) {
                    case 1:
                        etResult.setText("" + (num1 + num2));
                        break;
                    case 2:
                        etResult.setText("" + (num1 - num2));
                        break;
                    case 3:
                        etResult.setText("" + (num1 * num2));
                        break;
                    case 4:
                        etResult.setText("" + (num1 / num2));
                        break;

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etResult.setText("0");
                clickNum = 0;
                num1 = 0;
                num2 = 0;
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "0");
                clickNum++;
                break;
            case R.id.btn1:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "1");
                clickNum++;
                break;
            case R.id.btn2:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "2");
                clickNum++;
                break;
            case R.id.btn3:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "3");
                clickNum++;
                break;
            case R.id.btn4:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "4");
                clickNum++;
                break;
            case R.id.btn5:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "5");
                clickNum++;
                break;
            case R.id.btn6:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "6");
                clickNum++;
                break;
            case R.id.btn7:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "7");
                clickNum++;
                break;
            case R.id.btb8:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "8");
                clickNum++;
                break;
            case R.id.btn9:
                if(clickNum == 0) etResult.setText("");
                etResult.setText(etResult.getText().toString() + "9");
                clickNum++;
                break;
        }
    }
}