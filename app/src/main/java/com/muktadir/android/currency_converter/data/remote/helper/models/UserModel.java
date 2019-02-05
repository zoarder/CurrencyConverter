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

package com.muktadir.android.currency_converter.data.remote.helper.models;

import android.net.Uri;

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

public class UserModel {
    private String mUserName;
    private String mPassword;
    private String mEmail;
    private String mPhoneNumber;
    private boolean isSuccess;
    private String mFullName;
    private Uri mPhotoUrl;
    private boolean mEmailVerified;
    private String mUid;

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }


    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setPhotoUrl(Uri photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public void setEmailVerified(boolean emailVerified) {
        mEmailVerified = emailVerified;
    }

    public boolean getEmailVarified() {
        return mEmailVerified;
    }

    public void setUid(String uid) {
        mUid = uid;
    }
}