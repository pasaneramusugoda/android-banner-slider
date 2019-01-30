package com.eramusugoda.bannerslider;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.viewpager.widget.ViewPager;

import com.eramusugoda.bannerslider.events.OnSlideChangeListener;
import com.eramusugoda.bannerslider.events.OnSlideClickListener;
import com.eramusugoda.bannerslider.events.OnSliderImageReadyListener;
import com.eramusugoda.bannerslider.indicator.PageIndicatorView;
import com.eramusugoda.bannerslider.indicator.animation.type.AnimationType;
import com.eramusugoda.bannerslider.transformations.AntiClockSpinTransformation;
import com.eramusugoda.bannerslider.transformations.Clock_SpinTransformation;
import com.eramusugoda.bannerslider.transformations.CubeInDepthTransformation;
import com.eramusugoda.bannerslider.transformations.CubeInRotationTransformation;
import com.eramusugoda.bannerslider.transformations.CubeInScalingTransformation;
import com.eramusugoda.bannerslider.transformations.CubeOutDepthTransformation;
import com.eramusugoda.bannerslider.transformations.CubeOutRotationTransformation;
import com.eramusugoda.bannerslider.transformations.CubeOutScalingTransformation;
import com.eramusugoda.bannerslider.transformations.DepthTransformation;
import com.eramusugoda.bannerslider.transformations.FadeTransformation;
import com.eramusugoda.bannerslider.transformations.FanTransformation;
import com.eramusugoda.bannerslider.transformations.FidgetSpinTransformation;
import com.eramusugoda.bannerslider.transformations.GateTransformation;
import com.eramusugoda.bannerslider.transformations.HingeTransformation;
import com.eramusugoda.bannerslider.transformations.HorizontalFlipTransformation;
import com.eramusugoda.bannerslider.transformations.PopTransformation;
import com.eramusugoda.bannerslider.transformations.SimpleTransformation;
import com.eramusugoda.bannerslider.transformations.SpinnerTransformation;
import com.eramusugoda.bannerslider.transformations.TossTransformation;
import com.eramusugoda.bannerslider.transformations.VerticalFlipTransformation;
import com.eramusugoda.bannerslider.transformations.VerticalShutTransformation;
import com.eramusugoda.bannerslider.transformations.ZoomOutTransformation;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Pasan
 * @since 01/30/19
 */

public class SliderLayout extends FrameLayout implements OnSlideChangeListener {

    private static final String TAG = SliderLayout.class.getSimpleName();
    private static final long DELAY_MS = 500;
    private SliderAdapter mSliderAdapter;
    private int currentPage = 0;
    private ViewPager mSliderPager;
    private PageIndicatorView pagerIndicator;
    private int scrollTimeInSec = 2;
    private Handler handler = new Handler();
    private Timer flippingTimer;
    private boolean autoScrolling = true;
    private OnSlideChangeListener onSlideChangeListener;
    private OnSlideClickListener onSlideClickListener;
    private OnSliderImageReadyListener onSliderImageReadyListener;
    private boolean notifyOnce = false;

    public SliderLayout(Context context) {
        super(context);
        setLayout(context);
    }

    public SliderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout(context);
    }

    public SliderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayout(context);
    }

    public void setOnSliderImageReadyListener(OnSliderImageReadyListener onSliderImageReadyListener) {
        setOnSliderImageReadyListener(onSliderImageReadyListener, false);
    }

    /**
     * @param onSliderImageReadyListener will fire when one of provided images are ready
     * @param notifyOnce                 default value is false
     */
    public void setOnSliderImageReadyListener(OnSliderImageReadyListener onSliderImageReadyListener, boolean notifyOnce) {
        this.onSliderImageReadyListener = onSliderImageReadyListener;
        this.notifyOnce = notifyOnce;

        if (mSliderAdapter != null)
            mSliderAdapter.setSliderImageReadyListener(onSliderImageReadyListener, notifyOnce);
    }

    public void setOnSlideChangeListener(OnSlideChangeListener onSlideChangeListener) {
        this.onSlideChangeListener = onSlideChangeListener;
    }

    public void setOnSlideClickListener(OnSlideClickListener onSlideClickListener) {
        this.onSlideClickListener = onSlideClickListener;

        if (mSliderAdapter != null)
            mSliderAdapter.setSlideClickListener(onSlideClickListener);
    }

    public boolean isAutoScrolling() {
        return autoScrolling;
    }

    public void setAutoScrolling(boolean autoScrolling) {
        this.autoScrolling = autoScrolling;
        startAutoCycle();
    }

    public int getScrollTimeInSec() {
        return scrollTimeInSec;
    }

    /**
     * @param time default value is 2sec
     */
    public void setScrollTimeInSec(int time) {
        scrollTimeInSec = time;
        startAutoCycle();
    }

    public void setSliderTransformAnimation(SliderAnimations animation) {

        switch (animation) {
            case ANTICLOCKSPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new AntiClockSpinTransformation());
                break;
            case CLOCK_SPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new Clock_SpinTransformation());
                break;
            case CUBEINDEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInDepthTransformation());
                break;
            case CUBEINROTATIONTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInRotationTransformation());
                break;
            case CUBEINSCALINGTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInScalingTransformation());
                break;
            case CUBEOUTDEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutDepthTransformation());
                break;
            case CUBEOUTROTATIONTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutRotationTransformation());
                break;
            case CUBEOUTSCALINGTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutScalingTransformation());
                break;
            case DEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new DepthTransformation());
                break;
            case FADETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FadeTransformation());
                break;
            case FANTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FanTransformation());
                break;
            case FIDGETSPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FidgetSpinTransformation());
                break;
            case GATETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new GateTransformation());
                break;
            case HINGETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new HingeTransformation());
                break;
            case HORIZONTALFLIPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new HorizontalFlipTransformation());
                break;
            case POPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new PopTransformation());
                break;
            case SIMPLETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new SimpleTransformation());
                break;
            case SPINNERTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new SpinnerTransformation());
                break;
            case TOSSTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new TossTransformation());
                break;
            case VERTICALFLIPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new VerticalFlipTransformation());
                break;
            case VERTICALSHUTTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new VerticalShutTransformation());
                break;
            case ZOOMOUTTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new ZoomOutTransformation());
                break;
            default:
                mSliderPager.setPageTransformer(false, new SimpleTransformation());

        }

    }

    public void setCustomSliderTransformAnimation(ViewPager.PageTransformer animation) {
        mSliderPager.setPageTransformer(false, animation);
    }

    public int getCurrentPagePosition() {
        if (mSliderAdapter != null) {
            int item = mSliderPager.getCurrentItem() % mSliderAdapter.getCount();
            Log.d(TAG, "getCurrentPagePosition: " + item);
            return item;
        } else {
            throw new NullPointerException("Adapter not set");
        }
    }

    public void setIndicatorAnimation(IndicatorAnimations animations) {
        switch (animations) {
            case DROP:
                pagerIndicator.setAnimationType(AnimationType.DROP);
                break;
            case FILL:
                pagerIndicator.setAnimationType(AnimationType.FILL);
                break;
            case NONE:
                pagerIndicator.setAnimationType(AnimationType.NONE);
                break;
            case SWAP:
                pagerIndicator.setAnimationType(AnimationType.SWAP);
                break;
            case WORM:
                pagerIndicator.setAnimationType(AnimationType.WORM);
                break;
            case COLOR:
                pagerIndicator.setAnimationType(AnimationType.COLOR);
                break;
            case SCALE:
                pagerIndicator.setAnimationType(AnimationType.SCALE);
                break;
            case SLIDE:
                pagerIndicator.setAnimationType(AnimationType.SLIDE);
                break;
            case SCALE_DOWN:
                pagerIndicator.setAnimationType(AnimationType.SCALE_DOWN);
                break;
            case THIN_WORM:
                pagerIndicator.setAnimationType(AnimationType.THIN_WORM);
                break;
        }
    }

    public void setPagerIndicatorVisibility(boolean visibility) {
        if (visibility) {
            pagerIndicator.setVisibility(VISIBLE);
        } else {
            pagerIndicator.setVisibility(GONE);
        }
    }

    private void setLayout(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_layout, this, true);
        mSliderPager = view.findViewById(R.id.vp_slider_layout);
        pagerIndicator = view.findViewById(R.id.pager_indicator);
        pagerIndicator.setDynamicCount(true);

        // Handler for onPageChangeListener
        CircularSliderHandle circularSliderHandle = new CircularSliderHandle();
        circularSliderHandle.setOnSlideChangeListener(this);

        mSliderPager.addOnPageChangeListener(circularSliderHandle);
    }

    public void setAdapter(SliderAdapter adapter) {
        mSliderAdapter = adapter == null ? new DefaultSliderAdapter() : adapter;
        mSliderAdapter.setSliderImageReadyListener(onSliderImageReadyListener, notifyOnce);
        mSliderAdapter.setSlideClickListener(onSlideClickListener);

        mSliderPager.setAdapter(mSliderAdapter);
        if (pagerIndicator != null && mSliderPager != null) {
            pagerIndicator.setViewPager(mSliderPager);
        }
        //Starting auto cycle at the time of setting up of layout
        startAutoCycle();
    }

    private void startAutoCycle() {

        if (flippingTimer != null) {
            flippingTimer.cancel();
            flippingTimer.purge();
        }

        if (!autoScrolling) return;

        //Cancel If Thread is Running
        final Runnable scrollingThread = new Runnable() {
            public void run() {
                if (mSliderAdapter == null || currentPage == mSliderAdapter.getCount()) {
                    currentPage = 0;
                }
                // true set for smooth transition between pager
                mSliderPager.setCurrentItem(currentPage++, true);
            }

        };

        flippingTimer = new Timer();
        flippingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(scrollingThread);
            }
        }, DELAY_MS, scrollTimeInSec * 1000);
    }

    @Override
    public void onSlideChange(int position) {
        currentPage = position;
        if (onSlideChangeListener != null)
            onSlideChangeListener.onSlideChange(currentPage);
    }

    public int getPosition(SliderView sliderView) {
        if (mSliderAdapter == null || mSliderAdapter.getSliderViews() == null)
            return -1;

        return mSliderAdapter.getSliderViews().indexOf(sliderView);
    }

    public List<SliderView> getSliderItems() {
        if (mSliderAdapter == null)
            return null;

        return mSliderAdapter.getSliderViews();
    }

    public void setCurrentItem(int position) {
        if (position < 0 || position >= mSliderAdapter.getCount())
            throw new IndexOutOfBoundsException();
        mSliderPager.setCurrentItem(position, true);
    }

    public void setCurrentItem(SliderView sliderView) {
        if (sliderView == null)
            throw new NullPointerException();
        mSliderPager.setCurrentItem(getPosition(sliderView), true);
    }

    public int getSliderItemCount() {
        return mSliderAdapter == null ? 0 : mSliderAdapter.getCount();
    }
}
