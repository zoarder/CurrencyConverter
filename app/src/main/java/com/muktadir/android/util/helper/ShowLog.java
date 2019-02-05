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

import android.util.Log;

import com.muktadir.android.currency_converter.BuildConfig;

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

public class ShowLog {

    public static void i(String tag, String string) {
        if (BuildConfig.DEBUG) Log.i(tag, string);
    }

    public static void e(String tag, String string) {
        if (BuildConfig.DEBUG) Log.e(tag, string);
    }

    public static void d(String tag, String string) {
        if (BuildConfig.DEBUG) Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (BuildConfig.DEBUG) Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (BuildConfig.DEBUG) Log.w(tag, string);
    }
}