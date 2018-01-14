package com.example.nishan.donar_list;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nishan.donar_list.Model.Donar;
import com.example.nishan.donar_list.adapter.ListDonarAdapter;
import com.example.nishan.donar_list.database.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvDonar;
    private ListDonarAdapter adapter;
    private List<Donar> mDonarList;

    SearchView sv;

    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDonar = (ListView) findViewById(R.id.listview_donar);

        sv = (SearchView) findViewById(R.id.search);
        sv.setIconified(false);

        mDBHelper = new DatabaseHelper(this);

        //check exist database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //copy db
            if (copyDatabase(this)) {
                Toast.makeText(MainActivity.this, "DB successfully copied", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, "DB copying error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //get donar list in db when db exists
        mDonarList = mDBHelper.getListDonar();

        //init adapter
        adapter = new ListDonarAdapter(this, mDonarList);

        //set adapter for listview
        lvDonar.setAdapter(adapter);


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Donar> newList = new ArrayList<>();
                for (Donar donar : mDonarList) {
                    String Address = donar.getCurrentAddresss();
                    if (Address == null) {
                        Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Address.contains(newText)) {
                            newList.add(donar);
                        }
                    }
                }

                adapter.setFilter(newList);
                return true;
            }
        });


       /* lvDonar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Donar donar = mDonarList.get(position);
                String tel = donar.getPhoneNumber();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel" +tel));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
            }
        });
        */

        lvDonar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Donar item = (Donar) adapter.getItem(position);

                Intent intent = new Intent(getApplicationContext(),CallActivity.class);
                intent.putExtra("Name",item.getName());
                intent.putExtra("Email",item.getEmail());
                intent.putExtra("PA",item.getPermanentAddress());
                intent.putExtra("CA",item.getCurrentAddresss());
                intent.putExtra("Phone",item.getPhoneNumber());
                intent.putExtra("BloodGroup",item.getBloodGroup());

                startActivity(intent);
            }
        });
    }


    private boolean copyDatabase(Context context){
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DNAME);
            String outFilename = DatabaseHelper.DBLOCATION + DatabaseHelper.DNAME;
            OutputStream outputStream = new FileOutputStream(outFilename);
            byte[] buff = new byte[1024];

            int length = 0;
            while ((length = inputStream.read(buff)) > 0){
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();

            Toast.makeText(MainActivity.this, "DB copied", Toast.LENGTH_SHORT).show();

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
