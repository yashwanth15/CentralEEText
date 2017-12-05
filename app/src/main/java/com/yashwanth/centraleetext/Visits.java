package com.yashwanth.centraleetext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Visits extends AppCompatActivity {

    private Button mAddCustomer,mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits);

        mAddCustomer=(Button)findViewById(R.id.addCustomer);
        mHistory=(Button)findViewById(R.id.history);

        mAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Visits.this,AddCustomer.class));
            }
        });

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Visits.this,History.class));
            }
        });
    }
}
