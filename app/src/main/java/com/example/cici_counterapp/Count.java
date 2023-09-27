package com.example.cici_counterapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Count {
    private int[] cnt;
    private int size;
    private String[] index;
    private List<String> words;

    public Count(ArrayList<String> w) {
        this.words = w;
        this.cnt = new int[10000000];
        this.index = new String[10000000];
        this.size = 0;
    }

    private int findIndex(String s) {
        int i = 0;
        while (i < size && !index[i].equals("")) {
            if (index[i].equals(s)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void calculateCnt() {
        for (String e : words) {
            int existingIndex = findIndex(e);
            if (existingIndex == -1) {
                index[size] = e;
                cnt[size] = 1;
                size++;
            } else {
                cnt[existingIndex] += 1;
            }
        }
    }

    public String[] topFive() {
        String[] ans = new String[5];
        int[] copyCnt = Arrays.copyOf(cnt, size);
        String[] copyIndex = Arrays.copyOf(index, size);

        for (int i = 0; i < 5; i++) {
            int maxCountIndex = findMaxCountIndex(copyCnt);
//            System.out.println(maxCountIndex);
            ans[i] = copyIndex[maxCountIndex];
            copyIndex[maxCountIndex] = "";
            copyCnt[maxCountIndex] = 0;
        }

        return ans;
    }

    private int findMaxCountIndex(int[] countArray) {
        int maxCount = 0;
        int maxCountIndex = -1;

        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] > maxCount) {
                maxCount = countArray[i];
                maxCountIndex = i;
            }
        }

        return maxCountIndex;
    }

    public String topOne(){
        String ans = "";
        int maxCount = 0;
        for(int i=0; i<size; i++){
            if(cnt[i] > maxCount){
                maxCount = cnt[i];
                ans = index[i];
            }
        }
        return ans;
    }

    public int getCntByWord(String s){
        return cnt[findIndex(s)];
    }

    public int getSize() {
        return size;
    }

    public int[] getCnt() {
        return cnt;
    }

    public String[] getIndex() {
        return index;
    }
}