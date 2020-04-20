package com.lec.android.a011_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


// Value 1
// 1 ~ 10 까지 1초 단위로 증가시키기
// 10초에 도달하면 멈추어서 Toast 띄우기
// Message 사용

// Value 2
// 1 ~ 20 까지 1초 단위로 증가시키기
// 20초에 도달하면 멈추어서 Toast 띄우기
// Handler

public class Main4Activity extends AppCompatActivity {
    int num1 = 0;
    int num2 = 0;
    int num3 = 0;
    int num4 = 0;
    int num5 = 0;

    TextView tvResult1, tvResult2, tvResult3, tvResult4, tvResult5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);
        tvResult3 = findViewById(R.id.tvResult3);
        tvResult4 = findViewById(R.id.tvResult4);
        tvResult5 = findViewById(R.id.tvResult5);

        Thread1 thread1 = new Thread1();
        thread1.setDaemon(true);
        thread1.start();

        Thread2 thread2 = new Thread2();
        thread2.setDaemon(true);
        thread2.start();

        Thread3 thread3 = new Thread3();
        thread3.setDaemon(true);
        thread3.start();

        // 방법 #3
        // 핸들러를 사용하지 않고도 일정시간마다 (혹은 후에) 코스를 수행할수 있도록
        // CountDownTimer 클래스가 제공된다.
        // '총시간'  과 '인터벌(간격)' 을 주면 매 간격마다 onTick 메소드를 수행한다.
        new CountDownTimer(15 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) { // 매 간격마다 수행하는 코드
                num4++;
                tvResult4.setText("num4 = " + num4);
            }

            @Override
            public void onFinish() { // 종료시 수행하는 코드
                Toast.makeText(getApplicationContext(), "num4 종료!", Toast.LENGTH_LONG).show();
            }
        }.start(); // 타이머 시작

    }


    class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {
                num1++;
                handler1.sendEmptyMessage(1);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (num1 == 10)
                    break;
            }
        }
    }

    class Thread2 extends Thread {
        @Override
        public void run() {
            while(true){
                num2++;
                handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult2.setText("결과창2 : "+ num2);
                        if(num2 == 20) Toast.makeText(getApplicationContext(), "20까지 도달!", Toast.LENGTH_LONG).show();
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(num2 == 20) {
                    break;
                }
            }
        }
    }
    class Thread3 extends Thread {
        @Override
        public void run() {
            while (true) {
                num3++;
                num5++;
                handler3.sendEmptyMessage(2);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                tvResult1.setText("결과창1 : " + num1);
                if(num1 == 10) Toast.makeText(getApplicationContext(), "10까지 도달!", Toast.LENGTH_LONG).show();
            }
        }
    };

    Handler handler2 = new Handler();

    Handler handler3 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 2){
                tvResult3.setText("결과창3 : " + num3);
                tvResult5.setText("결과창5 : " + num5);
            }
        }
    };
}

