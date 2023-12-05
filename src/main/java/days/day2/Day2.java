package days.day2;


import days.day1.Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

//Day 2: Cube Conundrum
public class Day2 {

    private static int part1(String relativePath) {
        HashMap<String,Integer> colorToCount;
        colorToCount = new HashMap<>();
        colorToCount.put("red", 12);
        colorToCount.put("green", 13);
        colorToCount.put("blue", 14);

        int ans = 0;
        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream(relativePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                boolean isPossible = true;
                String[] info = line.split(":");
                String[] sets = info[1].split(";");
                for (String set: sets) {
                    if (!isPossible) break;
                    String[] cubes = set.split(",");
                    for (String cube: cubes) {
                        String[] cubeInfo = cube.trim().split(" ");
                        int count = Integer.parseInt(cubeInfo[0]);
                        String color = cubeInfo[1];
                        if(colorToCount.get(color) < count) {
                            isPossible = false;
                            break;
                        }
                    }
                }

                int id = Integer.parseInt(info[0].split(" ")[1]);
                if (isPossible) ans += id;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans ;
    }

    private static int part2(String relativePath) {
        HashMap<String,Integer> colorToCount;
        int ans = 0;
        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream(relativePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                colorToCount = new HashMap<>();
                String[] sets =  line.split(":")[1].split(";");
                for (String set: sets) {
                    String[] cubes = set.split(",");
                    for (String cube: cubes) {
                        String[] cubeInfo = cube.trim().split(" ");
                        //int count = Integer.parseInt(cubeInfo[0]);
                        String color = cubeInfo[1];
                        int count = colorToCount.getOrDefault(color, 0);
                        count = count > Integer.parseInt(cubeInfo[0]) ? count : Integer.parseInt(cubeInfo[0]);
                        colorToCount.put(color, count);
                    }
                }

                int gamePower = 1;
                for (String color: colorToCount.keySet()) {
                    gamePower *= colorToCount.get(color);
                }
                ans += gamePower;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Answer for part 1 : "  + part1("day2/part1/input.txt"));
        System.out.println("Answer for part 2 : "  +part2("day2/part2/input.txt"));
    }




}
