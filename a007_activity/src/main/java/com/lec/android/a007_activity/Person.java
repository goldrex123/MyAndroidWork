package com.lec.android.a007_activity;

import java.io.Serializable;

// Intent 담아 보내는 객체는 반드시 Serializable 되어 있어야 한다
public class Person implements Serializable {
    String name;
    int age;

    // 알트 + 인설트 로 자동생성
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
