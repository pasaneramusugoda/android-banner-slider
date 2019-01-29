package com.eramusugoda.bannerslider.IndicatorView;

import android.support.annotation.Nullable;
import com.eramusugoda.bannerslider.IndicatorView.animation.AnimationManager;
import com.eramusugoda.bannerslider.IndicatorView.animation.controller.ValueController;
import com.eramusugoda.bannerslider.IndicatorView.animation.data.Value;
import com.eramusugoda.bannerslider.IndicatorView.draw.DrawManager;
import com.eramusugoda.bannerslider.IndicatorView.draw.data.Indicator;

public class IndicatorManager implements ValueController.UpdateListener {

    private DrawManager drawManager;
    private AnimationManager animationManager;
    private Listener listener;

    interface Listener {
        void onIndicatorUpdated();
    }

    IndicatorManager(@Nullable Listener listener) {
        this.listener = listener;
        this.drawManager = new DrawManager();
        this.animationManager = new AnimationManager(drawManager.indicator(), this);
    }

    public AnimationManager animate() {
        return animationManager;
    }

    public Indicator indicator() {
        return drawManager.indicator();
    }

    public DrawManager drawer() {
        return drawManager;
    }

    @Override
    public void onValueUpdated(@Nullable Value value) {
        drawManager.updateValue(value);
        if (listener != null) {
            listener.onIndicatorUpdated();
        }
    }
}
