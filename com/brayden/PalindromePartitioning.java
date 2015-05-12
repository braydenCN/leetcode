package com.brayden;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PalindromePartitioning extends LeetcodeCommon {

    public List<List<String>> partition(String s) {
    	if(s == null || s.isEmpty())
    		return null;
    	
    	int len = s.length();
    	List<List<List<String>>> resLists = new ArrayList<>();
    	List<List<String>> list0 = new ArrayList<>();
    	list0.add(new ArrayList<String>());
    	list0.get(0).add(s.substring(0, 1));
    	resLists.add(list0);
    	
    	for(int i = 1; i < s.length(); i++){
    		List<List<String>> listI = new ArrayList<>();
    		for(int j = 0; j < i; j++){
    			if(!isPalindrome(s.substring(j + 1, i + 1)))
                    continue;
    			for(List<String> list: resLists.get(j)){
    				List<String> newList = new ArrayList<>(list);
    				newList.add(s.substring(j + 1, i + 1));
    				listI.add(newList);
    			}
    		}
    		if(isPalindrome(s.substring(0, i + 1))){
    			List<String> newList = new ArrayList<>();
    			newList.add(s.substring(0, i + 1));
				listI.add(newList);
    		}
    		resLists.add(listI);
    	}
    	return resLists.get(len - 1);
    }

	@Test
	public void test(){
//		List<List<String>> expectedList = new ArrayList<>();
//		expectedList.add(new ArrayList<String>());
//		expectedList.add(new ArrayList<String>());
//		expectedList.get(0).add("aa");
//		expectedList.get(0).add("b");
//		expectedList.get(1).add("a");
//		expectedList.get(1).add("a");
//		expectedList.get(1).add("b");
//		assertListOfListEquals(expectedList, partition("aab"));
		
		/**
		 * 
Submission Result: Time Limit Exceeded
Last executed input: 	"kwtbjmsjvbrwriqwxadwnufplszhqccayvdhhvscxjaqsrmrrqngmuvxnugdzjfxeihogzsdjtvdmkudckjoggltcuybddbjoizu"
		 */
		partition("kwtbjmsjvbrwriqwxadwnufplszhqccayvdhhvscxjaqsrmrrqngmuvxnugdzjfxeihogzsdjtvdmkudckjoggltcuybddbjoizu");
	}
}
