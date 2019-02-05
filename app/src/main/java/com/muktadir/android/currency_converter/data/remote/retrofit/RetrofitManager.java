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

package com.muktadir.android.currency_converter.data.remote.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.muktadir.android.currency_converter.R;
import com.muktadir.android.currency_converter.data.helper.keys.Endpoints;
import com.muktadir.android.currency_converter.data.remote.helper.callback.ResponseCallBack;
import com.muktadir.android.util.helper.ShowLog;
import com.muktadir.android.util.lib.GSonHelper;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

public class RetrofitManager {

    private static final Object sMutexObject = new Object();
    private static volatile RetrofitManager sRetrofitManagerInstance;
    private static Retrofit sRetrofit;
    private Context mContext;

    private RetrofitManager(Context context) {
        mContext = context;
    }

    public synchronized static void init(Context context) {
        synchronized (sMutexObject) {
            if (sRetrofitManagerInstance == null) {
                sRetrofitManagerInstance = new RetrofitManager(context);
            }
        }

        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(Endpoints.Constants.BASE_URL)
                    .client(buildClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

    }


    private static OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();

                    Request.Builder builder1 = request.newBuilder()
//                                .addHeader("X-Parse-Application-Id", mContext.getString(R.string.parse_app_id))
                            .addHeader("Content-Type", "application/json");
//                                .addHeader("X-Parse-REST-API-Key", mContext.getString(R.string.parse_rest_api_key))
//                                .addHeader("X-Parse-Session-Token", ParseUser.getCurrentUser() != null ? ParseUser.getCurrentUser().getSessionToken() : "");

                    request = builder1.build();

                    return chain.proceed(request);
                });

        return builder.build();

    }


    public static RetrofitManager on() {
        return sRetrofitManagerInstance;
    }


    /**
     * Get api response using retrofit
     *
     * @param endpoint         endpoint
     * @param modelClass       modelClass
     * @param parameters       parameters
     * @param responseCallback responseCallback
     * @param <T>              <T>
     */
    public <T> void getApiResponse(String endpoint, Class<T> modelClass, HashMap<String, Object> parameters, ResponseCallBack responseCallback) {
        ShowLog.e("endpoint", endpoint);
        ShowLog.e("parameters", parameters.toString());
        RetrofitService service = sRetrofit.create(RetrofitService.class);
        Call<JsonObject> call = service.callApi(endpoint, parameters);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.body() != null) {
                    responseCallback.onResponseReceived(GSonHelper.fromJson(response.body().toString(), modelClass), modelClass, parameters, response.isSuccessful(), response.message());
                } else {
                    responseCallback.onResponseReceived(null, modelClass, parameters, false, mContext.getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                responseCallback.onResponseReceived(null, modelClass, parameters, false, t.getMessage());
            }
        });
    }
}
