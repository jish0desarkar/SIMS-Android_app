package com.simshospitals;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.HashMap;

public class approval extends AppCompatActivity {

    TextView patientName, patientId, patientAge,patientAgeType, admitDate, authorisedBy, consultant, dischargeDate,
               requestedBy, billAmount, discount, reason;
    Button approveBtn, rejectBtn;
    HashMap<String,String>map;
    private static final String RECORD_URL = "http://103.249.207.47/sims/record.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
        Intent intent=getIntent();
       map= (HashMap<String, String>) intent.getSerializableExtra("detail");
     //   System.err.println(map.get("patientname"));
        approveBtn = findViewById(R.id.approve_btn);
        rejectBtn = findViewById(R.id.reject_btn);

        patientName = findViewById(R.id.patient_name);
        patientId = findViewById(R.id.patientid_text);
        patientAge = findViewById(R.id.patient_age);
        patientAgeType = findViewById(R.id.patient_agetype);
        admitDate = findViewById(R.id.patient_admitdate);
        authorisedBy = findViewById(R.id.authorized);
        consultant = findViewById(R.id.consultant);
        dischargeDate =findViewById(R.id.patient_dischargedate);
        requestedBy = findViewById(R.id.requestedby);
        billAmount  = findViewById(R.id.patient_billamount);
        reason = findViewById(R.id.reason);
        discount = findViewById(R.id.patient_discount);


        //Setting texts
        patientId.setText(map.get("ipid"));
        patientName.setText(map.get("patientname"));
        patientAge.setText(map.get("age"));
        patientAgeType.setText(map.get("agetype"));
        admitDate.setText(map.get("admitdatetime"));
        authorisedBy.setText(map.get("authorisedby"));
        consultant.setText(map.get("consultant"));
        dischargeDate.setText(map.get("dischargedatetime"));
        requestedBy.setText(map.get("requestedby"));
        billAmount.setText(map.get("billamount"));
        discount.setText(map.get("discount"));
        reason.setText(map.get("reason"));

        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("approval_status","approved");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                map.put("timestamp",timestamp.toString());
                userLogin();
            }
        });
        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("approval_status","rejected");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                map.put("timestamp",timestamp.toString());
                userLogin();

            }
        });


    }

    private void userLogin(){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(approval.this,"We welcome you back! ","Hold on.....",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                System.err.println(s);
                if(s.equals("success")){
                    Toast.makeText(approval.this,"Discount "+map.get("approval_status"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(approval.this, MainActivity.class);
                     finish();
                    startActivity(intent);

                }else{
                    Toast.makeText(approval.this,"Something Went wrong", Toast.LENGTH_LONG).show();
                    System.err.println(s);
                }
            }

            @Override
            protected String doInBackground(String... params) {

                PostingClass ruc = new PostingClass();

                String result = ruc.sendPostRequest(RECORD_URL,map);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute();
    }
}
