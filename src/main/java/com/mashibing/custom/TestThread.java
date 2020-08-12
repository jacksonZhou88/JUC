package com.mashibing.custom;

public class TestThread {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
        private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println(SHUTDOWN);
    }
}
