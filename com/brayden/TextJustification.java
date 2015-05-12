package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextJustification {
    
    public List<String> fullJustify(String[] words, int L) {
        if(words == null || words.length == 0)
            return Collections.emptyList();
        
        List<String> resultList  = new ArrayList<>();
        List<String> oneLineList = new ArrayList<>();
        int charCount = 0;
        for(String word: words){
            /** current charCount + whitespace number + length of this word **/
            if(charCount + oneLineList.size() + word.length() > L){
                resultList.add(processOneLine(oneLineList, charCount, L));
                oneLineList.clear();
                charCount = 0;
            }
            charCount += word.length();
            oneLineList.add(word);
        }
        
        resultList.add(processLastLine(oneLineList, charCount, L));
        return resultList;
    }

    private String processLastLine(List<String> oneLineList, int charCount, int L) {
        StringBuilder sb = new StringBuilder();
        sb.append(oneLineList.get(0));
        for(int i = 1; i < oneLineList.size(); i++){
            sb.append(" ");
            sb.append(oneLineList.get(i));
        }
        for(int i = 0; i < L - charCount - oneLineList.size() + 1; i++)
            sb.append(" ");
        
        return sb.toString();
    }

    private String processOneLine(List<String> oneLineList, int charCount, int L) {        
        StringBuilder sb = new StringBuilder();
        sb.append(oneLineList.get(0));
        
        int wbNum        =  L - charCount;
        int boundaryNum  =  oneLineList.size() - 1;
        if(boundaryNum == 0){
            for(int i = 0; i < wbNum; i++)
                sb.append(" ");
            return sb.toString();
        }
        
        int baseWbNum    =  wbNum / boundaryNum;
        int oneMoreInd   =  wbNum % boundaryNum;
        
        for(int i = 1; i < oneLineList.size(); i++){
            for(int j = 0; j < baseWbNum; j++)
                sb.append(" ");
            if(i <= oneMoreInd)
                sb.append(" ");
            sb.append(oneLineList.get(i));
        }
        
        return sb.toString();
    }
    
}
