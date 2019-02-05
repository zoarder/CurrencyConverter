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

package com.muktadir.android.util.lib.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class JsonBodyStringRequest extends Request<String> {

    private static final String TAG = JsonBodyStringRequest.class.getSimpleName();
    private Map<String, String> headers;
    private Map<String, String> params;
    private byte[] body;
    private String dateFormat = null;
    private Response.Listener<String> mListener;

    public JsonBodyStringRequest(String url, Response.ErrorListener listener) {
        this(Method.GET, url, listener);
    }


    public JsonBodyStringRequest(int method, String url, Response.ErrorListener errorListener) {
        this(method, url, null, errorListener);
    }

    public JsonBodyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(method, url, null, null, null, listener, errorListener);
    }

    public JsonBodyStringRequest(int method, String url, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(method, url, null, params, null, listener, errorListener);
    }

    public JsonBodyStringRequest(int method, String url, Map<String, String> headers, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(method, url, headers, params, null, listener, errorListener);
    }

    public JsonBodyStringRequest(int method, String url, Map<String, String> headers, Map<String, String> params, byte[] body, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.headers = headers;
        this.params = params;
        this.body = body;
        this.mListener = listener;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }

    @Override
    protected void deliverResponse(String response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? this.headers : super.getHeaders();
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return (params != null) ? this.params : super.getParams();
    }


    @Override
    public byte[] getBody() throws AuthFailureError {
        return body != null ? this.body : super.getBody();
    }

    @Override
    public String getBodyContentType() {
        return "application/json"; //return "application/json; charset=utf-8";
    }
}
