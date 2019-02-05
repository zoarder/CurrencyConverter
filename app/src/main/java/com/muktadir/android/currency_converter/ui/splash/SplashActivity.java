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

package com.muktadir.android.currency_converter.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;

import com.muktadir.android.currency_converter.R;
import com.muktadir.android.currency_converter.data.helper.Constants;
import com.muktadir.android.currency_converter.data.local.currency_converter.ApiResponse;
import com.muktadir.android.currency_converter.databinding.ActivitySplashBinding;
import com.muktadir.android.currency_converter.ui.base.BaseActivity;
import com.muktadir.android.currency_converter.ui.main.MainActivity;
import com.muktadir.android.util.helper.Toaster;
import com.race604.drawable.wave.WaveDrawable;

import java.util.Objects;

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

public class SplashActivity extends BaseActivity<SplashMvpView, SplashPresenter> implements SplashMvpView {

    private static final int SPLASH_TIME = 0;
    private ActivitySplashBinding mBinding;
    private WaveDrawable mWaveDrawable;
    private int mWaveLoadingStatus = 0;
    private int mDataLoadingStatus = 0;
    private ApiResponse mApiResponse;

    public static void runActivity(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void startUI() {
        mBinding = (ActivitySplashBinding) getViewDataBinding();
        mWaveDrawable = new WaveDrawable(this, R.mipmap.ic_launcher);
        mWaveDrawable.setLevel(0);
        mWaveDrawable.setWaveAmplitude(0);
        mWaveDrawable.setWaveLength(600);
        mWaveDrawable.setWaveSpeed(0);
        mBinding.activitySplashLogoIv.setImageDrawable(mWaveDrawable);
        mWaveLoadingStatus = 1;
        startWaveLoading();
        new Handler(Objects.requireNonNull(this).getMainLooper()).postDelayed(() -> presenter.getCurrencyConverterData(), Constants.Integer.API_DELAY);
    }

    @Override
    protected void stopUI() {
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    private void startWaveLoading() {
        new Handler().postDelayed(new Runnable() {
            int mProgress = 0;

            @Override
            public void run() {
                new CountDownTimer(3000, 30) {

                    public void onTick(long millisUntilFinished) {
                        mWaveDrawable.setLevel(++mProgress * 100);
                    }

                    public void onFinish() {
                        mWaveDrawable.setLevel(++mProgress * 100);
                        mWaveLoadingStatus = 2;
                        goToNextPage();
                    }
                }.start();
            }
        }, SPLASH_TIME);
    }

    private void goToNextPage() {
        if (mWaveLoadingStatus == 2 && mDataLoadingStatus == 1) {
            MainActivity.runActivity(SplashActivity.this, mApiResponse);
            overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
        } else if (mDataLoadingStatus == 2) {
            Toaster.error(this, "App Data Loading Failed");
        } else {
            return;
        }
        finish();
    }

    @Override
    public void onCurrencyConverterDataLoadedSuccessful(ApiResponse apiResponse) {
        mApiResponse = apiResponse;
        mDataLoadingStatus = 1;
        goToNextPage();
    }

    @Override
    public void onCurrencyConverterDataLoadedFailed() {
        mDataLoadingStatus = 2;
        goToNextPage();
    }
}