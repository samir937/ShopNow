package com.example.shopnow;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {
    //  private ImageView Pimage;
    Button shopButton;
    // TextView priceText,nameText,totalamount;
    private String CHANNEL_ID = "channel_1";
    private String channel_description = "PRODUCT DETAILS";
    private CharSequence channel_name = "my_Channel";

    Cursor cursor;
    String arrr;
    int ar,total;

    CartDatabase db;

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    SQLiteDatabase db1;
    SQLiteOpenHelper openHelper;
    List<CartModel> cartModels;

    Button placeorderbutton;
    TextView finalamountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);


        placeorderbutton=findViewById(R.id.shopbutton);
        finalamountText=findViewById(R.id.amountText);


        cartModels = new ArrayList<CartModel>();
        recyclerView = (RecyclerView) findViewById(R.id.cartrecyclerview);


        openHelper = new CartDatabase(this);
        db1 = openHelper.getReadableDatabase();

        cursor = db1.rawQuery("SELECT * FROM " + CartDatabase.Table_Name, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {

                db = new CartDatabase(CartListActivity.this);
                cartModels = db.getdata();
                cartAdapter = new CartAdapter(cartModels);

                RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(reLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(cartAdapter);

                recyclerView.addOnItemTouchListener(new MyRecyclerItemClickListener(getApplicationContext(), recyclerView, new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Snackbar.make(view, "Long press to remove item from cart", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        arrr = cartModels.get(position).getId();
                        ar = Integer.parseInt(arrr);
                        final AlertDialog alertDialog = new AlertDialog.Builder(CartListActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setCancelable(false);
                        alertDialog.setMessage("Are you sure you want to remove this?");

                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CartDatabase databaseHelper = new CartDatabase(CartListActivity.this);
                                boolean trm = databaseHelper.deleteitem(ar);
                                if (trm) {
                                    alertDialog.dismiss();
                                    finish();
                                    startActivity(getIntent());
                                }
                            }
                        });
                        alertDialog.show();

                    }


                }));

            } else {
                Intent intent = new Intent(CartListActivity.this, EmptyActivty.class);
                startActivity(intent);

            }
        }




    ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null)

    {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
        actionBar.setTitle("Cart");

//setting total amount
        cursor = db1.rawQuery("SELECT SUM(" + CartDatabase.ProductPrice + ") as Total FROM " + CartDatabase.Table_Name, null);
        if (cursor.moveToFirst()) {
             total = cursor.getInt(cursor.getColumnIndex("Total"));
        }
        String totaltext=Integer.toString(total);
        finalamountText.setText("Rs "+totaltext+"/-");

////

       placeorderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartDatabase databaseHelper = new CartDatabase(CartListActivity.this);
                boolean trm = databaseHelper.deleteall();
                if (trm) {
                    finish();
                    Intent intent=new Intent(CartListActivity.this,Shopfront.class);
                    startActivity(intent);
                }
                createNotificationChannel();

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(CartListActivity.this,CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_message_black_24dp)
                                .setContentTitle("Order Placed")
                                .setContentText("Your order is placed succesfully.Thank You for Shopping....")
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setAutoCancel(true);

                NotificationManagerCompat notificationManagerCompact= NotificationManagerCompat.from(CartListActivity.this);
                notificationManagerCompact.notify(1,builder.build());



            }
        });
}





    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

  public void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

    }


}