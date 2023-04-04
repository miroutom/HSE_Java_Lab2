package Lab2;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws IOException {
        System.out.println("Enter a full path to your file.");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        System.out.println(openFile(fileName));
        try{
            System.out.println("Enter a path to save your result.");
            String fileNameToWrite = scanner.next();
            File newFile = new File(fileNameToWrite);
            if (!newFile.createNewFile())
                System.out.println("File has already existed.");
            else System.out.println("File was created.");
            FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(openFile(fileName));
            printWriter.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static HashMap<Character, Integer> openFile(String fileName) throws IOException {
        try{
            File file = new File(fileName);
            FileInputStream fileInput = new FileInputStream(file);
            InputStreamReader input = new InputStreamReader(fileInput);
            BufferedReader reader = new BufferedReader(input);
            String str;
            HashMap<Character, Integer> countOfEachElem = new HashMap<>();
            while ((str = reader.readLine()) != null) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z' || str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                        if (countOfEachElem.containsKey(str.charAt(i)))
                            countOfEachElem.put(str.charAt(i), countOfEachElem.get(str.charAt(i)) + 1);
                        else countOfEachElem.put(str.charAt(i), 1);
                    }
                }
            }
            return countOfEachElem;
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
