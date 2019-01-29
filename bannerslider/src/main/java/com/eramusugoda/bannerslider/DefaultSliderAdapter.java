package com.eramusugoda.bannerslider;

import java.util.ArrayList;

public class DefaultSliderAdapter extends SliderAdapter {

    public DefaultSliderAdapter() {
        new DefaultSliderAdapter(null);
    }

    public DefaultSliderAdapter(ArrayList<SliderView> sliderViews) {
        super(sliderViews);
    }
}
