package com.simshospitals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class approval extends AppCompatActivity {

    Button approveBtn, rejectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);


        approveBtn = findViewById(R.id.approve_btn);
        rejectBtn = findViewById(R.id.reject_btn);

        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(approval.this, "Discount Approved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(approval.this, MainActivity.class));
                finish();
            }
        });
        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(approval.this, "Discount Rejected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(approval.this, MainActivity.class));
                finish();
            }
        });


    }
}
