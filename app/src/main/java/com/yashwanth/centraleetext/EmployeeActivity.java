package com.yashwanth.centraleetext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeActivity extends AppCompatActivity {

    private Button mLogout;
    private ImageButton mVisits;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        mAuth=FirebaseAuth.getInstance();

        mLogout=(Button)findViewById(R.id.logoutButton);

        mVisits=(ImageButton)findViewById(R.id.visits);

        mVisits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeActivity.this,Visits.class));
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(EmployeeActivity.this,Login.class));
                finish();
                return;
            }
        });
    }
}
