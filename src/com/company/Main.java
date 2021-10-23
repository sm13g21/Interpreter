package com.company;

import java.util.*;

public class Main {

public Scanner scan = new Scanner(System.in);

    private static String filePath;

    public static void main(String[] args) {
        FReading FReading = new FReading();
        Main MainClass = new Main();
        String fileAddress = MainClass.addressGetter();
        FReading.fileInputCheck(fileAddress);
        if(FReading.checkAddress()==true){
            com.company.FReading.fileReader(filePath);
        }else{
            System.out.println("Address not acceptable");
        }
    }

    public String addressGetter(){
        System.out.println("Please Enter the file path to the folder the file is contained within");
        String fAddress = scan.nextLine();
        filePath = fAddress;
        return fAddress;
    }
}
