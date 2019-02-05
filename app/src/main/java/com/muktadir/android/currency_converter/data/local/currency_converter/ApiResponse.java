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

package com.muktadir.android.currency_converter.data.local.currency_converter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

public class ApiResponse implements Parcelable {

    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("version")
    @Expose
    private int version;
    @SerializedName("rates")
    @Expose
    private List<Rate> rates = new ArrayList<Rate>();

    /**
     * No args constructor for use in serialization
     */
    public ApiResponse() {
    }

    /**
     * @param details
     * @param rates
     * @param version
     */
    public ApiResponse(String details, int version, List<Rate> rates) {
        super();
        this.details = details;
        this.version = version;
        this.rates = rates;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("details", details).append("version", version).append("rates", rates).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(details).append(rates).append(version).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ApiResponse) == false) {
            return false;
        }
        ApiResponse rhs = ((ApiResponse) other);
        return new EqualsBuilder().append(details, rhs.details).append(rates, rhs.rates).append(version, rhs.version).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.details);
        dest.writeInt(this.version);
        dest.writeList(this.rates);
    }

    protected ApiResponse(Parcel in) {
        this.details = in.readString();
        this.version = in.readInt();
        this.rates = new ArrayList<Rate>();
        in.readList(this.rates, Rate.class.getClassLoader());
    }

    public static final Parcelable.Creator<ApiResponse> CREATOR = new Parcelable.Creator<ApiResponse>() {
        @Override
        public ApiResponse createFromParcel(Parcel source) {
            return new ApiResponse(source);
        }

        @Override
        public ApiResponse[] newArray(int size) {
            return new ApiResponse[size];
        }
    };
}
