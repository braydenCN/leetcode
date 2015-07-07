package com.brayden;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaSum = area(A, B, C, D) + area(E, F, G, H);
        if(!overlapped(A, B, C, D, E, F, G, H))
            return areaSum;
        
        return areaSum - area(max(A, E), max(B, F), min(C, G), min(D, H));
    }

    private int min(int x, int y){
        return Math.min(x, y);
    }
    
    private int max(int x, int y){
        return Math.max(x, y);
    }
    
    private int area(int a, int b, int c, int d) {
        return (c - a) * (d - b);
    }

    private boolean overlapped(int a, int b, int c, int d, int e, int f, int g, int h) {
        return !(b >= h || f >= d || c <= e || g <= a);
    }
}
