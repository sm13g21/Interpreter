package com.company;

import java.util.*;

public class Main {

    private Scanner scan = new Scanner(System.in);
    public static boolean addressCheck;
    private static String filePath;

    public static void main(String[] args) {
        FReading FREading = new FReading();
        Main MainClass = new Main();
        Decoder decode = new Decoder();

        String partialFileAddress = MainClass.addressGetter();
        String fileAddress = FREading.filePathFull(partialFileAddress);

        boolean addressCheck = FREading.fileInputCheck(fileAddress);
        if(addressCheck==true){
            FREading.fileReader(fileAddress);
        }else{
            System.out.println("Address not acceptable");
        }

        decode.opCode(FREading.data);
        decode.opErand(FREading.data);
        decode.runInterpreter();

    }

    public String addressGetter(){
        System.out.println("Please Enter the file path to the folder the file is contained within");
        String fAddress = scan.nextLine();
        filePath = fAddress;
        return fAddress;
    }
}
