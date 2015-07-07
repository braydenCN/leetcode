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
}
