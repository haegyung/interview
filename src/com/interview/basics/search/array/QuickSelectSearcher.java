package com.interview.basics.search.array;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: stefanie
 * Date: 10/17/14
 * Time: 4:50 PM
 */
public class QuickSelectSearcher<T extends Comparable<T>> extends ArraySearcher<T> {
    static Random RAND = new Random();

    protected QuickSelectSearcher(T[] input) {
        super(input);
    }

    @Override
    public T find(T element) {
        return find(element, 0, input.length - 1);
    }

    protected T find(T element, int low, int high){
        if(low > high) return null;
        else if(low == high) {
            if(element.equals(input[low])) return input[low];
            else return null;
        }
        int rand = low + RAND.nextInt(high - low);  //random shuffle
        T key = input[rand];
        if(element.equals(key)) return input[rand];
        if(rand != low) swap(input, rand, low);
        int partition = partition(low, high);
        if(element.compareTo(key) < 0) return find(element, low, partition - 1);
        else return find(element, partition + 1, high);
    }

    protected int partition(int low, int high){
        int i = low;
        for(int j = low + 1; j <= high; j++)
            if(input[j].compareTo(input[low]) < 0 && ++i != j)  swap(input, i, j);
        if(low != i) swap(input, low, i);
        return i;
    }

    public void swap(T[] input, int i, int j){
        T temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
