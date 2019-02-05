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

package com.muktadir.android.currency_converter.data.local.download_task;

import android.arch.persistence.room.Entity;

import com.muktadir.android.currency_converter.data.local.base.RoomEntity;
import com.muktadir.android.currency_converter.data.local.dbstorage.TableNames;

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

@Entity(tableName = TableNames.DOWNLOADS_QUEUE)
public class DownloadTaskEntity extends RoomEntity {

    String appId;
    private String appName;
    private String appUrl;
    private String appData;
    private boolean isDownloading;
    long downloadId;

    DownloadTaskEntity(String appId, String appName, String appUrl, String appData) {
        this.appId = appId;
        this.appName = appName;
        this.appUrl = appUrl;
        this.appData = appData;
    }

    String getAppData() {
        return appData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }

    String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    long getDownloadId() {
        return downloadId;
    }

    void setDownloadId(long downloadId) {
        this.downloadId = downloadId;
    }

    String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    boolean isDownloading() {
        return isDownloading;
    }

    void setDownloading(boolean downloading) {
        isDownloading = downloading;
    }

}
