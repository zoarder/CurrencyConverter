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

package com.muktadir.android.currency_converter.data.remote.httpapi;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

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

public class HttpModel {
    private int status;
    private Header[] header;
    private JSONObject jsonObject;
    private File file;
    private String message;
    private String data;
    private org.json.JSONArray JSONArray;
    private State state;

    public HttpModel() {
    }

    public HttpModel(File file) {
        this.file = file;
    }

    public HttpModel(String responseString) {
        this.data = responseString;
    }

    HttpModel(State state) {
        this.state = state;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setHeader(Header[] header) {
        this.header = header;
    }

    void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    void setJSONArray(org.json.JSONArray JSONArray) {
        this.JSONArray = JSONArray;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        Success, Failure
    }
}
