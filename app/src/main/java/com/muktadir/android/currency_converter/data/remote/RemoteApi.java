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

package com.muktadir.android.currency_converter.data.remote;

import android.content.Context;
import android.os.AsyncTask;

import com.muktadir.android.currency_converter.data.remote.helper.InitManagerAsync;
import com.muktadir.android.currency_converter.data.remote.helper.RemoteApiOption;
import com.muktadir.android.currency_converter.data.remote.helper.callback.AuthSignInCallback;
import com.muktadir.android.currency_converter.data.remote.helper.callback.AuthSignUpCallback;
import com.muktadir.android.currency_converter.data.remote.helper.callback.FileTransferCallback;
import com.muktadir.android.currency_converter.data.remote.helper.callback.ProfileUpdateCallback;
import com.muktadir.android.currency_converter.data.remote.helper.callback.ResetPasswordCallback;
import com.muktadir.android.currency_converter.data.remote.helper.callback.ResponseCallBack;
import com.muktadir.android.currency_converter.data.remote.helper.callback.UserCallback;
import com.muktadir.android.currency_converter.data.remote.helper.callback.UserVerifiedCallback;
import com.muktadir.android.currency_converter.data.remote.helper.models.ChatMessage;
import com.muktadir.android.currency_converter.data.remote.helper.models.RemoteObject;
import com.muktadir.android.currency_converter.data.remote.parseapi.ParseManager;
import com.muktadir.android.currency_converter.data.remote.retrofit.RetrofitManager;

import java.io.File;
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

public class RemoteApi {

    private static final Object sMutexObject = new Object();
    /*RegionStart: Singleton Internal */
    private static volatile RemoteApi sRemoteApiInstance;

    private RemoteApi(Context context, RemoteApiOption remoteApiOption) {
        new InitManagerAsync(context, remoteApiOption).execute();
    }

    public synchronized static void init(Context context, RemoteApiOption remoteApiOption) {
        synchronized (sMutexObject) {
            if (sRemoteApiInstance == null)
                sRemoteApiInstance = new RemoteApi(context, remoteApiOption);
        }
    }

    public static RemoteApi on() {
        return sRemoteApiInstance;
    }

    /*RegionEnd: Singleton Internal */

    /*RegionStart: Public API */

    public void signUp(String username, String password, RemoteObject remoteObject, AuthSignUpCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().signUp(username, password, remoteObject, callback));
    }

    public void updateProfile(RemoteObject remoteObject, ProfileUpdateCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().updateUserProfile(remoteObject, callback));
    }

    public void signIn(String email, String password, AuthSignInCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().signIn(email, password, callback));
    }

    public void logOut() {
        AsyncTask.execute(() -> ParseManager.on().logOut());
    }


    public void verifyUser(String url, UserVerifiedCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().verifyUser(url, callback));
    }

    public void resetPassword(String email, ResetPasswordCallback resetPasswordCallback) {
        AsyncTask.execute(() -> ParseManager.on().resetPassword(email, resetPasswordCallback));
    }

    public void dynamicLink() {
//        AsyncTask.execute(() -> FireBaseManager.on().dynamicLink());
    }

    public void LiveQuerySubscribe(String user) {
        AsyncTask.execute(() -> ParseManager.on().LiveQuerySubscribe(user));
    }

    public void getCurrentUser(UserCallback callback) {
        AsyncTask.execute(() -> ParseManager.on().getCurrentUser(callback));
    }

    public void sendChatMessage(String user, ChatMessage chatMessage) {
//        AsyncTask.execute(() -> TCharmManager.on().sendMessage(user, chatMessage));
    }

    public void uploadFile(String url, String file_key, File file, FileTransferCallback callback) {
//        AsyncTask.execute(() -> S3Manager.on().uploadFile(url, file_key, file, callback));
    }

    public <T> void getRestApiResponse(String endpoint, Class<T> modelClass, HashMap<String, Object> parameters, ResponseCallBack responseCallback) {
        RetrofitManager.on().getApiResponse(endpoint, modelClass, parameters, responseCallback);
    }
}