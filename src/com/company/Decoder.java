package com.company;

import java.util.*;

public class Decoder {


    private static int interpreterLine;
    private int stackPointer = 0;
    private int queuePointer = 0;
    private static ArrayList<String> opCodeHold = new ArrayList<String>(); // Holds opcode
    private static ArrayList<String> opErandHold = new ArrayList<String>(); // Holds Operand

    private static ArrayList<String> funcName = new ArrayList<>();
    private static ArrayList<Integer> funcLocation = new ArrayList<>();

    private static ArrayList<String> variableHolder = new ArrayList<String>(); // Holds variable values in order of creation
    private static ArrayList<Integer> variableValue = new ArrayList<Integer>();
    private static Stack<Integer>  loopWhileLocation = new Stack<Integer>();
    private static Queue<Integer> loopEndLocation = new LinkedList<>();

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

    public void runInterpreter(int lineLocation){
        interpretCode(opCodeHold, opErandHold , lineLocation);
    }


    public void interpretCode(ArrayList<String> opCode, ArrayList<String> opErand, int lineLocation){
        int currentPos = lineLocation;

        int max = opCodeHold.size();
        String opcodeString;
        String operandString;

        while(currentPos<max){
            opcodeString = opCode.get(currentPos);
            operandString = opErand.get(currentPos);


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
                loopWhileLocation.push(currentPos);
                stackPointer++;

                loop(variableValue.get(varPosition(operandString)));

            }else if(opcodeString.contains("end")){
                if(variableValue.get(varPosition(operandString))==0){
                    loopEndLocation.add(currentPos);
                    System.out.println(currentPos);
                    stackPointer--;
                    queuePointer++;
                    interpretCode(opCodeHold, opErandHold, (loopEndLocation.poll())+1);
                }else{
                    loop(loopWhileLocation.get(stackPointer-1));
                }
                // interpretCode(opCodeHold, opErandHold, (loopEndLocation.peek())+1);

            }else if(opcodeString.contains("func")){
                saveFunc(currentPos, operandString);
            }
            else if(opcodeString.contains("funcR")){
                interpretCode(opCodeHold, opErandHold, funcLocation.get(funcName.indexOf(operandString)));
            }

            else if(opcodeString.contains("term")){
                System.exit(0);
            }else{
                System.exit(0);
            }


        }
    }

    public void loop(int startingValue) {
        interpretCode(opCodeHold, opErandHold, (loopWhileLocation.get(stackPointer - 1) + 1));
    }
    public void saveFunc(int currentPos, String operandString){
        if(funcName.contains(operandString)){
            funcName.add(operandString);
            funcLocation.add(funcName.indexOf(operandString),currentPos);
        }else{

            funcLocation.add(funcName.indexOf(operandString),currentPos);
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





}
