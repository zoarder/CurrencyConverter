/*
 * Copyright (C) 2017 MUKTADIR
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://muktadir.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.muktadir.android.util.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.muktadir.android.currency_converter.R;

import java.io.ByteArrayOutputStream;

/**
 * ****************************************************************************
 * * Copyright © 2017 MUKTADIR, All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@muktadir.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
 * ****************************************************************************
 */

public class Glider {

    public static void show(Context context, String location, ImageView imageView) {
        try {
            if (location != null && !location.isEmpty() && imageView != null && context != null) {

                RequestOptions options = new RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.image_placeholder_app)
                        .error(R.drawable.image_placeholder_app)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .skipMemoryCache(false);

                Glide.with(context)
                        .load(location)
                        .apply(options)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showScreenShots(Context context, String location, ImageView imageView) {
        try {
            if (location != null && !location.isEmpty() && imageView != null && context != null) {

                RequestOptions options = new RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.image_placeholder_screenshots)
                        .error(R.drawable.image_placeholder_screenshots)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .skipMemoryCache(false);

                Glide.with(context)
                        .load(location)
                        .apply(options)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCircleImage(Context context, String location, ImageView imageView) {
        try {
            if (location != null && !location.isEmpty() && imageView != null && context != null) {

                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.ic_user)
                        .error(R.drawable.ic_user)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .skipMemoryCache(false);

                Glide.with(context)
                        .load(location)
                        .apply(RequestOptions.circleCropTransform())
                        .apply(options)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * load bitmap in ImageView
     *
     * @param bitmap bitmap
     * @param imageView imageView
     */
    public static void loadBitmap(Context context, Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Glide.with(context)
                .asBitmap()
                .load(stream.toByteArray())
                .into(imageView);
    }
}