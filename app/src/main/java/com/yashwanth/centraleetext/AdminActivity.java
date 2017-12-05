package com.yashwanth.centraleetext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private Button mLogout;

    private FirebaseAuth mAuth;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAuth=FirebaseAuth.getInstance();

        mLogout=(Button)findViewById(R.id.logoutButton);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();

        for (int i=0;i<10;i++){
            ListItem listItem=new ListItem(
                    "heading " + (i+1),
                    "this is some text"
            );

            listItems.add(listItem);
        }

        adapter= new ListAdapter(listItems,this);

        recyclerView.setAdapter(adapter);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(AdminActivity.this,MainActivity.class));
                finish();
                return;
            }
        });
    }
}
