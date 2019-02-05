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

package com.muktadir.android.currency_converter.data.remote.helper;

import android.content.Context;
import android.os.AsyncTask;

import com.muktadir.android.currency_converter.BuildConfig;
import com.muktadir.android.currency_converter.R;
import com.muktadir.android.currency_converter.data.remote.httpapi.HttpManager;
import com.muktadir.android.currency_converter.data.remote.parseapi.ParseManager;
import com.muktadir.android.currency_converter.data.remote.retrofit.RetrofitManager;

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

public class InitManagerAsync extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    private RemoteApiOption mRemoteApiOption;

    public InitManagerAsync(Context context, RemoteApiOption remoteApiOption) {
        mContext = context;
        mRemoteApiOption = remoteApiOption;

        if (mRemoteApiOption == null)
            mRemoteApiOption = new RemoteApiOption(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (mRemoteApiOption.isStartTCharm()) {
//            TCharmManager.init(mContext);
        }
        if (mRemoteApiOption.isStartS3()) {
//            S3Manager.init(mContext);
        }
        if (mRemoteApiOption.isStartFireBase()) {
//            FireBaseManager.init(mContext);
        }
        if (mRemoteApiOption.isStartParse()) {
            ParseManagerInit();
        }
        if (mRemoteApiOption.isStartRightMesh()) {
//            RMManager.on().init(mContext);
        }
        if (mRemoteApiOption.isStartHttp()) {
            HttpManager.init(mContext);
        }
        if (mRemoteApiOption.ismIsStartRetrofit()) {
            RetrofitManager.init(mContext);
        }

        return null;
    }

    private void ParseManagerInit() {
        String serverUrl = BuildConfig.DEBUG ? mContext.getString(R.string.parse_development_url) : mContext.getString(R.string.parse_production_url);
        String appId = mContext.getString(R.string.parse_app_id);
        String clientKey = mContext.getString(R.string.parse_client_key);

        ParseManager.init(mContext, serverUrl, appId, clientKey);
    }
}