package com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        //TODO: check the TheHistory interface for more information
        this.wordsArray = text.split(" ");
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        //TODO: check the TheHistory interface for more information
        int numberOfDeletedElements = 0;
        for( int i=0; i<this.size(); i++){
            if (this.wordsArray[i] == wordToBeRemoved){
                numberOfDeletedElements += 1;
                for( int j=i; j<this.size()-1; j++) {
                    this.wordsArray[j] = this.wordsArray[j + 1];
                }
            }
        }
        wordsArray = Arrays.copyOf(this.wordsArray,this.size()-numberOfDeletedElements);
    }

    @Override
    public int size() {
        //TODO: check the TheHistory interface for more information
        return this.wordsArray.length;
    }

    @Override
    public void clear() {
        //TODO: check the TheHistory interface for more information
        this.wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        //TODO: check the TheHistory interface for more information
        for( int i=0; i<this.size(); i++) {
            if (wordsArray[i].equals(from)) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        int[] startingPositions = new int[(this.size()/fromWords.length)];  //declaration
        int numberOfChanges = 0;
        String[] previousWords = new String[fromWords.length];
        for( int i=0; i<this.size(); i++) {                                 //search for change places
            for(int j=0; j<fromWords.length-1; j++){
                previousWords[j] = previousWords[j+1];
            }
            previousWords[fromWords.length-1] = this.wordsArray[i];

            if(Arrays.equals(previousWords,fromWords)) {                    //add search place
                startingPositions[numberOfChanges] = i-fromWords.length+1;
                numberOfChanges++;
                previousWords = new String[fromWords.length];
            }
        }
        String[] answer = new String[this.wordsArray.length-(numberOfChanges*(fromWords.length-toWords.length))];
        int originalPos = 0;
        int copyPos = 0;
        for (int i=0; i < numberOfChanges; i++){
            System.arraycopy(this.wordsArray,originalPos,answer,copyPos,startingPositions[i]-originalPos);
            copyPos += startingPositions[i]-originalPos;
            originalPos = startingPositions[i] + fromWords.length;
            System.arraycopy(toWords,0,answer,copyPos,toWords.length);
            copyPos += toWords.length;
        }
        if (copyPos<answer.length) {
            System.arraycopy(this.wordsArray, originalPos, answer, copyPos, this.size() - originalPos);
        }
        this.wordsArray = answer;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
