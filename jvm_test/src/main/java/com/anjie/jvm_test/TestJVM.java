package com.anjie.jvm_test;

public class TestJVM {

    public static void main(String[] args) {
        String str = System.getProperty("str");
        if(str == null){
            System.out.println("itcast");
        }else{
            System.out.println(str);
        }

        System.gc();
    }
}
