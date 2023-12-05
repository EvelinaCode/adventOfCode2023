package days.day1;

import java.util.HashMap;
import java.util.Map;

public class Trie {


    public class TrieNode {
        private HashMap<Character, TrieNode> children;
        private boolean isLeaf;
        private Character digit;

        public TrieNode() {
            isLeaf = true;
            children = new HashMap<>();
            digit = null;
        }

        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isLeaf() {
            return isLeaf;
        }

        public void setIsLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        public void setDigit(Character digit) {
            this.digit = digit;
        }

        public Character getDigit() {
            return digit;
        }
    }

    private TrieNode rootOfForward;
    private TrieNode rootOfBackForward;
    private Map<String, Character> digitMap;

    public Trie() {
        this.rootOfForward = new TrieNode();
        this.rootOfBackForward = new TrieNode();
        this.digitMap = new HashMap<>();
        digitMap.put("one", '1');
        digitMap.put("two", '2');
        digitMap.put("three", '3');
        digitMap.put("four", '4');
        digitMap.put("five", '5');
        digitMap.put("six", '6');
        digitMap.put("seven", '7');
        digitMap.put("eight", '8');
        digitMap.put("nine", '9');
        initForward();
        initBackForward();
    }

    public TrieNode getRootOfForward() {
        return rootOfForward;
    }

    public TrieNode getRootOfBackForward() {
        return rootOfBackForward;
    }


    private void initForward() {
        for (String word: digitMap.keySet()) {
            TrieNode parent =  rootOfForward;
            TrieNode child;
            for (char ch: word.toCharArray()) {
                if (parent.getChildren().containsKey(ch)) {
                    child = parent.getChildren().get(ch);
                } else {
                    child = new TrieNode();
                    parent.getChildren().put(ch, child);
                    parent.setIsLeaf(false);
                }
                parent = child;
            }
            parent.setDigit(digitMap.get(word));
        }
    }

    private void initBackForward() {
        for (String word: digitMap.keySet()) {
            TrieNode parent =  rootOfBackForward;
            TrieNode child;
            for (int i = word.length() - 1; i > -1; i--) {
                char ch = word.charAt(i);
                if (parent.getChildren().containsKey(ch)) {
                    child = parent.getChildren().get(ch);
                } else {
                    child = new TrieNode();
                    parent.getChildren().put(ch, child);
                    parent.setIsLeaf(false);
                }
                parent = child;
            }
            parent.setDigit(digitMap.get(word));
        }
    }


}
