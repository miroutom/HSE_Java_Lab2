package Lab2;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws IOException {
        System.out.println("Enter a full path to your file.");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        long capitalLetters = openFile(fileName)[0];
        long lowercaseLetters = openFile(fileName)[1];
        System.out.println("Number of capital letters is " + capitalLetters);
        System.out.println("Number of lowercase letters is " + lowercaseLetters);
        try{
            System.out.println("Enter a path to save your result.");
            String fileNameToWrite = scanner.next();
            File newFile = new File(fileNameToWrite);
            if (!newFile.createNewFile())
                System.out.println("File has already existed.");
            else System.out.println("File was created.");
            FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Number of capital letters is " + capitalLetters);
            printWriter.println("Number of lowercase letters is " + lowercaseLetters);
            printWriter.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static long[] openFile(String fileName) throws IOException {
        try{
            File file = new File(fileName);
            FileInputStream fileInput = new FileInputStream(file);
            InputStreamReader input = new InputStreamReader(fileInput);
            BufferedReader reader = new BufferedReader(input);
            String str;
            long countCapital = 0;
            long countLower = 0;
            while ((str = reader.readLine()) != null) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                        countCapital++;
                    else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                        countLower++;
                }
            }
            return new long[]{countCapital, countLower};
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new long[0];
    }
}