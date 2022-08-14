package edu.sdccd.cisc191.template;

import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> nodes;
    boolean finalNode;

    public void insert(String string) {
        // Recursively traverse trie and insert characters
        if (string.length() == 0) {
            finalNode = true;
            return;
        }
        char firstCharacter = string.charAt(0);
        if (nodes.get(firstCharacter) == null) {
            nodes.put(firstCharacter, new TrieNode(new HashMap<>(), false));
        }
        nodes.get(firstCharacter).insert(string.substring(1));
    }

    public void remove(String string) {
        // Recursively traverse trie and remove entry
        if (string.length() == 0) {
            finalNode = false;
            return;
        }
        char firstCharacter = string.charAt(0);
        if (nodes.get(firstCharacter) != null) {
            nodes.get(firstCharacter).remove(string.substring(1));
        }
    }

    public boolean search(String string) {
        // Recursively traverse trie and search for entry
        if (string.length() == 0) {
            return finalNode;
        }
        char firstCharacter = string.charAt(0);
        if (nodes.get(firstCharacter) != null) {
            return nodes.get(firstCharacter).search(string.substring(1));
        }
        return false;
    }

    public TrieNode(HashMap<Character, TrieNode> nodes, boolean finalNode) {
        this.nodes = nodes;
        this.finalNode = finalNode;
    }
}
