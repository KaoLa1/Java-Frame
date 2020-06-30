package com.example.demo.app;

import com.muye.kl.demo.utils.UuidUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestApp {
    public static void main(String[] args) {
        List<Map<String,String>> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String randomUUID = UuidUtil.getRandomUUID();
            System.out.println(randomUUID);
        }
    }

    public void db(){
        Integer[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        String[] b = {};
    }
}
