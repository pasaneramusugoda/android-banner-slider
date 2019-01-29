package com.eramusugoda.bannerslider;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public abstract class SliderAdapter extends PagerAdapter {

    private ArrayList<SliderView> sliderViews;

    public SliderAdapter() {
    }

    public SliderAdapter(ArrayList<SliderView> sliderViews) {
        this.sliderViews = sliderViews;
    }

    public ArrayList<SliderView> getSliderViews() {
        return sliderViews;
    }

    public void addSliderView(SliderView view) {
        if (sliderViews == null)
            sliderViews = new ArrayList<>();
        sliderViews.add(view);
        notifyDataSetChanged();
    }

    public void addSliderViews(ArrayList<SliderView> sliderViews) {
        this.sliderViews = sliderViews;
        notifyDataSetChanged();
    }

    public void removeAllSliderViews() {
        if (sliderViews == null)
            return;

        sliderViews.clear();
        notifyDataSetChanged();
    }

    public SliderView getSliderView(int position) {
        if (sliderViews == null || sliderViews.isEmpty() || position >= sliderViews.size()) {
            return null;
        }
        return sliderViews.get(position);
    }

    @Override
    public int getCount() {
        return sliderViews == null ? 0 : sliderViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SliderView imageSliderView = sliderViews.get(position);
        View v = imageSliderView.getView();
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
