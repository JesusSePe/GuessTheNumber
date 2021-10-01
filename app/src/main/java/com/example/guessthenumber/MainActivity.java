package com.example.guessthenumber;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Generate and save a random number between 1 and 100
        Random r = new Random();
        int toGuess = r.nextInt((100 - 1) + 1) + 1;
        Context context = getApplicationContext();
        EditText numberInput = findViewById(R.id.numberInput);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check button logic
        final Button button = findViewById(R.id.check);
        button.setOnClickListener(v -> {
            // onClick action

            // Get user input
            int userGuess = Integer.parseInt(numberInput.getText().toString());

            // Check if user guessed the number
            if (userGuess == toGuess) {
                CharSequence text = "You got it!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else if (userGuess > toGuess){
                CharSequence text = "Nope, my number is lower!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                CharSequence text = "Nope, my number is higher!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            numberInput.setText("");
        });
        numberInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (actionId == KeyEvent.KEYCODE_ENTER)){
                    button.performClick();
                }
                return false;
            }
        });
    }
}