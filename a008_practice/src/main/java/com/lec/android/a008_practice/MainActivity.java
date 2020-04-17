package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AddressbookAdapter adapter; //Adapter 객체
    RecyclerView rv;
    EditText etName, etAge, etAddr;
    Addressbook addr;
    int age;
    String name, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);

        //RecyclerView 를 사용하기 위해서는 LayoutManager 지정해주어야 한다.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);


        // 어탭터 객체 생성
        adapter = new AddressbookAdapter();

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAddr = findViewById(R.id.etAddr);

//        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                addr.setName(etName.getText().toString().trim());
//
//                return false;
//            }
//        });
//
//        etAge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                addr.setAge(etAge.getText().toString());
//                return false;
//            }
//        });
//
//        etAddr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                addr.setAddr(etAddr.getText().toString());
//                return false;
//            }
//        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(new Addressbook(etName.getText().toString().trim(), etAge.getText().toString().trim(),
                        etAddr.getText().toString()));
                adapter.notifyDataSetChanged();

            }
        });
//        initAdapter(adapter);

        rv.setAdapter(adapter); // RecyclerView 에 어탭터 장착!

    } //onCreate

    // 샘플 데이터 가져오기
//    protected void initAdapter(AddressbookAdapter adapter){
//        //몇개만 생성
//        for(int i = 0 ; i < 10 ; i++){
//            int idx = D.next();
//            adapter.addItem(new Addressbook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx], D.EMAIL[idx]));
//        }
//    }
} //MainActivity