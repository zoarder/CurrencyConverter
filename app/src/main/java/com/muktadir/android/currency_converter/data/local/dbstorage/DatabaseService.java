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

package com.muktadir.android.currency_converter.data.local.dbstorage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.muktadir.android.currency_converter.R;
import com.muktadir.android.currency_converter.data.local.download_task.DownloadTaskDao;
import com.muktadir.android.currency_converter.data.local.download_task.DownloadTaskEntity;
import com.muktadir.android.currency_converter.data.local.installedapps.SharedAppsDao;
import com.muktadir.android.currency_converter.data.local.installedapps.SharedAppsEntity;
import com.muktadir.android.currency_converter.data.local.user.UserDao;
import com.muktadir.android.currency_converter.data.local.user.UserEntity;
import com.muktadir.android.util.helper.Converter;
import com.muktadir.android.util.lib.roomdb.AppDatabase;

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

@Database(entities = {
        UserEntity.class,
        DownloadTaskEntity.class,
        SharedAppsEntity.class},
        version = 3, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class DatabaseService extends AppDatabase {

    private static final String MIGRATION_SQL_1_2 = "ALTER TABLE  Employee ADD COLUMN address TEXT";
    /*
    public static AppDatabaseService on(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabaseService.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabaseService.class, context.getString(R.string.app_name))
                            .build();
                }
            }
        }

        return INSTANCE;
    }
    */
    private static volatile DatabaseService sInstance;
    private static volatile DatabaseService INSTANCE;

    // Create the database
    private static DatabaseService createOld(Context context) {
        RoomDatabase.Builder<DatabaseService> builder =
                Room.databaseBuilder(context, DatabaseService.class, context.getString(R.string.app_name))
                        .allowMainThreadQueries();

        return builder.build();
    }

    // Get a database instance
    public static synchronized DatabaseService on() {
        return sInstance;
    }

    public static synchronized DatabaseService init(Context context) {

        if (sInstance == null) {
            synchronized (DatabaseService.class) {
                sInstance = createDb(context, context.getString(R.string.app_name), DatabaseService.class
                        , MIGRATION_SQL_1_2);
            }
        }

        return sInstance;
    }

    public abstract UserDao userDao();

    public abstract DownloadTaskDao downloadTaskDao();

    public abstract SharedAppsDao sharedAppsDao();

    @Override
    public void clearAllTables() {

    }
}