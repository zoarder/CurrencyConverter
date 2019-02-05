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

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.muktadir.android.currency_converter.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

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

public abstract class BaseActivity<V extends MvpView, P extends BasePresenter<V>>
        extends AppCompatActivity implements MvpView, View.OnClickListener {

    /**
     * LifecycleRegistry is an implementation of Lifecycle that can handle multiple observers.
     * It is used by Fragments and Support Library Activities.
     * You can also directly use it if you have a custom LifecycleOwner.
     */
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    protected P presenter;
    //FixMe: have to enable for commit Fragment in Activity
    protected BaseFragment mBaseCurrentFragment;
    private ViewDataBinding mViewDataBinding;
    private Menu mMenu;
    private int mDefaultValue = -1;

    /*
     * Start Activity with intent
     * */
    protected static void runCurrentActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    /**
     * its built in method in Fragment Activity
     * that is extends by AppCompatActivity
     */
    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }

    /**
     * If user wants to register eventbus (optional)
     *
     * @return boolean value for registering
     */
    protected boolean getEventBus() {
        return false;
    }

    /*
     * Child class have to implement this method. On this method you will pass the layout file of current activity
     * */
    protected abstract int getLayoutId();

    /*
     * Child class can(optional) override this method. On this method you will pass the toolbar id of current layout
     * */
    protected int getToolbarId() {
        return mDefaultValue;
    }

    /*
     * Child class can(optional) override this method. On this method you will pass the menu file of current activity
     * */
    protected int getMenuId() {
        return mDefaultValue;
    }

    /*
     * Child class can(optional) override this method. On this method you will pass the color id
     * */
    protected int statusBarColor() {
        return mDefaultValue;
    }

    /*FixMe: have to enable for on Resume state
    @Override
    protected void onResume() {
        super.onResume();
        onResumeUI();
    }
    protected abstract void onResumeUI();
    */

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutId();
        if (layoutId > mDefaultValue) {

            updateLayoutView(layoutId);

            BaseViewModel<V, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
            boolean isPresenterCreated = false;

            if (viewModel.getPresenter() == null) {
                viewModel.setPresenter(initPresenter());
                isPresenterCreated = true;
            }

            presenter = viewModel.getPresenter();
            presenter.attachLifecycle(getLifecycle());
            presenter.attachView((V) this);

            if (isPresenterCreated) presenter.onPresenterCreated();

            int toolbarId = getToolbarId();

            setStatusBarColor();

            if (toolbarId > mDefaultValue) {
                Toolbar toolbar = findViewById(toolbarId);

                if (toolbar != null) {
                    setSupportActionBar(toolbar);
                }

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setDisplayShowHomeEnabled(true);
                }
            }
        }

        this.startUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //registering eventbus
        if (getEventBus() && !EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        presenter.setActivity(this);
    }

    private void updateLayoutView(int layoutId) {
        try {
            mViewDataBinding = DataBindingUtil.setContentView(this, layoutId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mViewDataBinding == null) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }
    }

    /*
     * Child class have to implement this method. This method run on onStart lifecycle
     * */
    protected abstract void startUI();

    /*
     * Child class have to implement this method. This method run on onDestroy lifecycle
     * */
    protected abstract void stopUI();

    /*
     * Return current viewDataBinding
     * */
    protected ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    private void setStatusBarColor() {

        int statusBarColor = statusBarColor();

        if (statusBarColor > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
                window.setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
            }
        }
    }

    @Override
    public void onClick(View view) {
    }

    /*
     * To get the current menu. It will return current menu if you set it. Otherwise return null.
     * */
    protected Menu getMenu() {
        return mMenu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() > mDefaultValue) {
            getMenuInflater().inflate(getMenuId(), menu);
            this.mMenu = menu;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void refreshMenu() {
        supportInvalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.stopUI();

        //unregistering eventbus
        if (getEventBus() && EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);

        if (presenter != null) {
            presenter.detachLifecycle(getLifecycle());
            presenter.detachView();
        }
    }

    /*
     * Child class have to implement this Generic method and will pass the current new object of presenter
     * */
    protected abstract P initPresenter();

    /**
     * To set title on toolbar
     *
     * @param title string value
     */
    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    /**
     * To set sub title on toolbar
     *
     * @param subtitle string value
     */
    protected void setSubtitle(String subtitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }

    /**
     * To set both title and subtitle in toolbar
     *
     * @param title    string value
     * @param subtitle string value
     */
    protected void setToolbarText(String title, String subtitle) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setSubtitle(subtitle);
        }
    }

    /**
     * To set click listener on any view, You can pass multiple view at a time
     *
     * @param views View as params
     */
    protected void setClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    /**
     * To set animation on any view
     *
     * @param views View as params
     */
    protected void setAnimation(View... views) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.grow_effect);

        for (View view : views) {
            view.startAnimation(animation);
        }
    }

    /**
     * Commit child fragment of BaseFragment on a frameLayout
     *
     * @param viewId       int value
     * @param baseFragment BaseFragment object
     */
    protected void commitFragment(int viewId, BaseFragment baseFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(viewId, baseFragment, baseFragment.getClass().getName())
                .commit();

        setCurrentFragment(baseFragment);
    }

    /*
     * Get current running fragment
     * */
    protected BaseFragment getCurrentFragment() {
        return mBaseCurrentFragment;
    }

    private void setCurrentFragment(BaseFragment baseFragment) {
        this.mBaseCurrentFragment = baseFragment;
    }
}
