/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhihu.matisse.engine.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * {@link ImageEngine} implementation using Glide.
 */

public class GlideEngine implements ImageEngine {
    private RequestOptions thumbRequestOptions;
    private RequestOptions imageRequestOptions;

    public RequestOptions getThumbRequestOption(Drawable placeHolder, int resize) {
        if (thumbRequestOptions == null) {
            thumbRequestOptions = new RequestOptions().placeholder(placeHolder)
                                                      .override(resize, resize)
                                                      .centerCrop();
        }
        return thumbRequestOptions;
    }

    public RequestOptions getImageRequestOption(int resizeX, int resizeY) {
        if (thumbRequestOptions == null) {
            thumbRequestOptions = new RequestOptions().override(resizeX, resizeY)
                                                      .priority(Priority.HIGH);
        }
        return thumbRequestOptions;
    }

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context)
             .asBitmap()
             .load(uri)
             .apply(getThumbRequestOption(placeholder, resize))
             .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context)
             .asBitmap()
             .load(uri)
             .apply(getThumbRequestOption(placeholder, resize))
             .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
             .load(uri)
             .apply(getImageRequestOption(resizeX, resizeY))
             .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
             .asGif()
             .load(uri)
             .apply(getImageRequestOption(resizeX, resizeY))
             .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }

}
