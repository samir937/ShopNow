package com.example.shopnow;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {
    int n=1;
    SQLiteDatabase db1;
    SQLiteOpenHelper openHelper;

    Button BuyNow,Addtocart,plus,minus;
    TextView qtyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        qtyText=findViewById(R.id.qtyText);

        openHelper=new CartDatabase(this);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty= (String)qtyText.getText().toString();
                n=Integer.parseInt(qty);
                n=n+1;
                qty=Integer.toString(n);
                qtyText.setText(qty);

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty= (String) qtyText.getText().toString();
                n=Integer.parseInt(qty);
                if(n==1)
                {

                }

                else
                {
                    n=n-1;
                    qty=Integer.toString(n);
                    qtyText.setText(qty);

                }

            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextView priceTextview = (TextView) findViewById(R.id.priceText);
        TextView ProductName = (TextView) findViewById(R.id.ProductName1);

        final ImageView selectedImage = (ImageView) findViewById(R.id.productimage1);
        Intent intent = getIntent();

        final String name = getIntent().getStringExtra("name");

        final String price = getIntent().getStringExtra("price");

        selectedImage.setImageResource(intent.getIntExtra("image", 0));
        priceTextview.setText("Rs "+price+"/-");
        ProductName.setText(name);


        BuyNow = findViewById(R.id.BuyNowButton);
        Addtocart=findViewById(R.id.AddToCartButton);

        BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int productprice=Integer.parseInt(price);
                int finalAmount=n*productprice;
                String quantity=Integer.toString(n);
                String finalamount=Integer.toString(finalAmount);

                selectedImage.buildDrawingCache();
                Bitmap image= selectedImage.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                Intent intent=new Intent(ProductDetailsActivity.this,Product_Order_details.class);
                intent.putExtra("image",byteArray);
                intent.putExtra("Productprice",price);
                intent.putExtra("totalamount",finalamount);
                intent.putExtra("finalquantity",quantity);
                intent.putExtra("productname",name);
                startActivity(intent);

                    Toast.makeText(ProductDetailsActivity.this, "Item Purchased of " + price, Toast.LENGTH_SHORT).show();

            }
        });


        Addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db1=openHelper.getWritableDatabase();
                int productprice=Integer.parseInt(price);
                int finalAmount=n*productprice;
                String quantity=Integer.toString(n);
                String totalAmount=Integer.toString(finalAmount);
                ContentValues contentValues=new ContentValues();
                contentValues.put(CartDatabase.ProductName,name);
                contentValues.put(CartDatabase.ProductPrice,totalAmount);
                contentValues.put(CartDatabase.ProductQuantity,quantity);
                long id=db1.insert(CartDatabase.Table_Name,null,contentValues);

                Toast.makeText(ProductDetailsActivity.this,"Item Added to cart",Toast.LENGTH_SHORT).show();


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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
