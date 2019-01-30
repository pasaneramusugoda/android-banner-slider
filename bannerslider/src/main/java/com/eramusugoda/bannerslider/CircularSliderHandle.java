package com.eramusugoda.bannerslider;

import androidx.viewpager.widget.ViewPager;

import com.eramusugoda.bannerslider.events.OnSlideChangeListener;

class CircularSliderHandle implements ViewPager.OnPageChangeListener {
    private static final String TAG = CircularSliderHandle.class.getSimpleName();
    private int mCurrentPosition;

    private OnSlideChangeListener mOnSlideChangeListener;

    CircularSliderHandle() {
    }

    public void setOnSlideChangeListener(OnSlideChangeListener onSlideChangeListener) {
        mOnSlideChangeListener = onSlideChangeListener;
    }

    @Override
    public void onPageSelected(final int position) {
        mCurrentPosition = position;
        mOnSlideChangeListener.onSlideChange(mCurrentPosition);
    }

    @Override
    public void onPageScrollStateChanged(final int state) {
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
    }
}
