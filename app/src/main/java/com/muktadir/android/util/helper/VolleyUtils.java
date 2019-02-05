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

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.muktadir.android.currency_converter.R;

import org.json.JSONObject;

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

public class VolleyUtils {

    public static boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key);
    }

    public static boolean showVolleyResponseError(final Activity activity, VolleyError volleyError, final boolean isFinishActivity) {
        try {
            NetworkResponse networkResponse = volleyError.networkResponse;
            if (networkResponse != null) {
                ShowLog.e("Error", "Error. HTTP Status Code:" + networkResponse.statusCode);
            }
            String title, errorMsg = "";
            if (volleyError instanceof TimeoutError) {
                ShowLog.e("Error", "TimeoutError");
                title = activity.getResources().getString(R.string.network_connection_timeout_error);
                errorMsg = activity.getResources().getString(R.string.network_connection_timeout_error_msg);
            } else if (volleyError instanceof NoConnectionError) {
                ShowLog.e("Error", "NoConnectionError");
                title = activity.getResources().getString(R.string.network_connection_disabled_title);
                errorMsg = activity.getResources().getString(R.string.network_connection_disabled_content);
            } else if (volleyError instanceof AuthFailureError) {
                ShowLog.e("Error", "AuthFailureError");
                title = activity.getResources().getString(R.string.network_connection_auth_error);
            } else if (volleyError instanceof ServerError) {
                ShowLog.e("Error", "ServerError");
                title = activity.getResources().getString(R.string.network_connection_server_error);
                errorMsg = activity.getResources().getString(R.string.network_connection_server_error_msg);
            } else if (volleyError instanceof NetworkError) {
                ShowLog.e("Error", "NetworkError");
                title = activity.getResources().getString(R.string.network_connection_network_error);
            } else if (volleyError instanceof ParseError) {
                ShowLog.e("Error", "ParseError");
                title = activity.getResources().getString(R.string.network_connection_parse_error);
            } else {
                ShowLog.e("Error", "UnknownError");
                title = activity.getResources().getString(R.string.network_connection_unknown_error);
            }


            final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
            builder.setTitle(title);
            builder.setCancelable(false);
            try {
                if (!StringUtils.isNullOrEmpty(errorMsg)) {
                    builder.setMessage(errorMsg);
                } else {
                    builder.setMessage(volleyError.getMessage());
                }
            } catch (NullPointerException ex) {
                builder.setMessage(volleyError.getMessage());
            }

            builder.setPositiveButton("OK", (dialog, which) -> {
                if (isFinishActivity) {
                    dialog.dismiss();
                    activity.finish();
                    activity.overridePendingTransition(R.anim.trans_right_in,
                            R.anim.trans_right_out);
                } else {
                    dialog.dismiss();
                }
            });
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();

            //NotificationDialog notificationDialog = new NotificationDialog(activity, title, errorMsg, "Ok", true);
            //notificationDialog.show();

            return true;
        } catch (Exception ex) {
            ShowLog.e("Volley Error", ex.getMessage());
            return false;
        }
    }

    public static void showUnauthorizedResponseError(final Activity activity, final boolean isFinishActivity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Unauthorized Access!!!");
        builder.setCancelable(false);
        builder.setMessage("To Resolve the problem please logout & login again. Thank You.");

        builder.setPositiveButton("OK", (dialog, which) -> {
            if (isFinishActivity) {
                dialog.dismiss();
                activity.finish();
                activity.overridePendingTransition(R.anim.trans_right_in,
                        R.anim.trans_right_out);
            } else {
                dialog.dismiss();
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    public static void showOtherResponseError(final Activity activity, String error, final boolean isFinishActivity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Unsuccessful Request!!!");
        builder.setCancelable(false);
        builder.setMessage(error);

        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            if (isFinishActivity) {
                activity.finish();
                activity.overridePendingTransition(R.anim.trans_right_in,
                        R.anim.trans_right_out);
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    public static void showSuccessfulResponse(final Activity activity, String error, final boolean isFinishActivity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Successful Request!!!");
        builder.setCancelable(false);
        builder.setMessage(error);

        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            if (isFinishActivity) {
                activity.finish();
                activity.overridePendingTransition(R.anim.trans_right_in,
                        R.anim.trans_right_out);
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    private static void finish(Activity activity, boolean isFinishActivity) {
        if (isFinishActivity) {
            activity.finish();
            activity.overridePendingTransition(R.anim.trans_right_in,
                    R.anim.trans_right_out);
        }
    }
}
