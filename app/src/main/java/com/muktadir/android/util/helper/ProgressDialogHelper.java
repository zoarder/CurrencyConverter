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
import android.app.ProgressDialog;
import android.content.Context;

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

public class ProgressDialogHelper {

    private ProgressDialog mProgressDialog;
    private Activity mContext;

    public ProgressDialogHelper() {

    }

    public ProgressDialogHelper(Activity context, String title) {
        this.mContext = context;
        mProgressDialog = new ProgressDialog(context);
//        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setMessage(title);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setIndeterminate(true);
    }

    public ProgressDialogHelper(Context context, String title, String message) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }

    public void setProgressDialog(ProgressDialog mProgressDialog) {
        //make sure the previous dialog is hidden
        hide();
        this.mProgressDialog = mProgressDialog;
    }

    public void show() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void create(Context context, String title, String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = ProgressDialog.show(context, title, message);
    }

    public void hide() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void dismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mContext.runOnUiThread(() -> mProgressDialog.dismiss());
            mProgressDialog = null;
        }
    }

    public void onDestroy() {
        hide();
    }
}
