package com.example.shopnow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder> {

    private List<HorizontalScrolModel> horizontalScrolModelList;

    public HorizontalProductAdapter(List<HorizontalScrolModel> horizontalScrolModelList)
    {
        this.horizontalScrolModelList=horizontalScrolModelList;
    }

    @ NonNull
    @Override
    public HorizontalProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductAdapter.ViewHolder viewHolder, int position)
    {
        int resource=horizontalScrolModelList.get(position).getProductImage();
        String title=horizontalScrolModelList.get(position).getProductTitle();
        String description=horizontalScrolModelList.get(position).getProductDescription();
        String price=horizontalScrolModelList.get(position).getProductPrice();

        viewHolder.setProductImage(resource);
        viewHolder.setProductTitle(title);
        viewHolder.setProductDescription(description);
        viewHolder.setProuctprice(price);

    }

    @Override
    public int getItemCount() {
        return horizontalScrolModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView productImage;
        private TextView productprice,productTitle,productDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage=itemView.findViewById(R.id.image);
            productTitle=itemView.findViewById(R.id.Pname);
            productDescription=itemView.findViewById(R.id.itemlist_description);
            productprice=itemView.findViewById(R.id.product_price);
        }

        private void setProductImage(int resource)
        {
           productImage.setImageResource(resource);
        }

        private void setProductTitle(String title)
        {
            productTitle.setText(title);
        }

        private void setProductDescription(String description)
        {
            productDescription.setText(description);
        }

        private void setProuctprice(String price)
        {
            productprice.setText(price);

        }
    }


}

