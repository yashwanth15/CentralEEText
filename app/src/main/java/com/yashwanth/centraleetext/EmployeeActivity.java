package com.yashwanth.centraleetext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeActivity extends AppCompatActivity {

    private Button mLogout;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        mAuth=FirebaseAuth.getInstance();

        mLogout=(Button)findViewById(R.id.logoutButton);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(EmployeeActivity.this,MainActivity.class));
                finish();
                return;
            }
        });
    }
}
