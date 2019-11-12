package com.example.shopnow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class LifeStyles extends AppCompatActivity {

    String[] ProductNames = {"Banarasi Saree", "Embroidari Saree", "Kurti", "Mens Blazzer", "Nike Cap","Party Denim Shirt","Men's kurta","Nike Track Suit","Nike Sport Shoe","Lehnga Choli"};
    String[] productprice = {"1599", "1499", "660", "1799", "1195","899", "2000", "4692","1697","1198"};
    int[] productImages = {R.drawable.banarasisaree, R.drawable.embroidarisaree,R.drawable.kurti, R.drawable.blazzer1,R.drawable.nikecap,R.drawable.denim_shirt,R.drawable.wedding_costume,R.drawable.niketracksuit,R.drawable.nikesportshoe,R.drawable.lehngacholi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(LifeStyles.this, ProductNames,productprice, productImages);
        recyclerView.setAdapter(customAdapter);
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



    public  class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
        private String[] productprice,ProductNames;
        int[] productImages;
        Context context;

        public CustomAdapter(Context context, String[] ProductNames,String[] productprice, int[] productImages) {
            this.context = context;
            this.ProductNames=ProductNames;
            this.productprice = productprice;
            this.productImages = productImages;
        }
        @Override
        public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder,final int position) {

            holder.name.setText(ProductNames[position]);
            holder.price.setText(productprice[position]);
            holder.image.setImageResource(productImages[position]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ProductDetailsActivity.class);
                    intent.putExtra("name",ProductNames[position]);
                    intent.putExtra("price",productprice[position]);
                    intent.putExtra("image", productImages[position]);
                    context.startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return productprice.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView price,name;
            ImageView image;

            public MyViewHolder(View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.Pname);
                price =  itemView.findViewById(R.id.product_price);
                image = itemView.findViewById(R.id.image);
            }
        }
    }

}