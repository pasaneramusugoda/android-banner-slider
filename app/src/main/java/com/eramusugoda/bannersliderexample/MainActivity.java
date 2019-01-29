package com.eramusugoda.bannersliderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.eramusugoda.bannerslider.DefaultSliderView;
import com.eramusugoda.bannerslider.IndicatorAnimations;
import com.eramusugoda.bannerslider.SliderAdapter;
import com.eramusugoda.bannerslider.SliderAnimations;
import com.eramusugoda.bannerslider.SliderLayout;
import com.eramusugoda.bannerslider.SliderView;


public class MainActivity extends AppCompatActivity {

    SliderLayout mSliderLayout1;
    SliderLayout mSliderLayout2;

    SliderAdapter1 mAdapter1;
    SliderAdapter2 mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter1 = new SliderAdapter1();
        mAdapter2 = new SliderAdapter2();

        mSliderLayout1 = findViewById(R.id.imageSlider1);
        mSliderLayout1.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mSliderLayout1.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        mSliderLayout1.setScrollTimeInSec(5); //set scroll delay in seconds :
        mSliderLayout1.setAdapter(mAdapter1);

        mSliderLayout2 = findViewById(R.id.imageSlider2);
        mSliderLayout2.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mSliderLayout2.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        mSliderLayout2.setScrollTimeInSec(5); //set scroll delay in seconds :
        mSliderLayout2.setAdapter(mAdapter2);

        setSliderViews();
    }

    private void setSliderViews() {
        for (int ii = 0; ii < 2; ii++) {
            for (int i = 0; i <= 3; i++) {

                DefaultSliderView sliderView = new DefaultSliderView(this);

                switch (i) {
                    case 0:
                        sliderView.setImageDrawable(R.drawable.ic_launcher_background);
                        break;
                    case 1:
                        sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                        break;
                    case 2:
                        sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
                        break;
                    case 3:
                        sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                        break;
                }

                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                sliderView.setDescription("The quick brown fox jumps over the lazy dog.\n" +
                        "Jackdaws love my big sphinx of quartz. " + (i + 1));
                final int finalI = i;

                sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(SliderView sliderView) {
                        Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

                    }
                });

                //at last add this view in your layout :
//                if (ii == 0) mSliderLayout1.addSliderView(sliderView);
//                else mSliderLayout1.addSliderView(sliderView);

                if (ii == 0) mAdapter1.addSliderView(sliderView);
                else mAdapter2.addSliderView(sliderView);
            }
        }
    }
}

class SliderAdapter1 extends SliderAdapter {
    SliderAdapter1() {

    }
}

class SliderAdapter2 extends SliderAdapter {
    SliderAdapter2() {

    }
}