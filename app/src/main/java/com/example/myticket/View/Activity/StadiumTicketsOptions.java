package com.example.myticket.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myticket.R;

public class StadiumTicketsOptions extends AppCompatActivity {
    private Spinner classSpinner;
    private Spinner placeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_tickets_options);
        findRefs();
    }

    private void findRefs() {
        classSpinner = findViewById(R.id.spinner_type);
        placeSpinner = findViewById(R.id.spinner_type_place);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.checked_text_spinner);
        classSpinner.setAdapter(adapter);


    }
}
