package com.brayden.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class Test1 {
    @Test
    public void test() throws Exception {
            Connection conn = null;
            Statement st = null;
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:a.db");
                st = conn.createStatement();
                st.execute("PRAGMA journal_mode=WAL");
                st.execute("create table t1(id int)");
            } finally {
                if(st != null)
                    st.close();
                if(conn != null)
                    conn.close();
            }
    }
    
    
    @Test
    public void test1(){
        System.out.println(Float.intBitsToFloat(1));
        System.out.println(Float.floatToIntBits(5.01f));
        System.out.println((int)4617326776960669450l);
    }
    
    @Test
    public void test2(){
        System.gc();
        for(int i = 10; i < 50; i++){
            long time1 = System.nanoTime();
            long f = fib(i);
            long timeUsed = System.nanoTime() - time1;
            System.out.println("fib: " + i + ":" + f +" time: "+ timeUsed);
            System.out.println(timeUsed/f);
        }
    }


    private long fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib(N-1) + fib(N-2);
    }
    
    @Test
    public void test3(){
        long n1 = 0, n2 = 1;
        for(int i = 2; i < 60; i++){
            long n = n1 + n2;
            n1 = n2;
            n2 = n;
            System.out.println(i + ": "+ n);
            System.out.println(n * 8 / 1_000_000_000);
        }
    }
}
