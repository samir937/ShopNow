package com.example.shopnow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Myholder> {
        List<CartModel> cartModelArrayList;

        public CartAdapter(List<CartModel> cartModelArrayList) {
            this.cartModelArrayList = cartModelArrayList;
        }

        class Myholder extends RecyclerView.ViewHolder{
            TextView name,itemprice,itemquantity;

            public Myholder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.productName);
                itemprice = (TextView) itemView.findViewById(R.id.price_cartItem);
                itemquantity = (TextView) itemView.findViewById(R.id.Itemquantity);
            }
        }


        @Override
        public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlist_item_details,null);
            return new Myholder(view);

        }

    @Override
        public void onBindViewHolder(Myholder holder, int position) {
            CartModel dataModel=cartModelArrayList.get(position);
            holder.name.setText(dataModel.getName());
            holder.itemprice.setText(dataModel.getTotalAmount());
            holder.itemquantity.setText(dataModel.getQuantity());

        }

        @Override
        public int getItemCount() {
            return cartModelArrayList.size();
        }
}

