package days.day4;

import days.day1.Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;

public class Day4 {

    public static void main(String[] args) {
        System.out.println("Answer for part 1 : "  + part1("day4/part1/input.txt"));
        System.out.println("Answer for part 2 : "  + part2("day4/part2/input.txt"));
    }

    private static int part2(String filePath) {
        int ans = 0;
        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            ArrayList<Integer> cardsMatches = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                int matches = getMatches(line);
                cardsMatches.add(matches);
            }
            int[] instances = new int[cardsMatches.size()];
            Arrays.fill(instances, 1);
            for (int i = 0; i < cardsMatches.size(); i++) {
                int  matches = cardsMatches.get(i);
                int instance = instances[i];
                int j = 1;
                while (j <= matches && i + j < cardsMatches.size()) {
                    instances[i + j] += instance;
                    j++;
                }

            }
            for (int instance: instances) {
                ans += instance;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    private static int part1(String filePath) {
        int ans = 0;
        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                int matches = getMatches(line);
                ans += matches == 0 ? 0 :  Math.pow(2, matches - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    private static int getMatches(String line) {
        int matches = 0;
        int[] info = new int[2];

        String[] card = line.split(":");

        String[] parts = card[1].split("\\|");
        String[] winningNumbers = parts[0].split("\\s+");
        String[] givenNumbers = parts[1].split("\\s+");

        Set<String> numbers = new HashSet<>();
        numbers.addAll(Arrays.asList(winningNumbers));
        for (String num: givenNumbers) {
            if (!num.isEmpty()  && numbers.contains(num))  matches++;
        }
        return matches;
    }
}
