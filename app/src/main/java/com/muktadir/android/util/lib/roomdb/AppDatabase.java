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

package com.muktadir.android.util.lib.roomdb;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
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

public abstract class AppDatabase extends RoomDatabase {
    protected static <T extends RoomDatabase> T createDb(Context context, String dbName, Class<T> dbService, String... migrationScripts) {
        Builder<T> builder = Room.databaseBuilder(context, dbService, dbName);

        for (Migration migration : getMigrations(migrationScripts)) {
            builder.addMigrations(migration);
        }

        return builder.build();
    }

    private static List<Migration> getMigrations(String... migrationScripts) {
        List<Migration> migrationList = new ArrayList<>();

        int startVersion = 1;
        int endVersion = 2;

        Migration migration;

        for (final String migrationSchema : migrationScripts) {
            migration = new Migration(startVersion, endVersion) {
                @Override
                public void migrate(@NonNull SupportSQLiteDatabase database) {
                    database.execSQL(migrationSchema);
                }
            };

            startVersion++;
            endVersion++;

            migrationList.add(migration);
        }

        return migrationList;
    }
}