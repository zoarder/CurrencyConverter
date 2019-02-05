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
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.muktadir.android.currency_converter.data.local.base.BaseDao;

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

@Dao
public interface DownloadTaskDao extends BaseDao<DownloadTaskEntity> {

    @Query("SELECT * FROM DOWNLOADS_QUEUE")
    List<DownloadTaskEntity> getDownloadsQueue();

    @Query("SELECT * FROM DOWNLOADS_QUEUE")
    LiveData<List<DownloadTaskEntity>> getDownloadsQueueLiveData();

    @Query("SELECT COUNT(id) FROM DOWNLOADS_QUEUE WHERE appId = :appId")
    int isAlreadyInQueue(String appId);

    @Query("SELECT * FROM DOWNLOADS_QUEUE WHERE appId = :appId")
    LiveData<DownloadTaskEntity> getDownloadInfoByAppIdLiveData(String appId);

    @Query("SELECT * FROM DOWNLOADS_QUEUE WHERE appId = :appId")
    DownloadTaskEntity getDownloadInfoByAppId(String appId);

    @Query("SELECT * FROM DOWNLOADS_QUEUE WHERE downloadId = :downloadId")
    DownloadTaskEntity getDownloadInfoByDownloadId(long downloadId);

    @Query("DELETE FROM DOWNLOADS_QUEUE WHERE downloadId = :downloadId")
    void removeAppFromDownloadsQueueByDownloadId(long downloadId);
}