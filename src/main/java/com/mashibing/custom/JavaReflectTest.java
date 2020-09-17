package com.mashibing.custom;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JavaReflectTest {

    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        UserService userService = new UserService();
        //第一种注入方式
        Field field = userController.getClass().getDeclaredField("userService");
        // field.setAccessible(true);
        // field.set(userController, userService);
        // System.out.println(userController.getUserService());

        //第二种注入方式
        String setMethodName = "set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
        Method method = userController.getClass().getMethod(setMethodName, userService.getClass());
        method.invoke(userController, userService);
        System.out.println(userController.getUserService());

    }
}
