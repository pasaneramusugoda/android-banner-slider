package com.eramusugoda.bannerslider;

import androidx.viewpager.widget.ViewPager;

import com.eramusugoda.bannerslider.events.OnSlideChangeListener;

class CircularSliderHandle implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private int mCurrentPosition;

    private OnSlideChangeListener mOnSlideChangeListener;

    CircularSliderHandle(final ViewPager viewPager) {
        mViewPager = viewPager;
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
        int currentPage = mViewPager.getCurrentItem();
        if (currentPage == mViewPager.getAdapter().getCount()-1 || currentPage == 0){
            int previousState = mCurrentPosition;
            mCurrentPosition = state;
            if (previousState == 1 && mCurrentPosition == 0){
                mViewPager.setCurrentItem(currentPage == 0 ? mViewPager.getAdapter().getCount()-1 : 0);
            }
        }
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
    }
}
