package com.example.demojavaexample.effectiveJava.item10;

public class A {

    public A() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

//    public boolean equals(Object obj) {
//        return (this == obj);
//    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) {
        A a = new A();
        A aa = new A();

        System.out.println(a.equals(a));
        System.out.println(aa.equals(aa));
        System.out.println(a.equals(aa));
        System.out.println(aa.equals(a));

    }
}
