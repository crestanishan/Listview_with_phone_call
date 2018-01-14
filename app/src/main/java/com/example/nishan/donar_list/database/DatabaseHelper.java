package com.example.nishan.donar_list.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nishan.donar_list.Model.Donar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishan on 12/21/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public  static final String DNAME = "Donar.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.nishan.donar_list/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

     public DatabaseHelper(Context context){
         super(context, DNAME, null, 1);
         this.mContext = context;

     }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()){

            return;
        }
      mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if (mDatabase != null){
            mDatabase.close();
        }

    }

    public List<Donar> getListDonar(){
        Donar donar = null;
        List<Donar> donarList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM  Donar", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            donar = new Donar(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            donarList.add(donar);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return donarList;
    }
}
