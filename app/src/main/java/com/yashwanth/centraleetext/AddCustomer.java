package com.yashwanth.centraleetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddCustomer extends AppCompatActivity {

    private EditText mName,mNumber,mAddress,mCompany;
    private Button mAdd;
    private TextView mDate;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        mName=(EditText)findViewById(R.id.name);
        mNumber=(EditText)findViewById(R.id.number);
        mAddress=(EditText)findViewById(R.id.address);
        mCompany=(EditText)findViewById(R.id.companyName);

        mDate=(TextView)findViewById(R.id.date);
        mAdd=(Button)findViewById(R.id.add);

        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        mDate.setText("Visit Date: "+date);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=mName.getText().toString().trim();
                final String number=mNumber.getText().toString().trim();
                final String address=mAddress.getText().toString().trim();
                final String companyName=mCompany.getText().toString().trim();
                if (name==""||number==""||address==""||companyName==""){
                    Toast.makeText(AddCustomer.this, "enter all the details", Toast.LENGTH_SHORT).show();
                }
                else{

                    DatabaseReference addCustomer= FirebaseDatabase.getInstance().getReference().child("visits").child(date).child(companyName);
                    //String customerId=addCustomer.push().getKey();

                    HashMap map=new HashMap();
                    map.put("name",name);
                    map.put("number",number);
                    map.put("address",address);
                    map.put("company_name",companyName);
                    map.put("date",date);
                    addCustomer.updateChildren(map);

                    Toast.makeText(AddCustomer.this, "Added!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
