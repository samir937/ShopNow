package com.example.shopnow;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class sliderAdapter extends PagerAdapter {

   private List<sliderModel> sliderModelList;

   public  sliderAdapter(List<sliderModel> sliderModelList)
   {
       this.sliderModelList=sliderModelList;
   }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       View view= LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout,container,false);
        ImageView banner=view.findViewById(R.id.slider_banner);
        banner.setImageResource(sliderModelList.get(position).getBanner());
        container.addView(view,0);
        return view ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }
}
