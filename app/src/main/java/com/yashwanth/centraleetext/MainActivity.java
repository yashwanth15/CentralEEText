package com.yashwanth.centraleetext;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mAdmin,mEmployee;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    progressDialog.setMessage("Verifying");
                    progressDialog.show();
                    final String userId=mAuth.getCurrentUser().getUid();
                    DatabaseReference userDB= FirebaseDatabase.getInstance().getReference().child("users");
                    userDB.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                    if (snapshot.getKey().equals(userId)){
                                        progressDialog.dismiss();
                                        if ((boolean)snapshot.getValue()){
                                            startActivity(new Intent(MainActivity.this,AdminActivity.class));
                                        }
                                        else{
                                            startActivity(new Intent(MainActivity.this,EmployeeActivity.class));
                                        }
                                        finish();
                                        return;
                                    }
                                }
                                mAuth.signOut();
                                Toast.makeText(MainActivity.this, "enter a employee mail id", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        };

        mAdmin=(Button)findViewById(R.id.adminButton);
        mEmployee=(Button)findViewById(R.id.employeeButton);

        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdminLogin.class));
            }
        });

        mEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EmployeeLogin.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
