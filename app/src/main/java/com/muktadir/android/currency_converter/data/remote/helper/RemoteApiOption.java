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

public class RemoteApiOption {
    private boolean mIsStartTCharm;
    private boolean mIsStartS3;
    private boolean mIsStartFireBase;
    private boolean mIsStartParse;
    private boolean mIsStartRightMesh;
    private boolean mIsStartHttp;
    private boolean mIsStartRetrofit;

    public RemoteApiOption(boolean isStartAll) {
 /*mIsStartTCharm = isStartAll;
 mIsStartS3 = isStartAll;
 mIsStartFireBase = isStartAll;
 mIsStartParse = isStartAll;
 mIsStartRightMesh = isStartAll;
 mIsStartHttp = isStartAll;
 */

        mIsStartTCharm = false;
        mIsStartS3 = false;
        mIsStartFireBase = false;
        mIsStartParse = isStartAll;
        mIsStartRightMesh = false;
        mIsStartHttp = false;
        mIsStartRetrofit = isStartAll;
    }

    boolean isStartHttp() {
        return mIsStartHttp;
    }

    public void setStartHttp(boolean startHttp) {
        mIsStartHttp = startHttp;
    }

    boolean isStartTCharm() {
        return mIsStartTCharm;
    }

    public void setStartTCharm(boolean startTCharm) {
        mIsStartTCharm = startTCharm;
    }

    boolean isStartS3() {
        return mIsStartS3;
    }

    public void setStartS3(boolean startS3) {
        mIsStartS3 = startS3;
    }

    boolean isStartFireBase() {
        return mIsStartFireBase;
    }

    public void setStartFireBase(boolean startFireBase) {
        mIsStartFireBase = startFireBase;
    }

    boolean isStartParse() {
        return mIsStartParse;
    }

    public void setStartParse(boolean startParse) {
        mIsStartParse = startParse;
    }

    boolean isStartRightMesh() {
        return mIsStartRightMesh;
    }

    public void setStartRightMesh(boolean startRightMesh) {
        mIsStartRightMesh = startRightMesh;
    }

    boolean ismIsStartRetrofit() {
        return mIsStartRetrofit;
    }

    public void setmIsStartRetrofit(boolean mIsStartRetrofit) {
        this.mIsStartRetrofit = mIsStartRetrofit;
    }

}