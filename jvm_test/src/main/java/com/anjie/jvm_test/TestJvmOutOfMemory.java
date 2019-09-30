package com.anjie.jvm_test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestJvmOutOfMemory {

    // 实现，向集合中添加100万个字符串，每个字符串由100个UUID组成
    public static void main(String[] args) {
        System.out.println("start");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            String str = "";
            for (int j = 0; j < 1000; j++) {
                str += UUID.randomUUID().toString();
            }
            list.add(str);
        }
        System.out.println("ok");
    }

}
