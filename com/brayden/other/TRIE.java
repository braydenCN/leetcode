package com.brayden.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TRIE {

    private static class TrieNode{
        private char c;
        private int count = 1;
        public TrieNode(char c) {
            this.c = c;
        }
        public TrieNode() {}
        private Map<Character, TrieNode> map = null;
    }
    
    TrieNode root = new TrieNode();
    
    public void insert(String s){
        insertInt(root, s);
    }
    
    private void insertInt(TrieNode n, String s) {
        if(s == null || s.length() == 0)
            return;
        char c = s.charAt(0);
        if(n.map == null)
            n.map = new HashMap<>();
        if(!n.map.containsKey(c))
            n.map.put(c, new TrieNode(c));
        else
            n.map.get(c).count++;
        insertInt(n.map.get(c), s.substring(1));
    }

    public void delete(String s){
        deleteInt(root, s);
    }
    
    private void deleteInt(TrieNode n, String s) {
        if(s == null || s.length() == 0)
            return;
        char c = s.charAt(0);
        if(!root.map.containsKey(c))
            return;
        root.map.get(c).count--;
        if(root.map.get(c).count == 0)
            root.map.remove(c);
        else
            deleteInt(root.map.get(c), s.substring(1));
    }

    public int frequency(String s){
        
    }
    
    public Set<String> prefixSet(String prefix){
        
    }
}
