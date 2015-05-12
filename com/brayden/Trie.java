package com.brayden;

class TrieNode {
//    char c;
    boolean endOfWord = false;
    TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()){
            int ind = c - 'a';
            if(node.children[ind] == null){
                node.children[ind] = new TrieNode();
//                node.children[ind].c = c;
            }
            node = node.children[ind];
        }
        node.endOfWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.endOfWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }
    
    private TrieNode findNode(String s){
        TrieNode node = root;
        for(char c: s.toCharArray()){
            int ind = c - 'a';
            if(node.children[ind] == null)
                return null;
            node = node.children[ind];
        }
        return node;
    }
}
