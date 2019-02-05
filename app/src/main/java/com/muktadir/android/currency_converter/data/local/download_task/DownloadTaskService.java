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

import android.arch.lifecycle.LiveData;

import com.muktadir.android.currency_converter.data.local.dbstorage.DatabaseService;

import java.util.List;

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

public class DownloadTaskService {

    public static long addAppToDownloadsQueue(DownloadTaskEntity downloadTaskEntity) {
        return DatabaseService.on().downloadTaskDao().insert(downloadTaskEntity);
    }

    // Required later
    public static void removeAppFromDownloadsQueueByDownloadId(long downloadId) {
        DatabaseService.on().downloadTaskDao().removeAppFromDownloadsQueueByDownloadId(downloadId);
    }

    public static void removeAppFromDownloadsQueue(DownloadTaskEntity downloadTaskEntity) {
        DatabaseService.on().downloadTaskDao().delete(downloadTaskEntity);
    }

    public static int updateAppDownloadInfo(DownloadTaskEntity downloadTaskEntity) {
        return DatabaseService.on().downloadTaskDao().update(downloadTaskEntity);
    }

    public static LiveData<List<DownloadTaskEntity>> getDownloadsQueueLiveData() {
        return DatabaseService.on().downloadTaskDao().getDownloadsQueueLiveData();
    }

    public static List<DownloadTaskEntity> getDownloadsQueue() {
        return DatabaseService.on().downloadTaskDao().getDownloadsQueue();
    }

    // Required later
    public static boolean isAlreadyInQueue(String appId) {
        return DatabaseService.on().downloadTaskDao().isAlreadyInQueue(appId) > 0;
    }

    public static LiveData<DownloadTaskEntity> getDownloadInfoByAppIdLiveData(String appId) {
        return DatabaseService.on().downloadTaskDao().getDownloadInfoByAppIdLiveData(appId);
    }

    public static DownloadTaskEntity getDownloadInfoByAppId(String appId) {
        return DatabaseService.on().downloadTaskDao().getDownloadInfoByAppId(appId);
    }

    public static DownloadTaskEntity getDownloadInfoByDownloadId(long downloadId) {
        return DatabaseService.on().downloadTaskDao().getDownloadInfoByDownloadId(downloadId);
    }
}
