/*
 * Created by Pasan on 1/30/19 2:12 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 1/30/19 2:09 PM
 */

package com.eramusugoda.bannerslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class DefaultSliderView extends SliderView {

    private static final String TAG = DefaultSliderView.class.getSimpleName();
    private String description;
    private int descriptionTextColor = Color.WHITE;
    private float descriptionTextSize = 1;
    private boolean hideShadow = false;
    private RequestListener<Drawable> mRequestListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            Log.e(TAG, "onLoadFailed", e);

            if (e != null)
                e.logRootCauses(TAG);
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            if (onSliderImageReadyListener != null)
                onSliderImageReadyListener.onSliderImageIsReady();
            return false;
        }
    };

    public DefaultSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(context).inflate(R.layout.image_slider_layout_item, null, true);
        ImageView autoSliderImage = v.findViewById(R.id.iv_auto_image_slider);
        TextView tv_description = v.findViewById(R.id.tv_auto_image_slider);

        if (hideShadow)
            v.findViewById(R.id.fl_bottom).setBackground(null);

        tv_description.getBackground();
        if (descriptionTextSize != 1) {
            tv_description.setTextSize(descriptionTextSize);
        }
        tv_description.setTextColor(descriptionTextColor);
        tv_description.setText(getDescription());
        bindViewData(v, autoSliderImage);
        return v;
    }

    @Override
    void bindViewData(View v, ImageView autoSliderImage) {
        final DefaultSliderView con = this;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSliderClickListener != null) {
                    onSliderClickListener.onSlideClick(con);
                }
            }
        });
        try {
            autoSliderImage.setScaleType(getScaleType());
            if (imageUrl != null) {
                Glide.with(context).asDrawable().load(imageUrl).addListener(mRequestListener).into(autoSliderImage);
            }
            if (imageRes != 0) {
                Glide.with(context).asDrawable().load(imageRes).addListener(mRequestListener).into(autoSliderImage);
            }
        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHideShadow() {
        return hideShadow;
    }

    public void setHideShadow(boolean hideShadow) {
        this.hideShadow = hideShadow;
    }

    public void setDescriptionTextColor(int descriptionTextColor) {
        this.descriptionTextColor = descriptionTextColor;
    }

    public void setDescriptionTextSize(float descriptionTextSize) {
        this.descriptionTextSize = descriptionTextSize;
    }
}
