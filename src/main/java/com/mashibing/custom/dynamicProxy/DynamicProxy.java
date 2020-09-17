package com.mashibing.custom.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
*<p>功能描述：动态代理</p>
*<ul>
*<li>@param </li>
*<li>@return </li>
*<li>@throws </li>
*<li>@author My</li>
*<li>@date 2020/9/13 21:55</li>
*</ul>
*/
public class DynamicProxy implements InvocationHandler {

    private Object object;

    public DynamicProxy(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before proxy=====");
        method.invoke(object, args);
        System.out.println("after proxy=====");
        return null;
    }


    public static void main(String[] args) {
        HelloInChinese helloInChinese = new HelloInChinese();
        InvocationHandler handler = new DynamicProxy(helloInChinese);
        HelloInterface helloInterface = (HelloInterface) Proxy.newProxyInstance(helloInChinese.getClass().getClassLoader(), helloInChinese.getClass().getInterfaces(), handler);
        helloInterface.sayHello();
    }
}
