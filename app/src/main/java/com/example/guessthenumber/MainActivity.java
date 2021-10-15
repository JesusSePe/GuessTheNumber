package com.example.guessthenumber;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Generate and save a random number between 1 and 100
        Random r = new Random();
        int toGuess = r.nextInt((100 - 1) + 1) + 1;

        final EditText input = new EditText(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check button logic
        final Button button = findViewById(R.id.check);
        button.setOnClickListener(v -> {
            // onClick action

            // Get user input
            Context context = getApplicationContext();
            EditText numI = findViewById(R.id.numberInput);
            TextView att = findViewById(R.id.attempts);
            int userGuess;

            if (Integer.parseInt(numI.getText().toString()) >= 0) {
                userGuess = Integer.parseInt(numI.getText().toString());
            } else {
                userGuess = 0;
            }


            // Check if user guessed the number
            if (userGuess == toGuess) {
                CharSequence text = "You got it!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                new AlertDialog.Builder(this)
                        .setTitle("You won!")
                        .setMessage("Do you want to save your record?")
                        .setView(input)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String uname = input.getText().toString();
                                sendMessage(v, uname, att.getText().toString().split(": ")[1]);
                                /*Intent i = new Intent(MainActivity.this, MainActivity.class);
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(i);
                                overridePendingTransition(0, 0);*/
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(MainActivity.this, MainActivity.class);
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(i);
                                overridePendingTransition(0, 0);
                            }
                        }).show();
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
            numI.setText("");
            String[] attempts = att.getText().toString().split(": ");


            att.setText(new StringBuilder().append("Attempt: ").append(Integer.parseInt(attempts[1])+1).toString());
        });

    }

    public void sendMessage(View view, String uname, String attempts) {
        Intent intent = new Intent(this, HallOfFame.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();
        intent.putExtra("uname", uname);
        intent.putExtra("attempts", attempts);
        startActivity(intent);
    }
}