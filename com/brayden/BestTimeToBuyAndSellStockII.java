package com.brayden;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStockII {
    
    public int maxProfit(int[] prices) {
        if(prices == null)
            return 0;
            
        int profit = 0;
        for(int i = 1; i < prices.length; i++)
            if(prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        
        return profit;
    }
    
	public int maxProfit1(int[] prices) {
		if(prices == null || prices.length < 2)
    		return 0;
		
        int buyPrice   =  prices[0];
        int sellPrice  =  prices[0];
        int prePrice   =  prices[0];
        int curPrice   =  prices[0];
        int profit     =  0;
        
        for(int i = 1; i < prices.length; i++){
        	curPrice = prices[i];
        	if(curPrice < prePrice){
        		/** commit last transaction **/
        		profit += (sellPrice - buyPrice);
        		/** start a new transaction **/
        		buyPrice = curPrice;
        	}
            sellPrice = curPrice;
            prePrice  = curPrice;
        }
        if(curPrice >= prePrice)
    		profit += (sellPrice - buyPrice);
        
        return profit;
	}
	
	@Test
	public void test(){
        assertEquals(20, maxProfit(new int[]{5, 2, 9, 11, 1, 6, 12, 4}));
	
	    assertEquals(1, maxProfit(new int[]{4, 1, 2}));

	    assertEquals(0, maxProfit(new int[]{4, 1}));
	}
	
}
