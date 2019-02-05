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

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.muktadir.android.currency_converter.R;

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

public final class BarUtil {
    private BarUtil() {
    }

    public static void setStatusBarColor(AppCompatActivity context, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(context, colorId));
        }
    }

    public static void setActionBarColor(AppCompatActivity activity, int colorId) {
        if (activity != null && colorId > 0) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(colorId)));
            }
        }
    }

    private static int getColor(Context context, int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static int getToolbarHeight(Context context) {

        final TypedArray styledAttributes = context.getTheme().
                obtainStyledAttributes(new int[]{R.attr.actionBarSize});

        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static void showToolbar(Activity activity, int toolbarId) {
        View view = ScreenUtils.viewById(activity, toolbarId);
        if (Toolbar.class.isInstance(view)) {
            showToolbar((Toolbar) view);
        }
    }

    private static void showToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        }
    }

    public static void hideToolbar(Activity activity, int toolbarId) {
        View view = ScreenUtils.viewById(activity, toolbarId);
        if (Toolbar.class.isInstance(view)) {
            hideToolbar((Toolbar) view);
        }
    }

    private static void hideToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}