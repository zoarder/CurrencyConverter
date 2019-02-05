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

package com.muktadir.android.currency_converter.data.local.user;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.muktadir.android.currency_converter.data.local.base.BaseDao;
import com.muktadir.android.currency_converter.data.local.dbstorage.ColumnNames;
import com.muktadir.android.currency_converter.data.local.dbstorage.TableNames;

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
public interface UserDao extends BaseDao<UserEntity> {
    @Query("SELECT * FROM " + TableNames.USERS)
    List<UserEntity> getAllUser();

    @Query("SELECT * FROM " + TableNames.USERS)
    LiveData<List<UserEntity>> getAllUserLiveData();

    @Query("SELECT * FROM " + TableNames.USERS + " WHERE " + ColumnNames.USER_ID + " = :meshID")
    UserEntity getUser(String meshID);

    @Query("SELECT * FROM " + TableNames.USERS + " WHERE " + ColumnNames.ID + " = :id")
    UserEntity getUserById(long id);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM " + TableNames.USERS)
    void deleteAllUsers();
}