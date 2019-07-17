package com.example.myticket.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myticket.R;
import com.example.myticket.View.Adapter.TicketsConfirmAdapter;

public class StadPaymentConfirm extends AppCompatActivity {

    private RecyclerView ticketsRV;
    private TicketsConfirmAdapter ticketsConfirmAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stad_payment_confirm);
        ticketsConfirmAdapter = new TicketsConfirmAdapter(this);
        ticketsRV = findViewById(R.id.confirm_tickets_rv);
        ticketsRV.setLayoutManager(new LinearLayoutManager(this));
        ticketsRV.setAdapter(ticketsConfirmAdapter);
    }
}
