package com.example.shopnow;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class CartDatabase extends SQLiteOpenHelper {
    public static final String Database_Name = "CartItemDatabase.db";
    public static final String Table_Name = "CartItem_Table";
    public static final String ID = "id";
    public static final String ProductName = "ProductName";
    public static final String ProductPrice = "ProductPrice";
    public static final String ProductQuantity = "Quantity";


    public CartDatabase(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
        db1.execSQL("CREATE TABLE " + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,ProductName TEXT,ProductPrice TEXT ,Quantity TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {
        db1.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db1);

    }


    public List<CartModel> getdata() {
        List<CartModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Table_Name + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        CartModel cartModel = null;
        while (cursor.moveToNext()) {
            cartModel = new CartModel();
            String id= cursor.getString(cursor.getColumnIndexOrThrow("ID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("ProductName"));
            String finalprice = cursor.getString(cursor.getColumnIndexOrThrow("ProductPrice"));
            String quantity = cursor.getString(cursor.getColumnIndexOrThrow("Quantity"));
            cartModel.setId(id);
            cartModel.setName(name);
            cartModel.setTotalAmount(finalprice);
            cartModel.setQuantity(quantity);
            stringBuffer.append(cartModel);
            data.add(cartModel);
        }

        return data;

    }
    public boolean deleteitem(int id){

        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.execSQL("delete from "+Table_Name+" where "+ID+" ="+id);
        return  true;
    }

    public boolean deleteall()
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.execSQL("delete from "+Table_Name);
        return  true;
    }
}

