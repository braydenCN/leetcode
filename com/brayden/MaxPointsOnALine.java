package com.brayden;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MaxPointsOnALine extends LeetcodeCommon {


    class MyPoint extends Point {
        MyPoint(Point p) {
            super(p.x, p.y);
        }
        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }
    
    private static class Slope {
        boolean isNeg;
        int dividend, divisor;
        public Slope(boolean isNeg, int dividend, int divisor) {
            this.isNeg = isNeg;
            this.dividend = dividend;
            this.divisor = divisor;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + dividend;
            result = prime * result + divisor;
            result = prime * result + (isNeg ? 1231 : 1237);
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Slope other = (Slope) obj;
            if (dividend != other.dividend)
                return false;
            if (divisor != other.divisor)
                return false;
            if (isNeg != other.isNeg)
                return false;
            return true;
        }
        @Override
        public String toString() {
            return "Slope [isNeg=" + isNeg + ", dividend=" + dividend
                    + ", divisor=" + divisor + "]";
        }
        
    }
    
    private static class Line{
        Set<MyPoint> pointSet;
        int pointNum = 0;
        Line(MyPoint... points){
            pointSet = new HashSet<>(Arrays.asList(points));
            pointNum = pointSet.stream().mapToInt(p -> pointMap.get(p)).sum();
        }
    }
    
    private static Map<MyPoint, Integer> pointMap = new HashMap<>();
    
    public int maxPoints(Point[] points) {
        if(points == null || points.length == 0)
            return 0;
        
        MyPoint[] myPoints = new MyPoint[points.length];
        for(int i = 0; i < points.length; i++)
            myPoints[i] = new MyPoint(points[i]);
        
        pointMap.clear();
        Arrays
            .stream(myPoints)
            .forEach(p -> pointMap.merge(p, 1, (i1, i2) -> i1 + i2));
        if(pointMap.size() == 1)
            return pointMap.values().iterator().next();
        
        Map<MyPoint, Map<Slope, Line>> maps = new HashMap<>();
        pointMap.keySet().forEach(p -> maps.put(p, new HashMap<>()));
        
        int max = 0;
        Set<MyPoint> computedSet = new HashSet<>();
        for(MyPoint curP: pointMap.keySet()){
            Set<MyPoint> computingSet = new HashSet<>(computedSet);
            while(!computingSet.isEmpty()){
                MyPoint preP = computingSet.iterator().next();
                computingSet.remove(preP);
                Slope slope = getSlope(curP, preP);
                if(maps.get(preP).containsKey(slope)){
                    Line line = maps.get(preP).get(slope);
                    computingSet.removeAll(line.pointSet);
                    line.pointSet.add(curP);
                    line.pointNum += pointMap.get(curP);
                    if(line.pointNum > max)
                        max = line.pointNum;
                }else{
                    Line line = new Line(curP, preP);
                    maps.get(curP).put(slope, line);
                    if(line.pointNum > max)
                        max = line.pointNum;
                }
            }
            computedSet.add(curP);
        };
        return max;
    }

    private Slope getSlope(Point p1, Point p2) {
        int dividend = p1.y - p2.y;
        int divisor  = p1.x - p2.x;
        if(dividend == 0)
            return new Slope(true, 0, 1);
        if(divisor == 0)
            return new Slope(true, 1, 0);
        boolean isNeg = (dividend > 0 && divisor < 0) || 
                (dividend < 0 && divisor > 0);
        dividend = Math.abs(dividend);
        divisor  = Math.abs(divisor);
        int gcd  = gcd(dividend, divisor);
        return new Slope(isNeg, dividend / gcd, divisor/ gcd);
    }
    
    @Test
    public void test(){
        assertEquals(1, maxPoints(new Point[]{new Point(1, 1)}));
    }
    
    @Test
    public void test1(){
        assertEquals(2, maxPoints(new Point[]{new Point(1, 1), new Point(2, 2)}));
    }
    
    @Test
    public void test2(){
        assertEquals(3, maxPoints(new Point[]{new Point(1, 1), new Point(2, 2), new Point(3, 3)}));
    }
    
    @Test
    public void test3(){
        assertEquals(2, maxPoints(new Point[]{new Point(1, 1), new Point(1, 1)}));
    }
    
    @Test
    public void test4(){
        assertEquals(3, maxPoints(new Point[]{new Point(1, 1), new Point(1, 1), new Point(2, 3)}));
    }
    
    @Test
    public void test5(){
        assertEquals(3, maxPoints(new Point[]{new Point(0, 0), new Point(1, 1), new Point(0, 0)}));
    }
    
    @Test
    public void test6(){
        assertEquals(3, maxPoints(new Point[]{new Point(-4,1), new Point(-7,7), new Point(-1,5), new Point(9,-25)}));
    }
    
}
