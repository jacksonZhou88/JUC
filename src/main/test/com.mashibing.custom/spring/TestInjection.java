package spring;

import com.mashibing.custom.A;
import com.mashibing.custom.B;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestInjection {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Test
    public void test() throws Exception {
        A a = new A();
        Class clazz = a.getClass();

        B b = new B();
        Field field = clazz.getDeclaredField("b");
        field.setAccessible(true);
        String fieldName = field.getName();

        String methodName = "set" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1, fieldName.length());
        Method method = clazz.getMethod(methodName, B.class);
        method.invoke(a, b);
        System.out.println(a.getB());
    }
}
