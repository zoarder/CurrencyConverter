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

package com.muktadir.android.currency_converter.data.local.installedapps;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.os.Parcel;
import android.os.Parcelable;

import com.muktadir.android.currency_converter.data.local.base.RoomEntity;
import com.muktadir.android.currency_converter.data.local.dbstorage.ColumnNames;
import com.muktadir.android.currency_converter.data.local.dbstorage.TableNames;

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

@Entity(tableName = TableNames.MESH_SHARED_APPS, indices = {@Index(value = {ColumnNames.PACKAGE_NAME}, unique = true)})
public class SharedAppsEntity extends RoomEntity implements Parcelable {


    public static final Parcelable.Creator<SharedAppsEntity> CREATOR = new Parcelable.Creator<SharedAppsEntity>() {
        @Override
        public SharedAppsEntity createFromParcel(Parcel source) {
            return new SharedAppsEntity(source);
        }

        @Override
        public SharedAppsEntity[] newArray(int size) {
            return new SharedAppsEntity[size];
        }
    };
    @ColumnInfo(name = ColumnNames.APP_NAME)
    private String appName;
    @ColumnInfo(name = ColumnNames.APPLICATION_ID)
    private String applicationId;
    @ColumnInfo(name = ColumnNames.SHORT_DESCRIPTION)
    private String shortDescription;
    @ColumnInfo(name = ColumnNames.SHORT_DESCRIPTION_WITHOUT_PREPOSITION)
    private String shortDescriptionWithoutPreposition;
    @ColumnInfo(name = ColumnNames.DETAILED_DESCRIPTION)
    private String detailedDescription;
    @ColumnInfo(name = ColumnNames.LOGO_URL)
    private String logoUrl;
    @ColumnInfo(name = ColumnNames.LOGO_IMAGE)
    private String logoImage; // Base64String
    @ColumnInfo(name = ColumnNames.CONTENT_RATING)
    private String contentRating;
    @ColumnInfo(name = ColumnNames.INSTALLED_COUNT)
    private int installCount;
    @ColumnInfo(name = ColumnNames.STORE_NAME)
    private String storeName;
    @ColumnInfo(name = ColumnNames.STORE_ID)
    private String storeId;
    @ColumnInfo(name = ColumnNames.STORE_LOGO_URL)
    private String storeLogoUrl;
    @ColumnInfo(name = ColumnNames.STORE_LOGO)
    private String storeLogo;  //Base64String
    @ColumnInfo(name = ColumnNames.PACKAGE_NAME)
    private String packagaeName;
    @ColumnInfo(name = ColumnNames.TAGS)
    private String tags;
    @ColumnInfo(name = ColumnNames.REQUESTED_VIA_MESH)
    private int requestedViaMesh;  // search counter, might be required for sorting search result
    @ColumnInfo(name = ColumnNames.IS_SHARED_ON_MESH)
    private boolean isSharedOnMesh;
    @ColumnInfo(name = ColumnNames.IS_SYNCED)
    private boolean isSynced; // is all data  up to date with internet
    @ColumnInfo(name = ColumnNames.IS_RM_APPS)
    private boolean isRmApps; // is it currency_converter apps
    @ColumnInfo(name = ColumnNames.APK_SIZE)
    private long apkSize; // is it currency_converter apps


    SharedAppsEntity() {

    }

    private SharedAppsEntity(Parcel in) {
        this.appName = in.readString();
        this.applicationId = in.readString();
        this.shortDescription = in.readString();
        this.detailedDescription = in.readString();
        this.logoUrl = in.readString();
        this.logoImage = in.readString();
        this.contentRating = in.readString();
        this.installCount = in.readInt();
        this.storeName = in.readString();
        this.storeId = in.readString();
        this.storeLogoUrl = in.readString();
        this.storeLogo = in.readString();
        this.packagaeName = in.readString();
        this.tags = in.readString();
        this.requestedViaMesh = in.readInt();
        this.isSharedOnMesh = in.readByte() != 0;
        this.isSynced = in.readByte() != 0;
        this.isRmApps = in.readByte() != 0;
        this.apkSize = in.readLong();
    }

    String getAppName() {
        return appName;
    }

    void setAppName(String appName) {
        this.appName = appName;
    }

    String getApplicationId() {
        return applicationId;
    }

    void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    String getShortDescription() {
        return shortDescription;
    }

    void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    String getShortDescriptionWithoutPreposition() {
        return shortDescriptionWithoutPreposition;
    }

    void setShortDescriptionWithoutPreposition(String text) {
        this.shortDescriptionWithoutPreposition = text;
    }

    String getDetailedDescription() {
        return detailedDescription;
    }

    void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    String getLogoUrl() {
        return logoUrl;
    }

    void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    String getLogoImage() {
        return logoImage;
    }

    void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    String getContentRating() {
        return contentRating;
    }

    void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    int getInstallCount() {
        return installCount;
    }

    void setInstallCount(int installCount) {
        this.installCount = installCount;
    }

    String getStoreName() {
        return storeName;
    }

    void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    String getStoreId() {
        return storeId;
    }

    void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    String getStoreLogoUrl() {
        return storeLogoUrl;
    }

    void setStoreLogoUrl(String storeLogoUrl) {
        this.storeLogoUrl = storeLogoUrl;
    }

    String getStoreLogo() {
        return storeLogo;
    }

    void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    String getPackagaeName() {
        return packagaeName;
    }

    void setPackagaeName(String packagaeName) {
        this.packagaeName = packagaeName;
    }

    String getTags() {
        return tags;
    }

    void setTags(String tags) {
        this.tags = tags;
    }

    int getRequestedViaMesh() {
        return requestedViaMesh;
    }

    void setRequestedViaMesh(int requestedViaMesh) {
        this.requestedViaMesh = requestedViaMesh;
    }

    boolean isSharedOnMesh() {
        return isSharedOnMesh;
    }

    void setSharedOnMesh(boolean sharedOnMesh) {
        isSharedOnMesh = sharedOnMesh;
    }

    boolean isSynced() {
        return isSynced;
    }

    void setSynced(boolean synced) {
        isSynced = synced;
    }

    boolean isRmApps() {
        return isRmApps;
    }

    void setRmApps(boolean rmApps) {
        isRmApps = rmApps;
    }

    long getApkSize() {
        return this.apkSize;
    }

    void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appName);
        dest.writeString(this.applicationId);
        dest.writeString(this.shortDescription);
        dest.writeString(this.detailedDescription);
        dest.writeString(this.logoUrl);
        dest.writeString(this.logoImage);
        dest.writeString(this.contentRating);
        dest.writeInt(this.installCount);
        dest.writeString(this.storeName);
        dest.writeString(this.storeId);
        dest.writeString(this.storeLogoUrl);
        dest.writeString(this.storeLogo);
        dest.writeString(this.packagaeName);
        dest.writeString(this.tags);
        dest.writeInt(this.requestedViaMesh);
        dest.writeByte((byte) (isSharedOnMesh ? 1 : 0));
        dest.writeByte((byte) (isSynced ? 1 : 0));
        dest.writeByte((byte) (isRmApps ? 1 : 0));
        dest.writeLong(this.apkSize);
    }
}
