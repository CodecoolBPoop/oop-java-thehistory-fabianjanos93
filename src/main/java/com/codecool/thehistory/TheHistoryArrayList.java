package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        //TODO: check the TheHistory interface for more information
        Collections.addAll(wordsArrayList,text.split(" "));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        //TODO: check the TheHistory interface for more information
        while (this.wordsArrayList.remove(wordToBeRemoved)){}
    }

    @Override
    public int size() {
        //TODO: check the TheHistory interface for more information
        return this.wordsArrayList.size();
    }

    @Override
    public void clear() {
        //TODO: check the TheHistory interface for more information
        this.wordsArrayList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        //TODO: check the TheHistory interface for more information
        Collections.replaceAll(this.wordsArrayList,from,to);
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        String[] previousWords = new String[fromWords.length];
        ListIterator iter = this.wordsArrayList.listIterator();
        int countdown = fromWords.length-1;
        List<String> answer = new ArrayList<>();
        while (iter.hasNext()){
            for(int j=0; j<fromWords.length-1; j++){
                previousWords[j] = previousWords[j+1];
            }
            previousWords[fromWords.length-1] = iter.next().toString();
            if(countdown == 0){
                if (Arrays.equals(previousWords,fromWords)){
                    Collections.addAll(answer,toWords);
                    countdown = fromWords.length-1;
                } else {
                    answer.add(previousWords[0]);
                }
            } else {
                countdown--;
            }
        }
        for (int i = countdown+1; i < fromWords.length ; i++){
            answer.add(previousWords[i]);
        }
        this.wordsArrayList = answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
