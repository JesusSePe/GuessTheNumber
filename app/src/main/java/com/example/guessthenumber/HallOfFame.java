package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HallOfFame extends AppCompatActivity {
    static class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom){
            intents = _intents;
            nom = _nom;
        }
    }

    ArrayList<Record> records;

    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);
        // Get input data from previuous activity
        Intent i=getIntent();
        // Initialize model
        records = new ArrayList<Record>();
        // Add some examples
        records.add(new Record(0, "God"));
        records.add(new Record(Integer.parseInt(i.getStringExtra("attempts")), i.getStringExtra("uname")));
        records.add(new Record(1500, "Noob"));

        // Initialize the ArrayAdapter with its layout.
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };
        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);
    }
}