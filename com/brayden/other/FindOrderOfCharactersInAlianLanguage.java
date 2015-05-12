package com.brayden.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.brayden.LeetcodeCommon;

public class FindOrderOfCharactersInAlianLanguage extends LeetcodeCommon {

    private static class CharNode{
        public CharNode(char c1) {
            c = c1;
        }
        char c;
        @Override
        public String toString() {
            return "CharNode [c=" + c + ", postSet=" + postSet
                    + ", preSet=" + preSet + "]";
        }
        Set<Character> postSet = new HashSet<>();
        Set<Character> preSet = new HashSet<>();
    }
    
    public List<Character> findOrder(String[] words){
        if(words == null || words.length < 2)
            return Collections.emptyList();
        
        Map<Character, CharNode> map = new HashMap<>();
        String preWord = words[0];
        int len = words.length;
        for(int i = 1; i < len; i++){
            String curWord = words[i];
            for(int j = 0; j < preWord.length() && j < curWord.length(); j++){
                char c1 = preWord.charAt(j), c2 = curWord.charAt(j);
                if(c1 != c2){
                    CharNode c1Node = map.computeIfAbsent(c1, 
                            k -> new CharNode(k));
                    CharNode c2Node = map.computeIfAbsent(c2, 
                            k -> new CharNode(k));
                    c1Node.postSet.add(c2);
                    c2Node.preSet.add(c1);
                    break;
                }
            }
            preWord = curWord;
        }
        
        CharNode node = null;
        for(CharNode n: map.values())
            if(n.preSet.isEmpty()){
                node = n;
                break;
            }
        
        List<Character> list = new ArrayList<>();
        do{
            list.add(node.c);
            if(node.postSet.isEmpty())
                break;
            CharNode tmpNode = null;
            for(char c: node.postSet){
                CharNode node1 = map.get(c);
                node1.preSet.remove(node.c);
                if(node1.preSet.isEmpty())
                    tmpNode = node1;
            }
            node = tmpNode;
        }while(true);
        
        return list;
    }
    
    @Test
    public void test(){
        List<Character> expected = Arrays.asList(Character.valueOf('b'), 
                Character.valueOf('d'), Character.valueOf('a'), Character.valueOf('c'));
        assertListEquals(expected, findOrder(new String[]{"baa", "abcd", "abca", "cab", "cad"}));
    }
    
    @Test
    public void test1(){
        List<Character> expected = Arrays.asList(Character.valueOf('c'), 
                Character.valueOf('a'), Character.valueOf('b'));
        assertListEquals(expected, findOrder(new String[]{"caa", "aaa", "aab"}));
    }
    
    @Test
    public void test2(){
        List<Character> expected = Arrays.asList(Character.valueOf('c'), 
                Character.valueOf('a'), Character.valueOf('b'));
        assertListEquals(expected, findOrder(new String[]{"cc", "ca", "ba", "bb"}));
    }
}
