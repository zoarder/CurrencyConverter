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

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

public class Rate implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("periods")
    @Expose
    private List<Period> periods = new ArrayList<Period>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rate() {
    }

    /**
     * 
     * @param periods
     * @param name
     * @param countryCode
     * @param code
     */
    public Rate(String name, String code, String countryCode, List<Period> periods) {
        super();
        this.name = name;
        this.code = code;
        this.countryCode = countryCode;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("code", code).append("countryCode", countryCode).append("periods", periods).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(periods).append(name).append(countryCode).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rate) == false) {
            return false;
        }
        Rate rhs = ((Rate) other);
        return new EqualsBuilder().append(periods, rhs.periods).append(name, rhs.name).append(countryCode, rhs.countryCode).append(code, rhs.code).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.code);
        dest.writeString(this.countryCode);
        dest.writeTypedList(this.periods);
    }

    protected Rate(Parcel in) {
        this.name = in.readString();
        this.code = in.readString();
        this.countryCode = in.readString();
        this.periods = in.createTypedArrayList(Period.CREATOR);
    }

    public static final Parcelable.Creator<Rate> CREATOR = new Parcelable.Creator<Rate>() {
        @Override
        public Rate createFromParcel(Parcel source) {
            return new Rate(source);
        }

        @Override
        public Rate[] newArray(int size) {
            return new Rate[size];
        }
    };
}
