package com.example.nishan.donar_list;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.donar_list.Model.Donar;
import com.example.nishan.donar_list.adapter.ListDonarAdapter;

import java.util.List;

public class CallActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    String Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Email = intent.getStringExtra("Email");
        String PA = intent.getStringExtra("PA");
        String CA = intent.getStringExtra("CA");
        Phone = intent.getStringExtra("Phone");
        String BloodGRoup = intent.getStringExtra("BloodGroup");


        TextView tvName = (TextView) findViewById(R.id.tv_name);
        TextView tvEmail = (TextView) findViewById(R.id.tv_email);
        TextView tvPA = (TextView) findViewById(R.id.tv_permanent_address);
        TextView tvCA = (TextView) findViewById(R.id.tv_current_address);
        TextView tvPhone;
        tvPhone = (TextView) findViewById(R.id.tv_phone_number);
        TextView tvbloodGroup = (TextView) findViewById(R.id.tv_bloodgroup);

        Button btn_call = (Button) findViewById(R.id.btn_call);

        tvName.setText("Name: " + Name);
        tvEmail.setText("Email: " + Email);
        tvPA.setText("Permanent Address: " + PA);
        tvCA.setText("Current Address: " + CA);
        tvPhone.setText("Phone Number: " + Phone);
        tvbloodGroup.setText("Blood Group: " + BloodGRoup);


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(Phone));
                Toast.makeText(CallActivity.this, "Call is working"+Phone, Toast.LENGTH_SHORT).show();


                if (ActivityCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
              */

                makePhoneCall();

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CallActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {

            String dial = "tel: " + Phone;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

        }
    }
}
