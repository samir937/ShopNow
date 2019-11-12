package com.example.shopnow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {



    public HomeFragment() {

    }

    private RecyclerView categoryRecyclerView;

    /// banner slider
    private ViewPager bannerSliderViewpager;
    private  List<sliderModel> sliderModelList;
    private  int currentpage=2;
    private Timer timer;
    final private long DELAY_TIME=3000;
    final private long Period_TIME=3000;

    //



    //horizontal scroll
    private TextView horizontallayoutTitle;
    private Button horizontal_scroll_viewAllbutton;
    private RecyclerView horizontalRecyclerView;

    //


    //
        private Button grid_viewAllButton;
    //

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        categoryRecyclerView=view.findViewById(R.id.catergory_recyclerview);

        // banner Slider
            bannerSliderViewpager=view.findViewById(R.id.slider_banner_viewPager);
            sliderModelList=new ArrayList<sliderModel>();

            sliderModelList.add(new sliderModel(R.drawable.image4));
            sliderModelList.add(new sliderModel(R.drawable.addlaptop));

            sliderModelList.add(new sliderModel(R.drawable.adiphone));
            sliderModelList.add(new sliderModel(R.drawable.image2));
            sliderModelList.add(new sliderModel(R.drawable.image3));
            sliderModelList.add(new sliderModel(R.drawable.image4));
            sliderModelList.add(new sliderModel(R.drawable.addlaptop));

            sliderModelList.add(new sliderModel(R.drawable.adiphone));
            sliderModelList.add(new sliderModel(R.drawable.image2));

            sliderAdapter sliderAdapter=new sliderAdapter(sliderModelList);
            bannerSliderViewpager.setAdapter(sliderAdapter);

            bannerSliderViewpager.setClipToPadding(false);
            bannerSliderViewpager.setPageMargin(20);
            ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    currentpage=i;

                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if(i==ViewPager.SCROLL_STATE_IDLE)
                    {
                        pagelooper();
                    }

                }
            };

            bannerSliderViewpager.addOnPageChangeListener(onPageChangeListener);
            startbannerSlideshow();
            bannerSliderViewpager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pagelooper();
                    stopbannerSlideShow();
                    if(event.getAction()==MotionEvent.ACTION_UP)
                    {
                        startbannerSlideshow();
                    }

                    return false;
                }
            });

        //


        //horizontal scroll view
        horizontallayoutTitle=view.findViewById(R.id.horizontal_scroll_textView);
        horizontal_scroll_viewAllbutton=view.findViewById(R.id.horizontal_scroll_button);
        horizontal_scroll_viewAllbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Mobiles.class);
                startActivity(intent);
            }
        });
        horizontalRecyclerView=view.findViewById(R.id.horizontal_scroll_recyclerview);


        List<HorizontalScrolModel>horizontalScrolModelList=new ArrayList<>();

        horizontalScrolModelList.add(new HorizontalScrolModel(R.drawable.redmi5a,"Redmi 5A","SD 625 Processor","Rs 5999/-"));
        horizontalScrolModelList.add(new HorizontalScrolModel(R.drawable.motoe6plus1,"Moto E6plus","SD 660 Processor","Rs 21999/-"));
        horizontalScrolModelList.add(new HorizontalScrolModel(R.drawable.pocof1,"Poco F1","SD 845 Processor","Rs 20999/-"));
        horizontalScrolModelList.add(new HorizontalScrolModel(R.drawable.mia2,"Mi A2","SD 660 Processor","Rs 11999/-"));
        horizontalScrolModelList.add(new HorizontalScrolModel(R.drawable.motoe4,"Moto E4","SD 636 Processor","Rs 14999/-"));
        horizontalScrolModelList.add(new HorizontalScrolModel(R.drawable.samsungj7pro,"Samsung j7","SD 425 Processor","Rs 15999/-"));
        horizontalScrolModelList.add(new HorizontalScrolModel(R.drawable.nokia6,"Nokia 6s","SD 660 Processor","Rs 10999/-"));

        HorizontalProductAdapter horizontalProductAdapter=new HorizontalProductAdapter(horizontalScrolModelList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductAdapter);
        horizontalProductAdapter.notifyDataSetChanged();

        //


        //gridLayout
        TextView gridLayoutTextview=view.findViewById(R.id.grid_layout_textview);
        Button  gridLayoutButton=view.findViewById(R.id.grid_layout_button);
        GridView gridView=view.findViewById(R.id.grid_layout_gridview);


        List<HorizontalScrolModel> horizontalScrolModelList2=new ArrayList<>();

        horizontalScrolModelList2.add(new HorizontalScrolModel(R.drawable.denim_shirt,"Men Party Denim ","Slim fit Shirt","Rs 899/-"));
        horizontalScrolModelList2.add(new HorizontalScrolModel(R.drawable.blazzer1,"Men's Blazzer","Cotton blended","Rs 1799/-"));
        horizontalScrolModelList2.add(new HorizontalScrolModel(R.drawable.slim_jeans,"Levi's Men's  Jeans","Slim fit jeans","Rs 2799/-"));
        horizontalScrolModelList2.add(new HorizontalScrolModel(R.drawable.wedding_costume,"Men's kurta ","Cotton wedding wear","Rs 2000/-"));

        gridView.setAdapter(new gridProductlayoutAdapter(horizontalScrolModelList2));
        grid_viewAllButton=view.findViewById(R.id.grid_layout_button);
        grid_viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LifeStyles.class);
                startActivity(intent);
            }
        });
        //
        return view;
    }

    private void pagelooper()
    {
        if(currentpage==sliderModelList.size()-2)
        {
            currentpage=2;
            bannerSliderViewpager.setCurrentItem(currentpage,false);
        }

        if(currentpage==1)
        {
            currentpage=sliderModelList.size()-3;
            bannerSliderViewpager.setCurrentItem(currentpage,false);
        }

    }

    private  void startbannerSlideshow()
    {
        final Handler handler=new Handler();
         final Runnable update= new Runnable() {
            @Override
            public void run() {
                if(currentpage>=sliderModelList.size())
                {
                    currentpage=1;
                }
                bannerSliderViewpager.setCurrentItem(currentpage++,true);
            }
        };

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }  ,DELAY_TIME,Period_TIME
        );


    }

    private void stopbannerSlideShow()

    {
        timer.cancel();

    }

}
