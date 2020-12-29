package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        String[][] array = {};
        try{
            array = getArrayFromFile("input.txt");
        }
        catch (FileNotFoundException ex){
            System.out.println("File no found");
        }

        printArray(sortByColumn(array, 3));

        //printArray(array);

        System.out.println("Общее число иммигрантов в этих странах: " +
                totalNumOfImmigrants(array));
        System.out.println("Общий процент иммигрантов в этих странах: " +
                totalPercentOfImmigrants(array));
        System.out.println("Самый низкий процент иммигрантов: " +
                minImmigrants(array));
        System.out.println("Самый высокий процент иммигрантов: " +
                maxImmigrants(array));
        System.out.println("Общая численность населения всех стран: " +
                totalPopulation(array));
    }

    public static String[][] sortByColumn(String[][] arr, int column){
        // Пузырёк
        for(int i = 0; i < arr.length - 1; i++){
            double maxElement = 0;
            int maxIndex = i;
            for(int j = i; j < arr.length; j++){
                if(Double.parseDouble(arr[j][column]) > maxElement){
                    String buffer = arr[j][column];
                    arr[j][column] = arr[maxIndex][column];
                    arr[maxIndex][column] = buffer;

                    maxElement = Double.parseDouble(buffer);
                }
            }
        }
        return arr;
    }

    public static String[][] getArrayFromFile(String fileName) throws FileNotFoundException{
        FileReader fr = new FileReader(fileName);
        Scanner in = new Scanner(fr);

        String input = "";
        int n = 0;

        while(in.hasNextLine()) {
            input += in.nextLine() + "\t";
            n++;
        }

        String[][] result = new String[n][4];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < 4; j++){
                String nextString = input.substring(0, input.indexOf('\t'));
                input = input.substring(input.indexOf('\t') + 1, input.length());
                result[i][j] = nextString;
            }
        }

        return result;
    }

    public static int totalPopulation(String[][] arr){
        int sum = 0;
        for(String[] i : arr){
            try{
                sum += Integer.parseInt(i[1]) / Double.parseDouble(i[3]) * 100;
            }
            catch (NumberFormatException ex){
                System.out.println("Ошибка в записях: " + i[1] + ", " + i[3]);
            }
        }
        return sum;
    }

    public static String minImmigrants(String[][] arr){
        double min = 100;
        String result = "";

        for(String[] i : arr){
            if(Double.parseDouble(i[3]) < min){
                min = Double.parseDouble(i[3]);
                result = i[0];
            }
        }
        return result;
    }

    public static String maxImmigrants(String[][] arr){
        double max = 0;
        String result = "";

        for(String[] i : arr){
            if(Double.parseDouble(i[3]) > max){
                max = Double.parseDouble(i[3]);
                result = i[0];
            }
        }
        return result;
    }

    public static double totalPercentOfImmigrants(String[][] arr){
        double sum = 0;

        for(String[] i : arr){
            try{
                sum += Double.parseDouble(i[2]);
            }
            catch (Exception ex){
                System.out.println("Ошибка в записи: " + i[2]);
            }
        }
        return sum;
    }

    public static int totalNumOfImmigrants(String[][] arr){
        int sum = 0;
        for(String[] i : arr){
            try {
                sum += Integer.parseInt(i[1]);
            }
            catch (Exception ex){
                System.out.println("Ошибка в записи: " + i[1]);
            }
        }
        return sum;
    }

    public static void printHead(){
        System.out.format("%30s", "Country");
        System.out.format("%30s", "Number of Immigrants");
        System.out.format("%30s", "Percentage of");
        System.out.format("%30s", "Immigrants as");

        System.out.println();

        System.out.format("%90s", "Total Immigrants");
        System.out.format("%30s", "Percentage of");

        System.out.println();

        System.out.format("%90s", "in the World");
        System.out.format("%30s", "National Population");

        System.out.println();
    }

    public static void printArray(String[][] arr){
        printHead();
        for(String[] i : arr){
            for(String j : i){
                System.out.format("%30s", j);
            }
            System.out.println();
        }
    }
}
