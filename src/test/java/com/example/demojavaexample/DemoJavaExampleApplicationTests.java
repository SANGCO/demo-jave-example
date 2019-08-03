package com.example.demojavaexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJavaExampleApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void integer_test() {

        int a1 = 10;
        int a2 = 10;
        Integer a3 = 10;
        Integer a4 = 10;
        Integer a5 = new Integer(10);
        Integer a6 = new Integer(10);
        Integer a7 = Integer.valueOf(10);
        Integer a8 = Integer.valueOf(10);

        System.out.println(a1 == a2); // true
        System.out.println(a3 == a4); // true
        System.out.println(a5 == a6); // false
        System.out.println(a7 == a8); // true
        System.out.println();

        System.out.println(a1 == a3); // true
        System.out.println(a1 == a5); // true
        System.out.println(a1 == a7); // true
        System.out.println();

        System.out.println(a3 == a5); // false
        System.out.println(a3 == a7); // true
        System.out.println();

        System.out.println(a5 == a7); // flase
        System.out.println();

        int b1 = 300;
        int b2 = 300;
        Integer b3 = 300;
        Integer b4 = 300;
        Integer b5 = new Integer(300);
        Integer b6 = new Integer(300);
        Integer b7 = Integer.valueOf(300);
        Integer b8 = Integer.valueOf(300);

        System.out.println(b1 == b2); // true
        System.out.println(b3 == b4); // false
        System.out.println(b5 == b6); // false
        System.out.println(b7 == b8); // false
        System.out.println();

        System.out.println(b1 == b3); // true
        System.out.println(b1 == b5); // true
        System.out.println(b1 == b7); // true
        System.out.println();

        System.out.println(b3 == b5); // false
        System.out.println(b3 == b7); // false
        System.out.println();

        System.out.println(b5 == b7); // flase
        System.out.println();

        int max = Integer.MAX_VALUE;  // 2147483647
        int max1 = 2147483647;
        Integer max2 = 2147483647;
        Integer max3 = new Integer(2147483647);
        Integer max4 = Integer.valueOf(2147483647);

        System.out.println(max1 == max2); // true
        System.out.println(max1 == max3); // true
        System.out.println(max1 == max4); // true
        System.out.println();

        System.out.println(max2 == max3); // false
        System.out.println(max2 == max4); // false
        System.out.println();

        System.out.println(max3 == max4); // flase
        System.out.println();

    }

}
