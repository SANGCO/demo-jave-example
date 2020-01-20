package com.example.demojavaexample.effectiveJava.item26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example {

    public static void main(String[] args) {

        // 로 타입(타입 매개변수가 없는 제네릭 타입)
        List a = new ArrayList();
        a.add(new String());
        a.add(Long.class);
        a.add(Integer.class);

        Class<String> c = String.class;

//        List<String>[] stringList = new List<String>[1]; // (1)
//        List<Integer> inList = Arrays.asList(42);        // (2)
//        Object[] objects = stringList;                   // (3)
//        objects[0] = inList;                             // (4)
//        String s = stringList[0].get(0);                 // (5)


    }

//    public <T> T[] toArray(T[] a) {
//        if (a.length < size) {
//            @SuppressWarnings("unchecked") T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
//            return result;
//        }
//        System.arraycopy(elements, 0, a, 0, size);
//        if (a.length > size) a[size] = null;
//        return a;
//    }

}
