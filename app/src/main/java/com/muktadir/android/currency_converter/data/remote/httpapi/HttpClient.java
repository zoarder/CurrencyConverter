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

package com.muktadir.android.currency_converter.data.remote.httpapi;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.muktadir.android.util.helper.ShowLog;

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

class HttpClient {
    private static final String BASE_URL = "https://api.twitter.com/1/";
    private static String TAG = "HttpClient";
    private static AsyncHttpClient client = new AsyncHttpClient();

    /*
     * Create the get request to remote
     * */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        ShowLog.e(TAG, getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /*
     * Create the post request to remote
     * */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        ShowLog.e(TAG, getAbsoluteUrl(url));
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /*
     * Get absolute url based on base url and api
     * */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}