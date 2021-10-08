package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HallOfFame extends AppCompatActivity {
    String[][]  ranking = {{"God", "0"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);
        for (String user[]: ranking) {
            System.out.println("Username: " + user[0] + "Attempts: " + user[1] );
        }
    }
}