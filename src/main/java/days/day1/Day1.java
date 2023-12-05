package days.day1;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

//advent of code 2023 day 1 solution java
public  class Day1 {

    public static int part1(String relativePath) {
        int totalCalibrationValue = 0;


        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream(relativePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String digits = line.replaceAll("[^0-9]", "");

                if (digits.length() >= 2) {
                    char firstDigit = digits.charAt(0);
                    char lastDigit = digits.charAt(digits.length() - 1);
                    totalCalibrationValue += Integer.parseInt("" + firstDigit + lastDigit);
                }
                if (digits.length() == 1) totalCalibrationValue += Integer.parseInt("" + digits.charAt(0) + digits.charAt(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalCalibrationValue;
    }

    private static int part2(String relativePath) {
        int totalCalibrationValue = 0;
        Trie trie = new Trie();

        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream(relativePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                totalCalibrationValue += findFirstAndLastRealDigitSum(line, trie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalCalibrationValue;
    }

    private static int findFirstAndLastRealDigitSum(String line, Trie trie) {
        int lineLength = line.length();
        if (lineLength == 0) return 0;

        char firstDigit = findFirstRealDigit(line, trie);
        char lastDigit = findLastRealDigit(line, trie);

        return Integer.parseInt("" + firstDigit + lastDigit);
    }

    private static Character findFirstRealDigit(String line, Trie trie) {
        HashMap<Character, Trie.TrieNode> children = trie.getRootOfForward().getChildren();
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) return ch;
            int index = i;
            while (children.containsKey(ch)) {
                Trie.TrieNode t = children.get(ch);
                if (t.isLeaf()) return t.getDigit();
                else children = t.getChildren();
                index++;
                if (index > line.length() - 1) break;
                else {
                    ch = line.charAt(index);
                    if (Character.isDigit(ch)) return ch;
                }
            }
            children = trie.getRootOfForward().getChildren();
        }
        return '0';
    }

    private static char findLastRealDigit(String line, Trie trie) {
        HashMap<Character, Trie.TrieNode> children = trie.getRootOfBackForward().getChildren();
        for (int i = line.length() - 1; i > -1 ; i--) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) return ch;
            int index = i;
            while (children.containsKey(ch)) {
                Trie.TrieNode t = children.get(ch);
                if (t.isLeaf()) return t.getDigit();
                else children = t.getChildren();
                index--;
                if (index < 0) break;
                else {
                    ch = line.charAt(index);
                    if (Character.isDigit(ch)) return ch;
                }
            }
            children = trie.getRootOfBackForward().getChildren();
        }
        return '0';
    }

    public static void main(String[] args) {
        System.out.println("Answer for part 1: " + part1("day1/part1/input.txt"));

        System.out.println("Answer for part 2: " + part2("day1/part2/input.txt"));

    }


}
