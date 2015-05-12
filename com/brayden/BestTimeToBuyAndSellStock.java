package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

public class BestTimeToBuyAndSellStock {
	
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        
        int min = prices[0], profit = 0;
        for(int price: prices){
            profit = Math.max(profit, price - min);
            min = Math.min(min, price);
        }
        return profit;
    }
    
    public int maxProfit1(int[] prices) {
    	if(prices == null || prices.length == 0)
    		return 0;
    	
    	int min           =  prices[0]; // current minimum price
    	int buyPrice      =  prices[0];
        int sellPrice     =  prices[0];
        
        for(int curPrice: prices){
        	if(curPrice < min)
        		min       =  curPrice; // min is always smaller than buyPrice
        	if(curPrice > sellPrice)
        		sellPrice =  curPrice;
        	if(curPrice - min > sellPrice - buyPrice){
        		sellPrice =  curPrice;
        		buyPrice  =  min;
        	}
        }
        return sellPrice - buyPrice;
    }
    
    @Test
    public void test(){
    	assertEquals(11, maxProfit(new int[]{5, 2, 9, 11, 1, 6, 12, 4}));
    	
    	/**
Submission Result: Wrong Answer
Input: 	[4,1,2]
Output: 	0
Expected: 	1
    	 */
    	assertEquals(1, maxProfit(new int[]{4, 1, 2}));

    	assertEquals(0, maxProfit(new int[]{4, 1}));
    	
    }
}
