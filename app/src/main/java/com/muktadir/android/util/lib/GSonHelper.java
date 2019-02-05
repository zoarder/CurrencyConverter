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

package com.muktadir.android.util.lib;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class GSonHelper {

    // Static fields
    private static Gson sGson = new Gson();

    // Private Constructor to avoid creating instance of this class from outside
    private GSonHelper() {
    }

    /*------------------Methods to get JSON from different OBJECTS------------------*/

    /**
     * @param <T>              the 1st type of the desired object
     * @param modelClassObject an object of model class
     * @return String the desired json
     * <p>
     * i.e: String json = GsonHelper.toJson(modelClassObject);
     * @author Mohd. Asfaq-E-Azam Rifat
     */
    public static <T> String toJson(T modelClassObject) {

        Type type = new TypeToken<T>() {
        }.getType();

        try {
            return sGson.toJson(modelClassObject, type);
        } catch (Exception e) {
            Log.e("GsonHelper", "toJson(T modelClassObject) Method -> " + e);
        }

        return null;
    }

    /**
     * @param <T>                     the 1st type of the desired object
     * @param listOfModelClassObjects a list of objects of model class
     * @return String the desired json
     * <p>
     * i.e: String json = GsonHelper.toJson(listOfModelClassObjects);
     * @author Mohd. Asfaq-E-Azam Rifat
     */
    public static <T> String toJson(List<T> listOfModelClassObjects) {

        Type type = new TypeToken<List<T>>() {
        }.getType();

        try {
            return sGson.toJson(listOfModelClassObjects, type);
        } catch (Exception e) {
            Log.e("GsonHelper", "toJson(List<T> listOfModelClassObjects) Method -> " + e);
        }

        return null;
    }

    /**
     * @param <T1>                             the 1st type of the desired object
     * @param <T2>                             the 2nd type of the desired object
     * @param keyValuePairsOfModelClassObjects an map of objects of model class
     * @return String the desired json
     * <p>
     * i.e: String json = GsonHelper.toJson(keyValuePairsOfModelClassObjects);
     * @author Mohd. Asfaq-E-Azam Rifat
     */
    public static <T1, T2> String toJson(Map<T1, T2> keyValuePairsOfModelClassObjects) {

        Type type = new TypeToken<Map<T1, T2>>() {
        }.getType();

        try {
            return sGson.toJson(keyValuePairsOfModelClassObjects, type);
        } catch (Exception e) {
            Log.e("GsonHelper", "toJson(Map<T1, T2> keyValuePairsOfModelClassObjects) Method -> " + e);
        }

        return null;
    }

    /**
     * @param <T>                      the type of the desired object
     * @param arrayOfModelClassObjects an array of objects of model class
     * @return String the desired json
     * <p>
     * i.e: String json = GsonHelper.toJson(arrayOfModelClassObjects);
     * @author Mohd. Asfaq-E-Azam Rifat
     */
    public static <T> String toJson(T[] arrayOfModelClassObjects) {

        Type type = new TypeToken<T[]>() {
        }.getType();

        try {
            return sGson.toJson(arrayOfModelClassObjects, type);
        } catch (Exception e) {
            Log.e("GsonHelper", "toJson(T[] arrayOfModelClassObjects) Method -> " + e);
        }

        return null;
    }

    /*------------------Methods to get OBJECTS from JSON------------------*/

    /**
     * @param <T>        the type of the desired object
     * @param json       the string from which the object is to be deserialized
     * @param modelClass the specific class of which the object is to be generated
     * @return T an object of the model class
     * <p>
     * i.e: ModelClass modelClassObject = GsonHelper.fromJson(json, ModelClass.class);
     * @author Mohd. Asfaq-E-Azam Rifat
     */
    public static <T> T fromJson(String json, Class<T> modelClass) {

        //Type type = new TypeToken<T>(){}.getType();
        //NotifyModel temp = sGson.fromJson(json, type);

        try {
            return sGson.fromJson(json, modelClass);
        } catch (Exception e) {
            Log.e("GsonHelper", "fromJson(String json, Class<T> modelClass) Method -> " + e);
        }

        return null;
    }

    /**
     * @param json the string from which the object is to be deserialized
     * @return JsonObject a json object to be used
     * <p>
     * i.e: JsonObject jsonObject = GsonHelper.fromJson(json);
     * @author Mohd. Asfaq-E-Azam Rifat
     */
    public static JsonObject fromJson(String json) {

        try {
            return sGson.fromJson(json, JsonObject.class);
        } catch (Exception e) {
            Log.e("GsonHelper", "fromJson(String json) Method -> " + e);
        }

        return null;
    }

    /**
     * @param <T>         the type of the desired object
     * @param json        the string from which the object is to be deserialized
     * @param desiredType the specific genericized type of source.
     * @return List<T> list of the desired objects
     * <p>
     * i.e:
     * List<ModelClass> listOfModelClassObjects
     * = GsonHelper.fromJson(json, new TypeToken<List<ModelClass>>(){}.getType());
     * @author Mohd. Asfaq-E-Azam Rifat
     */
    public static <T> List<T> fromJson(String json, Type desiredType) {

        try {
            return sGson.fromJson(json, desiredType);
        } catch (Exception e) {
            Log.e("GsonHelper", "fromJson(String json, Type desiredType) Method -> " + e);
        }

        return null;
    }


    /**
     * @param object object
     * @param aClass aClass
     * @param <T> <T>
     * @return Object of desired class
     */
    public static <T> T mapToObject(HashMap<Object, Object> object, Class<T> aClass) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(aClass, new MyDeserializer<T>())
                .create();

        JsonElement jsonElement = gson.toJsonTree(object);
        return gson.fromJson(jsonElement, aClass);
    }

    static class MyDeserializer<T> implements JsonDeserializer<T> {
        @Override
        public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException {
            JsonElement content = je.getAsJsonObject();

            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, type);

        }
    }


}