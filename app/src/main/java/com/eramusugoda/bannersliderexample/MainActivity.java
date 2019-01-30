package com.eramusugoda.bannersliderexample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eramusugoda.bannerslider.DefaultSliderAdapter;
import com.eramusugoda.bannerslider.DefaultSliderView;
import com.eramusugoda.bannerslider.IndicatorAnimations;
import com.eramusugoda.bannerslider.SliderAnimations;
import com.eramusugoda.bannerslider.SliderLayout;
import com.eramusugoda.bannerslider.SliderView;
import com.eramusugoda.bannerslider.events.OnSlideChangeListener;
import com.eramusugoda.bannerslider.events.OnSlideClickListener;
import com.eramusugoda.bannerslider.events.OnSliderImageReadyListener;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    SliderLayout mSliderLayout1;
    SliderLayout mSliderLayout2;

    DefaultSliderAdapter mAdapter1;
    DefaultSliderAdapter mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter1 = new DefaultSliderAdapter();
        mAdapter2 = new DefaultSliderAdapter();

        mSliderLayout1 = findViewById(R.id.imageSlider1);
        mSliderLayout1.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mSliderLayout1.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        mSliderLayout1.setScrollTimeInSec(5); //set scroll delay in seconds :
        mSliderLayout1.setAdapter(mAdapter1);
        mSliderLayout1.setOnSlideClickListener(new OnSlideClickListener() {
            @Override
            public void onSlideClick(SliderView sliderView) {
                Toast.makeText(MainActivity.this, "This is slider " + (mSliderLayout1.getPosition(sliderView) + 1), Toast.LENGTH_SHORT).show();
            }
        });
        mSliderLayout1.setOnSlideChangeListener(new OnSlideChangeListener() {
            @Override
            public void onSlideChange(int position) {
                Toast.makeText(MainActivity.this, "Slide Changed: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        mSliderLayout1.setOnSliderImageReadyListener(new OnSliderImageReadyListener() {
            @Override
            public void onSliderImageIsReady() {
                Toast.makeText(MainActivity.this, "Slider is ready", Toast.LENGTH_SHORT).show();
            }
        });

        mSliderLayout2 = findViewById(R.id.imageSlider2);
        mSliderLayout2.setAutoScrolling(false);
        mSliderLayout2.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mSliderLayout2.setSliderTransformAnimation(SliderAnimations.POPTRANSFORMATION);
        mSliderLayout2.setScrollTimeInSec(5); //set scroll delay in seconds :
        mSliderLayout2.setAdapter(mAdapter2);

        setSliderViews();

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderLayout2.getSliderPager().setCurrentItem(mSliderLayout2.getCurrentPagePosition() + 1, true);
            }
        });
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

                if (ii == 0) mAdapter1.addSliderView(sliderView);
                else {
                    sliderView.setHideShadow(true);
                    mAdapter2.addSliderView(sliderView);
                }
            }
        }
    }
}