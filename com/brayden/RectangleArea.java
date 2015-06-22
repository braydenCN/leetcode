package com.brayden;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if(overlapped(A, B, C, D, E, F, G, H))
            return area(A, B, C, D) + area(E, F, G, H);
        
    }

    private int area(int a, int b, int c, int d) {
        return (c - a) * (d - b);
    }

    private boolean overlapped(int a, int b, int c, int d, int e, int f, int g, int h) {
        return !(b >= h || f >= d || c <= e || g <= a);
    }
}
