/*
 * Copyright (C) 2017 NURDCODER
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://nurdcoder.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.muktadir.android.currency_converter.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;

import com.muktadir.android.currency_converter.R;
import com.muktadir.android.currency_converter.data.local.currency_converter.ApiResponse;
import com.muktadir.android.currency_converter.data.local.currency_converter.Period;
import com.muktadir.android.currency_converter.data.local.currency_converter.Rate;
import com.muktadir.android.currency_converter.databinding.ActivityMainBinding;
import com.muktadir.android.currency_converter.ui.base.BaseActivity;

/**
 * ****************************************************************************
 * * Copyright © 2018 W3 Engineers Ltd., All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@nurdcoder.com
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

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView {

    private ActivityMainBinding mBinding;
    private ApiResponse mApiResponse;
    private double mSelectedTax = 0;

    private AlertDialog.Builder mCountryDialog;
    private AlertDialog.Builder mTaxPeriodDialog;

    private int mSelectedCountry;
    private int mTempSelectedCountry;

    private int mSelectedTaxPeriod;
    private int mTempSelectedTaxPeriod;

    private ArrayAdapter<String> mCountryArrayAdapter;
    private ArrayAdapter<String> mTaxPeriodArrayAdapter;

    public static void runActivity(Context context, ApiResponse apiResponse) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("data", apiResponse);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void startUI() {
        mBinding = (ActivityMainBinding) getViewDataBinding();
        mSelectedCountry = 0;

        Bundle b = getIntent().getExtras();
        if (b != null) {
            mApiResponse = b.getParcelable("data");
        }

        mCountryDialog = new AlertDialog.Builder(
                this);
        mCountryArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.select_dialog_singlechoice);

        for (Rate rate : mApiResponse.getRates()) {
            mCountryArrayAdapter.add(rate.getName());
        }
        mCountryDialog
                .setTitle(R.string.select_a_country)
                .setPositiveButton(
                        getString(
                                R.string.dialog_ok),
                        (dialog, which) -> {
                            if (mSelectedCountry != mTempSelectedCountry) {
                                mSelectedCountry = mTempSelectedCountry;
                                changeCountry();
                            }
                            dialog.dismiss();
                        })
                .setNegativeButton(
                        getString(
                                R.string.dialog_cancel),
                        (dialog, which) -> dialog.dismiss());

        mTaxPeriodDialog = new AlertDialog.Builder(
                this);
        mTaxPeriodArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.select_dialog_singlechoice);

        mTaxPeriodDialog
                .setTitle(R.string.select_a_tax_period)
                .setPositiveButton(
                        getString(
                                R.string.dialog_ok),
                        (dialog, which) -> {
                            if (mSelectedTaxPeriod != mTempSelectedTaxPeriod) {
                                mSelectedTaxPeriod = mTempSelectedTaxPeriod;
                                changeTaxPeriod();
                            }
                            dialog.dismiss();
                        })
                .setNegativeButton(
                        getString(
                                R.string.dialog_cancel),
                        (dialog, which) -> dialog.dismiss());

        changeCountry();
        setClickListener(mBinding.activityMainCountryValueTv, mBinding.activityMainTaxPeriodValueTv);

        mBinding.activityMainTaxTypeRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {

                case R.id.activity_main_tax_type_standard_rb:
                    mSelectedTax = mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getStandard();
                    break;

                case R.id.activity_main_tax_type_reduced_rb:
                    mSelectedTax = mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getReduced();
                    break;

                case R.id.activity_main_tax_type_reduced_1_rb:
                    mSelectedTax = mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getReduced1();
                    break;

                case R.id.activity_main_tax_type_reduced_2_rb:
                    mSelectedTax = mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getReduced2();
                    break;

                case R.id.activity_main_tax_type_super_reduced_rb:
                    mSelectedTax = mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getSuperReduced();
                    break;

                case R.id.activity_main_tax_type_parking_rb:
                    mSelectedTax = mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getParking();
                    break;
            }
            changeTaxType(mSelectedTax);
        });

        mBinding.activityMainAmountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeTaxType(mSelectedTax);
            }
        });
    }

    private void changeTaxType(double tax) {
        double amount = 0;

        try {
            amount = Double.parseDouble(mBinding.activityMainAmountEt.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (amount > 0) {
            mBinding.activityMainAmountWithTaxValueTv.setText(String.valueOf(amount + tax));
        } else {
            mBinding.activityMainAmountWithTaxValueTv.setText(String.valueOf(0));
        }
    }

    private void changeCountry() {
        mBinding.activityMainCountryValueTv.setText(mApiResponse.getRates().get(mSelectedCountry).getName());
        mSelectedTaxPeriod = 0;
        changeTaxPeriod();

        mTaxPeriodArrayAdapter.clear();
        for (Period period : mApiResponse.getRates().get(mSelectedCountry).getPeriods()) {
            mTaxPeriodArrayAdapter.add(period.getEffectiveFrom());
        }
    }

    private void changeTaxPeriod() {
        mBinding.activityMainTaxPeriodValueTv.setText(mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getEffectiveFrom());
        mBinding.activityMainTaxTypeStandardRb.setChecked(true);
        mBinding.activityMainTaxTypeReducedRb.setVisibility(mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getReduced() == 0 ? View.GONE : View.VISIBLE);

        mBinding.activityMainTaxTypeReduced1Rb.setVisibility(mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getReduced1() == 0 ? View.GONE : View.VISIBLE);
        mBinding.activityMainTaxTypeReduced2Rb.setVisibility(mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getReduced2() == 0 ? View.GONE : View.VISIBLE);
        mBinding.activityMainTaxTypeSuperReducedRb.setVisibility(mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getSuperReduced() == 0 ? View.GONE : View.VISIBLE);

        mBinding.activityMainTaxTypeParkingRb.setVisibility(mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getParking() == 0 ? View.GONE : View.VISIBLE);
        mSelectedTax = mApiResponse.getRates().get(mSelectedCountry).getPeriods().get(mSelectedTaxPeriod).getRates().getStandard();
        changeTaxType(mSelectedTax);
    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.activity_main_country_value_tv:
                mTempSelectedCountry = mSelectedCountry;

                mCountryDialog.setSingleChoiceItems(
                        mCountryArrayAdapter,
                        mSelectedCountry,
                        (dialog, which) -> mTempSelectedCountry = which);

                mCountryDialog.show();
                break;
            case R.id.activity_main_tax_period_value_tv:
                mTempSelectedTaxPeriod = mSelectedTaxPeriod;

                mTaxPeriodDialog.setSingleChoiceItems(
                        mTaxPeriodArrayAdapter,
                        mSelectedTaxPeriod,
                        (dialog, which) -> mTempSelectedTaxPeriod = which);

                mTaxPeriodDialog.show();
                break;
        }
    }
}
