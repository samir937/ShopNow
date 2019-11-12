package com.example.shopnow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LoginDatabase extends SQLiteOpenHelper {

   public  static final  String Database_Name="LoginDatabase.db";
   public  static final  String Table_Name="LoginTable";
    public  static final  String col_1="ID";
    public  static final  String col_2="FirstName";
    public  static final  String col_3="LastName";
    public  static final  String col_4="Email";
    public  static final  String col_5="Password";


    public LoginDatabase(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ Table_Name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT ,Email TEXT ,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }
}
