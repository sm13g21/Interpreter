package com.company;

import java.util.*;

public class Main {

public Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        FReading FReading = new FReading();
        Main MainClass = new Main();
        String fileAddress = MainClass.addressGetter();
        com.company.FReading.fileInputCheck(fileAddress);
    }

    public String addressGetter(){
        System.out.println("Please Enter the file path to the folder the file is contained within");
        String fAddress = scan.nextLine();
        return fAddress;
    }
}
