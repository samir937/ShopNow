package com.example.shopnow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private EditText email,password;
    private Button login,signup;
    private CheckBox rememberCheckbox;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String mypreference = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.b12);
        signup=  findViewById(R.id.b2);
        email = findViewById(R.id.e1);
        password = findViewById(R.id.e2);
        rememberCheckbox=findViewById(R.id.rememberMecheckbox);

        sharedPreferences=getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        mEditor=sharedPreferences.edit();

        openHelper=new LoginDatabase(this);
        db=openHelper.getReadableDatabase();

        chechSharedPreference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                if (rememberCheckbox.isChecked())
                {
                    mEditor.putString(getString(R.string.checkbox),"True");
                    mEditor.commit();

                    String username=email.getText().toString();
                    mEditor.putString(getString(R.string.username),username);
                    mEditor.commit();

                    String passwords=password.getText().toString();
                    mEditor.putString(getString(R.string.passwords),passwords);
                    mEditor.commit();

                }
                else
                {
                    mEditor.putString(getString(R.string.checkbox),"False");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.username),"");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.passwords),"");
                    mEditor.commit();

                }

                cursor=db.rawQuery("SELECT * FROM "+ LoginDatabase.Table_Name+ " WHERE " + LoginDatabase.col_4 + "=? AND " + LoginDatabase.col_5 + "=?", new String[]{Email,Password});

                if(cursor!=null)
                {
                    if(cursor.getCount()>0)

                    {
                        cursor.moveToNext();
                        String user=cursor.getString(1);
                        Intent intent=new Intent(login.this,Shopfront.class);
                        intent.putExtra("username",user);
                        startActivity(intent);


                    }
                    else
                    {
                        Toast.makeText(login.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,SignUpPage.class);
                startActivity(intent);
            }
        });

    }

    private void chechSharedPreference()
    {
        String checkbox=sharedPreferences.getString(getString(R.string.checkbox),"false");
        String username=sharedPreferences.getString(getString(R.string.username),"");
        String passwordss=sharedPreferences.getString(getString(R.string.passwords),"");

        email.setText(username);
        password.setText(passwordss);

        if(checkbox.equals("True"))
        {
            rememberCheckbox.setChecked(true);
        }
        else
        {
            rememberCheckbox.setChecked(false);
        }
    }


}
