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

package com.muktadir.android.util.lib.okhttp3;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

public class OkHttpUtil {

    private static OkHttpUtil okHttpUtil;

    private OkHttpClient okHttpClient;

    private OkHttpUtil() {
        //okhttp config
        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtil getOkHttpUtil() {
        if (okHttpUtil == null) {
            okHttpUtil = new OkHttpUtil();
        }
        return okHttpUtil;
    }

    /**
     * OkHttp post request
     *
     * @param postUrl postUrl
     * @param requestBody requestBody
     * @param okHttpResponse okHttpResponse
     */
    public void postRequest(final String postUrl, RequestBody requestBody, final OkHttpResponse okHttpResponse) {

        Request request = new Request.Builder()
                .url(postUrl)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                okHttpResponse.onFail(postUrl);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = Objects.requireNonNull(response.body()).string();
                    okHttpResponse.onSuccess(postUrl, responseBody);
                }
            }
        });
    }


    /**
     * OkHttp get request
     *
     * @param postUrl postUrl
     * @param okHttpResponse okHttpResponse
     */
    public void getRequest(String requestTag, final String postUrl, final OkHttpResponse okHttpResponse) {

        Request request;

        if (requestTag != null) {
            for (Call call : okHttpClient.dispatcher().runningCalls()) {
                if (Objects.requireNonNull(call.request().tag()).equals(requestTag))
                    call.cancel();
                //return;
            }
            request = new Request.Builder()
                    .url(postUrl)
                    .tag(requestTag)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(postUrl)
                    .build();
        }


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                okHttpResponse.onFail(postUrl);
            }


            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    String responseBody = Objects.requireNonNull(response.body()).string();
                    okHttpResponse.onSuccess(postUrl, responseBody);
                }
            }
        });
    }


}
