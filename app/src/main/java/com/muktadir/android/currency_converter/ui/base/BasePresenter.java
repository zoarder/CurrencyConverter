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

package com.muktadir.android.currency_converter.ui.base;

import android.app.Activity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

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

public abstract class BasePresenter<V extends MvpView> implements LifecycleObserver, Presenter<V> {

    /**
     * Marks a class as a LifecycleObserver.
     * It does not have any methods, instead,
     * relies on OnLifecycleEvent annotated methods.
     * <p>
     * class TestObserver implements LifecycleObserver {
     *
     * @OnLifecycleEvent(ON_CREATE) void onCreated(LifecycleOwner source) {}
     * @OnLifecycleEvent(ON_ANY) void onAny(LifecycleOwner source, Event event) {}
     * }
     */

    private volatile V mMvpView;
    private Bundle mStateBundle;
    private Activity mActivity = null;

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    /**
     * Check MvpView attached with presenter or not
     *
     * @return boolean value is view attached state
     */
    private boolean isViewAttached() {
        return mMvpView != null;
    }

    /**
     * @return current MvpView
     */
    public V getMvpView() {
        return mMvpView;
    }

    /**
     * @return Bundle value
     */
    public Bundle getStateBundle() {
        return mStateBundle == null ? mStateBundle = new Bundle() : mStateBundle;
    }

    /**
     * Check MvpView attached with presenter, otherwise through exceptions
     *
     */
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    @Override
    public void onPresenterDestroy() {
        if (mStateBundle != null && !mStateBundle.isEmpty()) mStateBundle.clear();
    }

    @Override
    public void onPresenterCreated() {
        //NO-OP
    }

    /**
     * @return Activity current active activity
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * To set current Activity on presenter
     *
     * @param activity Activity as param
     */
    protected void setActivity(Activity activity) {
        mActivity = activity;
    }

    @SuppressWarnings("TypeParameterUnusedInFormals")
    @NonNull
    protected <T extends AndroidViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return ViewModelProviders.of((FragmentActivity) mActivity).get(modelClass);
    }

    /**
     * To get current LifecycleOwner of active activity
     *
     * @return LifecycleOwner object
     */
    protected LifecycleOwner getLifecycleOwner() {
        return (LifecycleOwner) mActivity;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }
}