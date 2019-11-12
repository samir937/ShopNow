package com.example.shopnow;

import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class gridProductlayoutAdapter extends BaseAdapter {

    List<HorizontalScrolModel> horizontalScrolModelList;

    public gridProductlayoutAdapter(List<HorizontalScrolModel> horizontalScrolModelList) {
        this.horizontalScrolModelList = horizontalScrolModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null)
        {
           view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
           view.setElevation(0);
           view.setBackgroundColor(Color.parseColor("#FFFFFF"));

            ImageView productImage=view.findViewById(R.id.image);
            TextView productTitle=view.findViewById(R.id.Pname);
            TextView productDescription=view.findViewById(R.id.itemlist_description);
            TextView productPrice=view.findViewById(R.id.product_price);

            productImage.setImageResource(horizontalScrolModelList.get(position).getProductImage());
            productTitle.setText(horizontalScrolModelList.get(position).getProductTitle());
            productDescription.setText(horizontalScrolModelList.get(position).getProductDescription());
            productPrice.setText(horizontalScrolModelList.get(position).getProductPrice());

        }
        else
        {
            view=convertView;
        }
        return view;
    }
}
