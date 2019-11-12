package com.example.shopnow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpPage extends AppCompatActivity {

    EditText ufname,ulname,uemail,upass;
    Button B1;

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        ufname=findViewById(R.id.fname);
        ulname=findViewById(R.id.lname);
        uemail=findViewById(R.id.email);
        upass=findViewById(R.id.pass);
        B1=findViewById(R.id.b1);

        openHelper=new LoginDatabase(this);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 db=openHelper.getWritableDatabase();
                String fname=ufname.getText().toString();
                String lname=ulname.getText().toString();
                String emails=uemail.getText().toString();
                String password=upass.getText().toString();

                insertData(fname,lname,emails,password);

                Toast.makeText(SignUpPage.this,"Register Succesfully",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(SignUpPage.this,login.class);
                startActivity(intent);


            }
        });


    }


    public void insertData(String fname,String lname,String emails,String password)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(LoginDatabase.col_2,fname);
        contentValues.put(LoginDatabase.col_3,lname);
        contentValues.put(LoginDatabase.col_4,emails);
        contentValues.put(LoginDatabase.col_5,password);
        long id=db.insert(LoginDatabase.Table_Name,null,contentValues);
    }

}
