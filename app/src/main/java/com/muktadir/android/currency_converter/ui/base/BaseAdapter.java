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

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

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

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private final List<T> mItemList;
    private ItemClickListener mItemClickListener;
    private ItemLongClickListener mItemLongClickListener;

    public BaseAdapter() {
        mItemList = new ArrayList<>();
    }

    abstract boolean isEqual(T left, T right);

    abstract BaseViewHolder newViewHolder(ViewGroup parent, int viewType);

    /**
     * Commit child fragment of BaseFragment on a frameLayout
     *
     * @param itemLongClickListener ItemLongClickListener object
     */
    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
    }

    /**
     * Commit child fragment of BaseFragment on a frameLayout
     *
     * @param itemClickListener AppItemClickListener object
     */
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return newViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        T itemData = getItem(position);

        ViewDataBinding viewDataBinding = holder.getViewDataBinding();

        holder.bind(itemData, viewDataBinding);
    }

    /**
     * Clear current item list and update UI
     *
     */
    public void clear() {
        mItemList.clear();
        notifyDataSetChanged();
    }

    /**
     * @return current item list
     */
    public List<T> getItems() {
        return mItemList;
    }


    /**
     * Remove a Item from list and update UI
     *
     * @param t T type object
     */
    public void removeItem(T t) {
        int toIndex = mItemList.indexOf(t);
        if (toIndex < 0 || toIndex >= mItemList.size()) return;
        mItemList.remove(toIndex);
        notifyDataSetChanged();
    }

    /**
     * @param position int value
     * @return get current Item based on Position
     */
    private T getItem(int position) {
        if (position < 0 || position >= mItemList.size()) return null;
        return mItemList.get(position);
    }

   /* public void addItem(T item) {

        int position = 0;
        addItem(item, position);

    }*/

    /**
     * @param item T type object
     * @return int value: current item inserted position in list
     */
    private int addItem(T item) {
        T tItem = findItem(item);

        if (tItem == null) {
            mItemList.add(item);
            notifyItemInserted(mItemList.size() - 1);
            return mItemList.size() - 1;
        }
        return updateItem(item, tItem);
    }

    /**
     * @param items List<T> type object list
     */
    public void addItem(List<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }

    /**
     * @param item     T type object
     * @param position int value of position where value will be inserted
     */
    public void addItemToPosition(T item, int position) {
        mItemList.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * @param item T type object
     * @return if found then item from list otherwise null
     */
    private T findItem(T item) {
        for (T tItem : mItemList) {
            if (isEqual(item, tItem)) {
                return tItem;
            }
        }
        return null;
    }

    /**
     * @param items List<T> type object list
     */
    public void addItems(List<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }

    /**
     * @param oldItem T type object
     * @param newItem T type object
     * @return int value: newItem position in list
     */
    private int updateItem(T oldItem, T newItem) {
        int toIndex = mItemList.indexOf(newItem);
        mItemList.set(toIndex, oldItem);
        notifyItemChanged(toIndex);
        return toIndex;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public ViewDataBinding inflate(ViewGroup viewGroup, int item_layout) {
        return DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), item_layout, viewGroup, false);
    }
}