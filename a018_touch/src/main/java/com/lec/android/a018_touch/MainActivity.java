package com.lec.android.a018_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Button btnTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        btnTouch = findViewById(R.id.btnTouch);

        btnTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN: // 버튼을 눌렀을 때
                        tvResult.setText("ACTION_DOWN : 눌렀습니다");
                        break;
                    case MotionEvent.ACTION_MOVE: // 버튼을 누른 상태에서 움직이고 있을 때
                        tvResult.setText("ACTION MOVE : 움직이고 있습니다");
                        break;
                    case MotionEvent.ACTION_UP: //버튼에서 손을 떼었을 때
                        tvResult.setText("ACTION UP : 손을 떼었습니다");
                        break;
                }

                // 이벤트 처리를 여기서 완료하고
                // 다른 곳에 이벤트를 넘기지 않도록
                // 리턴 값을 true로 준다. (consume)
                return true;
            }
        });

    }
}
