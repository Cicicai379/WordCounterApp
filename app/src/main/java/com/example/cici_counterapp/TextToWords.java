package com.example.cici_counterapp;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextToWords {
    private ArrayList<String> words;
    private String text;

    public TextToWords(AssetManager assetManager,String filename) {
        System.out.println("coverting to words.. ");
        try {
            InputStream inputStream = assetManager.open(filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.text+=line;
//                System.out.println(line);
            }
//            System.out.println("end");
//            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getWords() {
        return this.words;
    }

    public void convertWordsToList() {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean isWord = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(c);
                isWord = true;
            } else if (isWord && c == '\'') {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    words.add(sb.toString());
                    sb.setLength(0);
                }
                isWord = false;
            }
        }

        if (sb.length() > 0) {
            words.add(sb.toString());
        }

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            words.set(i, word.toLowerCase());
            System.out.println(word);

        }

        this.words = words;
    }
}