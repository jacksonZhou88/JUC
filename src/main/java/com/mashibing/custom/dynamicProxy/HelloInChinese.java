package com.mashibing.custom.dynamicProxy;

public class HelloInChinese implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("hello in Chinese!");
    }
}
