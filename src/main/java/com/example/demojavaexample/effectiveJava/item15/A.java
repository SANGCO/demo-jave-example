package com.example.demojavaexample.effectiveJava.item15;

//import com.example.demojavaexample.effectiveJava.item14.C;

import java.util.Arrays;

public class A {

    public static final String[] strings = {"가나다", "마바사"};

    public static final String string = "가나다라마바사";

   B b;

//    C c;
// class C의 접근 제한자를 default로 변경하면 다른 패키지에서는 사용할 수 없다.

    private static class Aa {
        // private 접근 제한자면 class A 안에서만 접근 가능
    }

    static class Ab {
        // default 접근 제한자면 class A와 같은 패키지에 있는 클래스에서 접근 가능
    }

    public static void main(String[] args) {
        Arrays.stream(A.strings).forEach(System.out::println);
        Arrays.stream(A.strings).map(s -> s + "한글").forEach(System.out::println);

        System.out.println(string);
//        string = "ABCDEF"; 값을 변경 할 수 없다.
    }

}
