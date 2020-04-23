package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/* HTTP 요청하기
   - 메니페스트 설정 하기 : android.permission.INTERNET 권한
   - <application> 에 추가 usesCleartextTraffic="true"
       HTTP와 같은 cleartext 네트워크 트래픽을 사용할지 여부를 나타내는 flag로
       이 플래그가 flase 로 되어 있으면, 플랫폼 구성 요소 (예 : HTTP 및 FTP 스택, DownloadManager, MediaPlayer)는
       일반 텍스트 트래픽 사용에 대한 앱의 요청을 거부하게 됩니다. 이 flag를 설정하게 되면 모든 cleartext 트래픽은 허용처리가 됩니다

   - URL 객체 만들기 -> HttpURLConnection 객체 만들기
       setXXX() 메소르도 Conneciton 세팅
           ex) setRequestMethod(method) :  "GET" "POST " 등의 문자열
           ex) setRequestProperty(field, value) :

   - request 는 별도의 Thread 로 진행!
   - 위 Thread에서 화면 UI 접근하려면 (당연히) Handler 사용
*/

public class MainActivity extends AppCompatActivity {

    EditText etUrl;
    TextView tvResult;
    Button btnRequest, btnClear;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = findViewById(R.id.etUrl);
        tvResult = findViewById(R.id.tvResult);

        btnRequest = findViewById(R.id.btnWebView);
        btnClear = findViewById(R.id.btnBrowser);


    }
}
