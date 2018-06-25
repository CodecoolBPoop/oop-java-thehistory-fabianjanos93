package com.codecool.thehistory;

import java.util.*;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        //TODO: check the TheHistory interface for more information
        Collections.addAll(this.wordsLinkedList,text.split(" "));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        //TODO: check the TheHistory interface for more information
        //while(this.wordsLinkedList.remove(wordToBeRemoved)){};
        ListIterator i = wordsLinkedList.listIterator();
        while (i.hasNext()){
            if (i.next() == wordToBeRemoved){
                i.remove();
            }
        }
    }

    @Override
    public int size() {
        //TODO: check the TheHistory interface for more information
        return this.wordsLinkedList.size();
    }

    @Override
    public void clear() {
        //TODO: check the TheHistory interface for more information
        this.wordsLinkedList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        //TODO: check the TheHistory interface for more information
        /* ListIterator i = this.wordsLinkedList.listIterator();
        while (i.hasNext()){
            if (i.next().toString().equals(from)){
                i.set(from);
            }
        } */
        Collections.replaceAll(this.wordsLinkedList,from,to);
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        String[] previousWords = new String[fromWords.length];
        ListIterator iter = this.wordsLinkedList.listIterator();
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
        this.wordsLinkedList = answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
