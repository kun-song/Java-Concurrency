package com.main;

/**
 * Author: Kyle Song
 * Date:   2015/5/21 + 20:23
 * Email:  satansk@hotmail.com
 */
class A {
    int a = 10;
    static boolean f = false;
}

class B extends A {}
class C extends A {}

public class testHeritence {
    public static void main(String[] args) {
        B b = new B();
        b.a = 20;
        B.f = true;

        System.out.println("B.a = " + b.a + " B.f = " + B.f);
        System.out.println("C.a = " + new C().a);
        System.out.println("““¥º");
    }
}