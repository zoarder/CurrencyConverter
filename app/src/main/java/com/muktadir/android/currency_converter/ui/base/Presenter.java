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

import android.arch.lifecycle.Lifecycle;

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

public interface Presenter<V extends MvpView> {

    /**
     * Called when MvpView type object attach to presenter
     *
     * @param mvpView The MvpView that have to attach.
     */
    void attachView(V mvpView);

    /**
     * Called when MvpView type object detach from presenter
     */
    void detachView();

    /**
     * Called when Lifecycle type object attach to presenter
     *
     * @param lifecycle The Lifecycle that have to attach.
     */
    void attachLifecycle(Lifecycle lifecycle);

    /**
     * Called when Lifecycle type object detach from presenter
     */
    void detachLifecycle(Lifecycle lifecycle);

    /**
     * Called when presenter type object creation done
     */
    void onPresenterCreated();

    /**
     * Called when presenter type object destroy done
     */
    void onPresenterDestroy();
}
