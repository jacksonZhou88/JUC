package com.mashibing.custom.dynamicProxy;

public class HelloProxy implements HelloInterface {
    private HelloInterface hello;

    public HelloProxy(HelloInterface helloInterface){
        this.hello = helloInterface;
    }

    @Override
    public void sayHello() {
        System.out.println("===proxy start===");
        hello.sayHello();
        System.out.println("===proxy end===");
    }

    public static void main(String[] args) {
        HelloInterface hello = new HelloInChinese();

        HelloProxy proxy = new HelloProxy(hello);

        proxy.sayHello();
    }
}
