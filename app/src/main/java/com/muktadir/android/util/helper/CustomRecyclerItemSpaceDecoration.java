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

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class CustomRecyclerItemSpaceDecoration extends RecyclerView.ItemDecoration {

    private static final int VERTICAL = 0;
    private static final int HORIZONTAL = 1;
    private static final int GRID = 2;

    private int mVerticalSpace = 0;
    private int mHorizontalSpace = 0;
    private int mVerticalStartSpace = 0;
    private int mVerticalEndSpace = 0;
    private int mHorizontalStartSpace = 0;
    private int mHorizontalEndSpace = 0;


    public CustomRecyclerItemSpaceDecoration() {
    }

    public CustomRecyclerItemSpaceDecoration(int mVerticalSpace, int mHorizontalSpace, int mVerticalStartSpace, int mVerticalEndSpace, int mHorizontalStartSpace, int mHorizontalEndSpace) {
        this.mVerticalSpace = mVerticalSpace;
        this.mHorizontalSpace = mHorizontalSpace;
        this.mVerticalStartSpace = mVerticalStartSpace;
        this.mVerticalEndSpace = mVerticalEndSpace;
        this.mHorizontalStartSpace = mHorizontalStartSpace;
        this.mHorizontalEndSpace = mHorizontalEndSpace;
    }

    public void setVerticalSpace(int mVerticalSpace) {
        this.mVerticalSpace = mVerticalSpace;
    }

    public void setHorizontalSpace(int mHorizontalSpace) {
        this.mHorizontalSpace = mHorizontalSpace;
    }

    public void setVerticalStartSpace(int mVerticalStartSpace) {
        this.mVerticalStartSpace = mVerticalStartSpace;
    }

    public void setVerticalEndSpace(int mVerticalEndSpace) {
        this.mVerticalEndSpace = mVerticalEndSpace;
    }

    public void setHorizontalStartSpace(int mHorizontalStartSpace) {
        this.mHorizontalStartSpace = mHorizontalStartSpace;
    }

    public void setHorizontalEndSpace(int mHorizontalEndSpace) {
        this.mHorizontalEndSpace = mHorizontalEndSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        int itemCount = state.getItemCount();

        switch (resolveDisplayMode(parent.getLayoutManager())) {
            case VERTICAL:
                outRect.left = mHorizontalStartSpace;
                outRect.right = mHorizontalEndSpace;
                outRect.bottom = mVerticalSpace;
                if (position == 0) {
                    outRect.top = mVerticalStartSpace;
                }
                if (position == itemCount - 1) {
                    outRect.bottom = mVerticalEndSpace;
                }
                break;
            case HORIZONTAL:
                outRect.top = mVerticalStartSpace;
                outRect.bottom = mVerticalEndSpace;
                outRect.right = mHorizontalSpace;
                if (position == 0) {
                    outRect.left = mHorizontalStartSpace;
                }
                if (position == itemCount - 1) {
                    outRect.right = mHorizontalEndSpace;
                }
                break;
            case GRID:
                int spanCount = ((GridLayoutManager) Objects.requireNonNull(parent.getLayoutManager())).getSpanCount();
                outRect.bottom = mVerticalSpace;
                outRect.right = mHorizontalSpace;
                if (position < spanCount) {
                    outRect.top = mVerticalStartSpace;
                }
                if (position % spanCount == 0) {
                    outRect.left = mHorizontalStartSpace;
                }

                if (position % spanCount == spanCount - 1) {
                    outRect.right = mHorizontalEndSpace;
                }
                if (position >= itemCount - spanCount) {
                    outRect.bottom = mVerticalEndSpace;
                }
                break;
        }
    }

    private int resolveDisplayMode(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) return GRID;
        if (layoutManager.canScrollHorizontally()) return HORIZONTAL;
        return VERTICAL;
    }
}