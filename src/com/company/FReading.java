package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FReading {


    public static ArrayList<String> data = new ArrayList<String>();
    private static Scanner scan = new Scanner(System.in);

    public static boolean fileInputCheck(String addressImport){

        // NEED TO ADD MORE VALIDATION OF FILE ADDRESS //
        if(addressImport != null){
            System.out.println("Address accepted");
            return true;


        }
        else{
            System.out.println("Please enter the address of the file!");
            return false;
        }
    }




    public static String filePathFull(String addImport){
        String fullFilePath = "";
        System.out.println(addImport);
        File codeFile = new File(addImport);
        File[] fileNameArray = codeFile.listFiles();
        for(int i = 0; i<fileNameArray.length ; i++){
            System.out.println(fileNameArray[i].getName());
        }
        String file = fileName();
        fullFilePath = (addImport+"\\"+file);
        System.out.println(fullFilePath);
        return fullFilePath;
    }

    private static String fileName(){
        String file;
        System.out.println("Please Enter the file from the list above that you want to decode!");
        file = scan.nextLine();
        return file;
    }

    public static void fileReader(String addImport){

        try {
            File file = new File(addImport);
            FileReader fileReading = new FileReader(file);
            BufferedReader fileIn = new BufferedReader(fileReading);

            String line;
            while((line=fileIn.readLine())!=null){
                data.add(line.replace(';', ' ').trim());

            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Could Not Be Located"); // If This error returns then fileInputCheck needs more work
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("");
        }
    }


}
