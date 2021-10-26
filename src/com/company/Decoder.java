package com.company;

import java.util.ArrayList;

public class Decoder {

    private static int lineCounter;
    private static ArrayList<String> opCodeHold = new ArrayList<String>(); // Holds opcode
    private static ArrayList<String> opErandHold = new ArrayList<String>(); // Holds Operand

    private static ArrayList<String> variableHolder = new ArrayList<String>(); // Holds variable values in order of creation
    private static ArrayList<Integer> variableValue = new ArrayList<Integer>();


    public void opCode(ArrayList<String> arrayList){
        int maxSize = arrayList.size();
        int pointer = 0;
        while(pointer<maxSize){
            String[] arrContents = arrayList.get(pointer).split(" ", 2);
            String opCode = arrContents[0];
            opCodeHold.add(pointer, opCode);
            System.out.println(opCode);
            pointer++;
        }
    }
    public void opErand(ArrayList<String> arrayList){
        int maxSize = arrayList.size();
        int pointer = 0;
        while(pointer<maxSize){
            String[] arrContents = arrayList.get(pointer).split(" ", 2);
            String opErand;
            try {
                opErand = arrContents[1];
            }catch(ArrayIndexOutOfBoundsException e){
                opErand = "!";
            }
            opErandHold.add(pointer, opErand);
            System.out.println(opErand);
            pointer++;
        }
    }

    public void runInterpreter(){
        interpretCode(opCodeHold, opErandHold);
    }


    public void interpretCode(ArrayList<String> opCode, ArrayList<String> opErand){
        int currentPos = 0;
        int varPoint = 0;
        int max = opCodeHold.size();
        String opcodeString;
        String operandString;
        lineCounter = 0;
        while(currentPos<max){
            opcodeString = opCode.get(currentPos);
            operandString = opErand.get(currentPos);
            lineCounter++;

            if(variableHolder.contains(operandString)!=true){ // placing variables in array in order of creation
                variableHolder.add(operandString);
                variableValue.add(0);
            }

            if(opcodeString.contains("clear")){
                variableValue.add(varPosition(operandString) ,clear(variableValue.get(varPosition(operandString))));
                System.out.println(variableValue.get(varPosition(operandString)));

                currentPos++;
            }else if(opcodeString.contains("incr")){
                variableValue.add(varPosition(operandString),incr((variableValue.get(varPosition(operandString)))));
                System.out.println(variableValue.get(varPosition(operandString)));
                currentPos++;
            }else if(opcodeString.contains("decr")){
                variableValue.add(varPosition(operandString),decr((variableValue.get(varPosition(operandString)))));
                System.out.println(variableValue.get(varPosition(operandString)));
                currentPos++;
            }else if(opcodeString.contains("while")) {
                loop(variableValue.get(varPosition(operandString)));
                System.out.println(variableValue.get(varPosition(operandString)));


            }else{
                System.exit(0);
            }


        }
    }

    public static int varPosition(String varName){
        int currentPos;
        currentPos = variableHolder.indexOf(varName);
        return currentPos;
    }

    private static int clear(int varValue){
        int value = varValue;
        value = 0;
        return value;
    }
    private static int incr(int varValue){
        int value = varValue;
        value = value+1;
        return value;
    }
    private static int decr(int varValue){
        int value = varValue;
        value = value-1;
        return value;
    }
    private static int loop(int InputVal){
        int returnVal = 0;
        int startingVal = InputVal;
        if(startingVal==0){
            returnVal = startingVal;
        }else{
            int startingPos = opCodeHold.indexOf(lineCounter);
            System.out.println(startingPos);
            int endPos =  opCodeHold.indexOf("end");
            System.out.println(endPos);
            System.exit(0);
        }
        return returnVal;
    }












}
