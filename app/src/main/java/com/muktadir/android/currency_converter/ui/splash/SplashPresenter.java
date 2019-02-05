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

package com.muktadir.android.currency_converter.ui.splash;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.muktadir.android.currency_converter.data.helper.Constants;
import com.muktadir.android.currency_converter.data.helper.keys.Endpoints;
import com.muktadir.android.currency_converter.data.local.currency_converter.ApiResponse;
import com.muktadir.android.currency_converter.ui.base.BasePresenter;
import com.muktadir.android.util.lib.volley.ObjectRequest;

import java.util.HashMap;

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

class SplashPresenter extends BasePresenter<SplashMvpView> {
    public void getCurrencyConverterData() {
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();

        ObjectRequest<ApiResponse> objectRequest = new ObjectRequest<>(Request.Method.GET, Endpoints.Constants.BASE_URL, headers, params,
                response -> {
                    if (getMvpView() == null) {
                        return;
                    }

                    if (response == null) {
                        getMvpView().onCurrencyConverterDataLoadedFailed();
                    } else {
                        if (response.getDetails().contains(Endpoints.Values.DETAILS)) {
                            getMvpView().onCurrencyConverterDataLoadedSuccessful(response);
                        } else {
                            getMvpView().onCurrencyConverterDataLoadedFailed();
                        }
                    }
                }, error -> {
            if (getMvpView() == null) {
                return;
            }
            getMvpView().onCurrencyConverterDataLoadedFailed();
        }, ApiResponse.class);

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.Integer.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        com.muktadir.android.CurrencyConverterApp.getInstance().addToRequestQueue(objectRequest);
    }
}