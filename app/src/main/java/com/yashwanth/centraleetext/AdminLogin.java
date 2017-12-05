package com.yashwanth.centraleetext;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    private EditText mEmail,mPassword;
    private Button mLogin;

    private FirebaseAuth mAuth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        mEmail=(EditText) findViewById(R.id.email);
        mPassword=(EditText) findViewById(R.id.password);
        mLogin=(Button)findViewById(R.id.login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Logging in");
                progressDialog.show();
                final String email=mEmail.getText().toString().trim();
                final String password=mPassword.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        final String userId=mAuth.getCurrentUser().getUid();
                        DatabaseReference userDB= FirebaseDatabase.getInstance().getReference().child("users");
                        userDB.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                        if (snapshot.getKey().equals(userId)&&(boolean)snapshot.getValue()){
                                            progressDialog.dismiss();
                                            startActivity(new Intent(AdminLogin.this,AdminActivity.class));
                                            finish();
                                            return;
                                        }
                                    }
                                    progressDialog.dismiss();
                                    mAuth.signOut();
                                    Toast.makeText(AdminLogin.this, "enter a employee mail id", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        });
    }
}
