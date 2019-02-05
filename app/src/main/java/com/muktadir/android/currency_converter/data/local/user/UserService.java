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

import com.muktadir.android.util.helper.ShowLog;

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

public class UserService {
    private final UserDao mUserDao;

    public UserService(UserDao userDao) {
        mUserDao = userDao;
    }

    public UserEntity getUser(String meshID) {
        return mUserDao.getUser(meshID);
    }

    public UserEntity getUserById(long id) {
        return mUserDao.getUserById(id);
    }

    public long insert(UserEntity userEntity) {
        long id = mUserDao.insert(userEntity);
        userEntity.setId(id);
        return id;
    }

    public int updateUser(UserEntity userEntity) {
        int value = mUserDao.update(userEntity);
        ShowLog.d("Updated value", value + "");
        return value;
    }

    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }

    public void deleteItem(UserEntity userEntity) {
        mUserDao.delete(userEntity);
    }

    public List<UserEntity> getAllUser() {
        return mUserDao.getAllUser();
    }

    public LiveData<List<UserEntity>> getAllUserLiveData() {
        return mUserDao.getAllUserLiveData();
    }

    public LiveData<List<UserEntity>> getUsers() {
        return mUserDao.getAllUserLiveData();
    }
}
