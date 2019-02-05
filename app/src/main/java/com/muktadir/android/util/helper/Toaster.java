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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.Toast;

import com.muktadir.android.currency_converter.BuildConfig;
import com.muktadir.android.currency_converter.R;

import es.dmoral.toasty.Toasty;

import static android.graphics.Typeface.BOLD_ITALIC;

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

public class Toaster {

    /*
     * Private constructor. Don't make it public
     * */
    private Toaster() {
    }


    /*
     * Show long toast message
     * */
    public static void show(Context context, String txt) {
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
    }

    /*
     * Init Toast message context only one time
     * */
    public static void init(Context context) {
        Toasty.Config.getInstance()
                //.setErrorColor(getColor(R.color.active_color))
                //.setInfoColor(getColor(R.color.active_color))
                //.setSuccessColor(getColor(R.color.active_color))
                //.setWarningColor(getColor(R.color.active_color))
                //.setTextColor(getColor(R.color.active_color))
                .tintIcon(true)
                //.setToastTypeface(Typeface.createFromAsset(sContext.getAssets(), "PCap Terminal.otf"))
                //.setTextSize(25)
                .apply();
    }

    private static int getColor(Context context, int active_color) {
        return context.getResources().getColor(active_color);
    }

    public static void showLong(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toast.makeText(context, txt, Toast.LENGTH_LONG).show();
        }
    }

    /*
     * Show short toast message
     * */
    public static void showShort(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Show short toast message only in debug mode
     * */
    public static void showDebugToast(Context context, String txt) {
        if (!TextUtils.isEmpty(txt) && BuildConfig.DEBUG) {
            Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
        }
    }

    public static void error(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.error(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void success(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.success(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void info(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.info(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void warning(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.warning(context, txt, Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void normal(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.normal(context, txt).show();
        }
    }

    public static void normalWithIcon(Context context, String txt) {
        if (!TextUtils.isEmpty(txt)) {
            Toasty.normal(context, txt, R.drawable.near_by_tab_icon).show();
        }
    }

    private CharSequence getFormattedMessage() {
        final String prefix = "Formatted ";
        final String highlight = "bold italic";
        final String suffix = " text";

        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ssb;
    }


}