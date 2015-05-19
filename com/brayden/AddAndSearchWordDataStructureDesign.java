package com.brayden;

import static org.junit.Assert.*;

import org.junit.Test;

class WordDictionary {

    class TrieNode {
      char c;
      boolean endOfWord = false;
      TrieNode[] children = new TrieNode[26];
      public TrieNode() {}
    }
    
    private TrieNode root = new TrieNode();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()){
            int ind = c - 'a';
            if(node.children[ind] == null){
                node.children[ind] = new TrieNode();
                node.children[ind].c = c;
            }
            node = node.children[ind];
        }
        node.endOfWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchInt(word, root);
    }

    private boolean searchInt1(String word, TrieNode node) {
        if(node == null)
            return false;
        if(word.isEmpty())
            return node.endOfWord;
        int c = word.charAt(0);
        String nextWord = word.substring(1);
        if(c != '.')
            return searchInt(nextWord, node.children[c - 'a']);
        for(TrieNode n: node.children)
            if(searchInt(nextWord, n))
                return true;
        return false;
    }
    
    private boolean searchInt(String word, TrieNode node) {
        if(word.isEmpty())
            return node.endOfWord;
        int i = 0, len = word.length();
        for(; i < len && word.charAt(i) != '.'; i++){
            node = node.children[word.charAt(i) - 'a'];
            if(node == null)
                return false;
            if(i == len - 1)
                return node.endOfWord;
        }
        if(i != len) // word.charAt(i) == '.'
            for(TrieNode n: node.children)
                if(n != null && searchInt(word.substring(i + 1), n))
                    return true;
        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");



public class AddAndSearchWordDataStructureDesign {

    @Test
    public void test(){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("word");
        assertTrue(wordDictionary.search("word"));
        assertTrue(wordDictionary.search("wo.d"));
        assertFalse(wordDictionary.search("worda"));
    }
}
