package com.example.cici_counterapp;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.view.View;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.cici_counterapp.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssetManager assetManager = this.getApplicationContext().getAssets();

         binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

        binding.mostCommon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CharSequence inputText = binding.filename.getText();
                assert inputText != null;
                String input = inputText.toString();
                TextView textView = findViewById(R.id.textView);
                String filename = input+".txt";
                ArrayList<String> words = new ArrayList<>();
                TextToWords textToWord = new TextToWords(assetManager, filename);
                textToWord.convertWordsToList();
                words = textToWord.getWords();
                System.out.println(words);

                Check check = new Check(assetManager,words);
                check.storeEveryNewWord();
                words = check.getNewWords();

                System.out.println(words.get(1));
                Count counter = new Count(words);
                counter.calculateCnt();

                String word = counter.topOne();
                String s = "The most common word in the text file <b>\"" + input + "\"</b> is ";
                if (word.equals("its")) {
                    s += "<font color=\"purple\">\"it's\"</font>";
                } else {
                    s += "<font color=\"purple\">\"" + word + "\"</font>";
                }
                s += " with " + counter.getCntByWord(word) + " occurrences.";

                textView.setText(Html.fromHtml(s));
            }

        });

        binding.top5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CharSequence inputText = binding.filename.getText();
                assert inputText != null;
                String input = inputText.toString();
                TextView textView = findViewById(R.id.textView);
                String filename = input+".txt";
                ArrayList<String> words = new ArrayList<>();
                TextToWords textToWord = new TextToWords(assetManager, filename);
                textToWord.convertWordsToList();
                words = textToWord.getWords();
                System.out.println(words);

                Check check = new Check(assetManager,words);
                check.storeEveryNewWord();
                words = check.getNewWords();

                System.out.println(words.get(1));
                Count counter = new Count(words);
                counter.calculateCnt();

                String[] topOccurrences = counter.topFive();
                String s = "The top five most common words in the file <font color=\"purple\"><b>\"" + input + "\"</b></font> are<br>";
                for (int i = 0; i < 5; i++) {
                    if (topOccurrences[i].equals("its")) {
                        s += "<font color=\"purple\"><b>" + (i + 1) + ". \"it's\"</b></font>" + " with " +
                                counter.getCntByWord(topOccurrences[i]) + " occurrences<br>";
                    } else {
                        s += "<font color=\"purple\"><b>" + (i + 1) + ". \"" + topOccurrences[i] + "\"</b></font> with " +
                                counter.getCntByWord(topOccurrences[i]) + " occurrences<br>";
                    }
                }
                textView.setText(Html.fromHtml(s));
            }
        });
    }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}