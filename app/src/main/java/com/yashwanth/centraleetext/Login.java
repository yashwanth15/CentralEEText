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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText mEmail,mPassword;
    private Button mLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);

        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    progressDialog.setMessage("Logging in...");
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
                                            startActivity(new Intent(Login.this,AdminActivity.class));
                                        }
                                        else{
                                            startActivity(new Intent(Login.this,EmployeeActivity.class));
                                        }
                                        finish();
                                        return;
                                    }
                                }
                                mAuth.signOut();
                                Toast.makeText(Login.this, "enter a employee mail id", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        };

        mEmail=(EditText) findViewById(R.id.email);
        mPassword=(EditText) findViewById(R.id.password);
        mLogin=(Button)findViewById(R.id.login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=mEmail.getText().toString().trim();
                final String password=mPassword.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });
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
