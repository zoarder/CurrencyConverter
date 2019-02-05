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

package com.muktadir.android.util.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;

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

public class SharedPreferencesManager {
//    private static final int KEY_MODE_PRIVATE = 0;
//    private static final String PREFERENCES_NAME = "AFCHealth";
//    // LogCat tag
//    private static String TAG = SharedPreferencesManager.class.getSimpleName();
//    private Context mContext;
//    private SharedPreferences mSharedPreferences;
//    private Editor mSharedPreferencesEditor;
//
//    public SharedPreferencesManager(Context context) {
//        mContext = context;
//        mSharedPreferences = mContext.getSharedPreferences(PREFERENCES_NAME, KEY_MODE_PRIVATE);
//        mSharedPreferencesEditor = mSharedPreferences.edit();
//    }


    public static boolean clearAllSession(Context context) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.clear();
        mSharedPreferencesEditor.apply();

        return true;
    }

    /*
     * Setting for register and unregister user
     */

    /**
     * /** Set long value in shared preference
     *
     * @param context context
     * @param key     key
     * @param value   value
     */
    public static void setLongSetting(Context context, String key, long value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.putLong(key, value);
        mSharedPreferencesEditor.apply();

    }

    public static double getDoubleSetting(Context context, String key,
                                          double defaultValue) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getFloat(key, (float) defaultValue);

    }

    public static void setDoubleSetting(Context context, String key,
                                        double value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.putFloat(key, (float) value);
        mSharedPreferencesEditor.apply();

    }

    public static long getLongSetting(Context context, String key,
                                      long defaultValue) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getLong(key, defaultValue);

    }

    public static void setStringSetting(Context context, String key,
                                        String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.putString(key, value);
        mSharedPreferencesEditor.apply();
    }

    /**
     * Set boolean value in shared preference
     *
     * @param context context
     * @param key     key
     * @param value   value
     */
    public static void setBooleanSetting(Context context, String key,
                                         boolean value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.putBoolean(key, value);
        mSharedPreferencesEditor.apply();

    }

    /**
     * Set int value in shared preference
     *
     * @param context context
     * @param key     key
     * @param value   value
     */
    public static void setIntegerSetting(Context context, String key, int value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.putInt(key, value);
        mSharedPreferencesEditor.apply();

    }

    /**
     * get set value from shared preference
     *
     * @param context context
     * @param key     key
     * @param value   value
     */
    public static void setStringSettingSet(Context context, String key,
                                           Set<String> value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.putStringSet(key, value);
        mSharedPreferencesEditor.apply();
    }

    /**
     * get value from shared preference
     *
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return String
     */
    public static String getStringSetting(Context context, String key, String defaultValue) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString(key, defaultValue);

    }

    /**
     * get set value from shared preference
     *
     * @param context context
     * @param key     key
     * @return Set<String>
     */
    public static Set<String> getStringSetSetting(Context context, String key) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getStringSet(key, new HashSet<>());
    }

    /**
     * get value from shared preference if not found return given default value
     *
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return string
     */
    public static String getDefaultSettingIfValueNotFound(Context context,
                                                          String key, String defaultValue) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString(key, defaultValue);

    }

    /**
     * get boolean value from shared preference if not found return given
     * default value
     *
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return boolean
     */
    public static boolean getBooleanSetting(Context context, String key,
                                            boolean defaultValue) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getBoolean(key, defaultValue);

    }

    /**
     * get integer value from shared preference if not found return given
     * default value
     *
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return boolean
     */
    public static int getIntegerSetting(Context context, String key, int defaultValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(key, defaultValue);

    }

    /**
     * remove item from shared preference
     *
     * @param context context
     * @param key     key
     */
    public static void removeSetting(Context context, String key) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.remove(key);
        mSharedPreferencesEditor.apply();
    }

    /***
     * get shared preference mSharedPreferencesEditor
     *
     * @param context context
     * @return Editor
     */
    public static Editor getEditor(Context context) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.edit();
    }

    public static void addToCounter(Context context, String key, int value) {

        int myValue = getCounter(context, key, 0) + value;
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor mSharedPreferencesEditor = sp.edit();
        mSharedPreferencesEditor.putInt(key, myValue);
        mSharedPreferencesEditor.apply();

    }

    private static int getCounter(Context context, String key, int defaultValue) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getInt(key, defaultValue);

    }

    public static boolean checkingAccessPermission(Context context, String key) {
        return SharedPreferencesManager.getCounter(context, key, 0) < 5;
    }

}
