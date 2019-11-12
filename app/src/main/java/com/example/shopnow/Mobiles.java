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

public class Mobiles extends AppCompatActivity {
    String[] ProductNames = {"Poco f1", "Redmi 6A", "Nokia 6", "Redmi 5A", "One plus 6t","Samsung j7 Pro","moto e4","one plus 6t"};
    String[] productprice = {"20999", "5999", "11999", "5999", "37999","15000", "7800", "37999"};
    int[] productImages = {R.drawable.pocof12, R.drawable.redmi6a,R.drawable.nokia6, R.drawable.redmi5a,R.drawable.motoe6plus1,R.drawable.samsungj7pro,R.drawable.motoe4,R.drawable.oneplus6t};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        actionBar.setTitle("Mobiles");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(Mobiles.this, ProductNames,productprice, productImages);
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

            CustomAdapter.MyViewHolder vh = new CustomAdapter.MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(CustomAdapter.MyViewHolder holder, final int position) {

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

                name  = itemView.findViewById(R.id.Pname);
                price = itemView.findViewById(R.id.product_price);
                image = itemView.findViewById(R.id.image);
            }
        }
    }

}
