package com.mashibing.juc.c_021_03_VarHandle;

import java.lang.invoke.MethodHandles;

public class T01_HelloVarHandle {

    int x = 8;

    // private static VarHandle handle;

    static {
        System.out.println();
        // handle = MethodHandles.lookup().findVarHandle(T01_HelloVarHandle.class, "x", int.class);
    }

    public static void main(String[] args) {
        T01_HelloVarHandle t = new T01_HelloVarHandle();

        //plain read / write
        // System.out.println((int)handle.get(t));
        // handle.set(t,9);
        // System.out.println(t.x);

        // handle.compareAndSet(t, 9, 10);
        // System.out.println(t.x);
        //
        // handle.getAndAdd(t, 10);
        // System.out.println(t.x);
    //
    }
}
