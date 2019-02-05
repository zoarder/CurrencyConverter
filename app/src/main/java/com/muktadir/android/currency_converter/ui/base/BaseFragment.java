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
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.muktadir.android.currency_converter.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

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

public abstract class BaseFragment<V extends MvpView, P extends BasePresenter<V>>
        extends Fragment implements MvpView, View.OnClickListener {

    /**
     * LifecycleRegistry is an implementation of Lifecycle that can handle multiple observers.
     * It is used by Fragments and Support Library Activities.
     * You can also directly use it if you have a custom LifecycleOwner.
     */
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    protected P presenter;
    private int mDefaultValue = -1;
    private ViewDataBinding mViewDataBinding;

    /*
     * Child class have to implement this method. On this method you will pass the layout file of current fragment
     * */
    protected abstract int getLayoutId();

    /**
     * If user wants to register eventbus (optional)
     *
     * @return boolean value for registering
     */
    protected boolean getEventBus() {
        return false;
    }

    /*
     * Child class can(optional) override this method. On this method you will pass the menu file of current fragment
     * */
    protected int getMenuId() {
        return mDefaultValue;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getMenuId() > mDefaultValue) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (getMenuId() > mDefaultValue) {
            inflater.inflate(getMenuId(), menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layoutId = getLayoutId();

        if (layoutId <= mDefaultValue) { // if default or invalid layout id, then no possibility to createDb view
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        return updateLayoutView(inflater, layoutId, container);
    }

    private View updateLayoutView(LayoutInflater inflater, int layoutId, ViewGroup container) {
        View view = null;

        try {
            mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);

            view = mViewDataBinding.getRoot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mViewDataBinding == null) {
            view = inflater.inflate(layoutId, container, false);
            ButterKnife.bind(Objects.requireNonNull(getActivity()));
        }

        return view;
    }

    @CallSuper
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    }

    @Override
    public void onStart() {
        super.onStart();

        //registering eventbus
        if (getEventBus() && !EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        presenter.setActivity(getActivity());

        this.startUI();
    }

    @Override
    public void onStop() {

        //unregistering eventbus
        if (getEventBus() && EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);

        super.onStop();
    }

    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    */

    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
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

    /*
        @CallSuper
        @Override
        public void onDestroyView() {
            super.onDestroyView();

            if (presenter != null) {
                presenter.detachLifecycle(getLifecycle());
                presenter.detachView();
            }
        }
    */

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stopUI();

        if (presenter != null) {
            presenter.detachLifecycle(getLifecycle());
            presenter.detachView();
        }
    }

    private BaseActivity getBaseActivity() {
        return ((BaseActivity) getActivity());
    }

    private boolean isBaseActivityInstance() {
        return BaseActivity.class.isInstance(getActivity());
    }

    /**
     * To set title on toolbar
     *
     * @param title string value
     */
    protected void setTitle(String title) {
        if (isBaseActivityInstance()) getBaseActivity().setTitle(title);
    }

    /**
     * To set sub title on toolbar
     *
     * @param subtitle string value
     */
    protected void setSubtitle(String subtitle) {
        if (isBaseActivityInstance()) getBaseActivity().setSubtitle(subtitle);
    }

    /**
     * To set both title and subtitle in toolbar
     *
     * @param title    string value
     * @param subtitle string value
     */
    public void setToolbarText(String title, String subtitle) {
        if (isBaseActivityInstance()) getBaseActivity().setToolbarText(title, subtitle);
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

        //if (isBaseActivityInstance()) getBaseActivity().setClickListener(views);
    }

    protected RecyclerView initRecyclerView(Context context, RecyclerView recyclerView, BaseAdapter adapter) {
        if (recyclerView == null) return null;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }

    protected RecyclerView.Adapter getAdapter(RecyclerView recyclerView) {
        return recyclerView.getAdapter();
    }

    /**
     * Commit child fragment of BaseFragment on a frameLayout
     *
     * @param viewId        int value
     * @param childFragment ChildFragment object
     */
    protected void commitChildFragment(int viewId, BaseFragment childFragment) {

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(viewId, childFragment).commit();

    }

    /**
     * To set animation on any view
     *
     * @param views View as params
     */
    protected void setAnimation(View... views) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.grow_effect);

        for (View view : views) {
            view.startAnimation(animation);
        }

        //if (isBaseActivityInstance()) getBaseActivity().setAnimation(views);
    }

    /*
     * Child class have to implement this Generic method and will pass the current new object of presenter
     * */
    protected abstract P initPresenter();
}