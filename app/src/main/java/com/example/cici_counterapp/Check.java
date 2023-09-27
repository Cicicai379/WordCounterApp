package com.example.cici_counterapp;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Check {
    private ArrayList<String> newWords;

    private ArrayList<String> commonWords;

    private ArrayList<String> words;

    public ArrayList<String> read(String[] args) throws Exception
    {
        return commonWords;
    }

    public Check(AssetManager assetManager, ArrayList<String> w) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> commonWords = new ArrayList<>();
        this.words = w;
        this.commonWords = commonWords;
        try {
            InputStream inputStream = assetManager.open("commonWords.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                commonWords.add(line);
//                System.out.println(line);
            }
//            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void storeEveryNewWord() {
        if (words == null) return;

        ArrayList<String> newWords = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            boolean flag = false;
            for (String e : commonWords) {
                if (e.equals(words.get(i))) {
                    flag = true;
                    break;
                }
            }
            if (!flag && words.get(i).length()>1) {
                newWords.add(words.get(i));
                System.out.println(words.get(i));
            }
        }
        this.newWords = newWords;
    }

    public ArrayList<String> getNewWords(){
        return newWords;
    }

}