package com.example.shopnow;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Product_Order_details extends AppCompatActivity {

    private ImageView Pimage;

    TextView priceText,nameText,totalamount,totalItemprice,finalquantity;

    private String CHANNEL_ID="channel_1";
    private String channel_description = "PRODUCT DETAILS";
    private CharSequence channel_name = "my_Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__order_details);
        Pimage=findViewById(R.id.placedOrderImage);
        priceText=findViewById(R.id.placedOrderprice);
        nameText=findViewById(R.id.placed_order_name);
        totalamount=findViewById(R.id.cart_total_amount);
        totalItemprice=findViewById(R.id.total_item_price);
        finalquantity=findViewById(R.id.finalquantity);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Pimage.setImageBitmap(bmp);

        final String cartItemname = getIntent().getStringExtra("productname");
        final String CartItemprice = getIntent().getStringExtra("Productprice");
        final String finalamount = getIntent().getStringExtra("totalamount");

        nameText.setText(cartItemname);
        priceText.setText("Rs "+CartItemprice+"/-");
        totalItemprice.setText("Rs "+CartItemprice+"/-");
        totalamount.setText("Rs "+finalamount+"/-");
        finalquantity.setText(getIntent().getStringExtra("finalquantity"));



        createNotificationChannel();

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(Product_Order_details.this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_message_black_24dp)
                        .setContentTitle("Order Placed")
                        .setContentText("Your order of " + cartItemname + " is placed succesfully.")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);


        NotificationManagerCompat notificationManagerCompact= NotificationManagerCompat.from(Product_Order_details.this);
        notificationManagerCompact.notify(1,builder.build());



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
