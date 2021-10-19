package com.company;
import java.io.*;

public class FReading {

    public String codeLineHolder[];

    public static void fileInputCheck(String addressImport){
        FReading ReaderClass = new FReading();
        // NEED TO ADD MORE VALIDATION OF FILE ADDRESS //
        if(addressImport != null){
            System.out.println("Address accepted");
            //ReaderClass.fileReader
            filePathFull(addressImport);
        }
        else{
            System.out.println("Please enter the address of the file!");
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




        return fullFilePath;
    }

    public static void fileReader(String addImport){
        FReading Fread = new FReading();
        try {
            FileReader fileReading = new FileReader(addImport);
            BufferedReader fileIn = new BufferedReader(fileReading);
            int counter = 0;
            String fileLine;

            while((fileLine = fileIn.readLine()) != null){
                Fread.codeLineHolder[counter] = fileLine;

            }
            for(int j = 0; j < Fread.codeLineHolder.length ; j++){
                System.out.println(j);

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
