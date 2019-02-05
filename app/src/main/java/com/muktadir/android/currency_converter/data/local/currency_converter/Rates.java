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

public class Rates implements Parcelable {

    @SerializedName("super_reduced")
    @Expose
    private double superReduced;
    @SerializedName("reduced")
    @Expose
    private double reduced;
    @SerializedName("standard")
    @Expose
    private double standard;
    @SerializedName("reduced1")
    @Expose
    private double reduced1;
    @SerializedName("reduced2")
    @Expose
    private double reduced2;
    @SerializedName("parking")
    @Expose
    private double parking;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rates() {
    }

    /**
     * 
     * @param parking
     * @param standard
     * @param reduced
     * @param superReduced
     * @param reduced2
     * @param reduced1
     */
    public Rates(double superReduced, double reduced, double standard, double reduced1, double reduced2, double parking) {
        super();
        this.superReduced = superReduced;
        this.reduced = reduced;
        this.standard = standard;
        this.reduced1 = reduced1;
        this.reduced2 = reduced2;
        this.parking = parking;
    }

    public double getSuperReduced() {
        return superReduced;
    }

    public void setSuperReduced(double superReduced) {
        this.superReduced = superReduced;
    }

    public double getReduced() {
        return reduced;
    }

    public void setReduced(double reduced) {
        this.reduced = reduced;
    }

    public double getStandard() {
        return standard;
    }

    public void setStandard(double standard) {
        this.standard = standard;
    }

    public double getReduced1() {
        return reduced1;
    }

    public void setReduced1(double reduced1) {
        this.reduced1 = reduced1;
    }

    public double getReduced2() {
        return reduced2;
    }

    public void setReduced2(double reduced2) {
        this.reduced2 = reduced2;
    }

    public double getParking() {
        return parking;
    }

    public void setParking(double parking) {
        this.parking = parking;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("superReduced", superReduced).append("reduced", reduced).append("standard", standard).append("reduced1", reduced1).append("reduced2", reduced2).append("parking", parking).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(parking).append(standard).append(reduced).append(superReduced).append(reduced2).append(reduced1).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rates) == false) {
            return false;
        }
        Rates rhs = ((Rates) other);
        return new EqualsBuilder().append(parking, rhs.parking).append(standard, rhs.standard).append(reduced, rhs.reduced).append(superReduced, rhs.superReduced).append(reduced2, rhs.reduced2).append(reduced1, rhs.reduced1).isEquals();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.superReduced);
        dest.writeDouble(this.reduced);
        dest.writeDouble(this.standard);
        dest.writeDouble(this.reduced1);
        dest.writeDouble(this.reduced2);
        dest.writeDouble(this.parking);
    }

    protected Rates(Parcel in) {
        this.superReduced = in.readDouble();
        this.reduced = in.readDouble();
        this.standard = in.readDouble();
        this.reduced1 = in.readDouble();
        this.reduced2 = in.readDouble();
        this.parking = in.readDouble();
    }

    public static final Parcelable.Creator<Rates> CREATOR = new Parcelable.Creator<Rates>() {
        @Override
        public Rates createFromParcel(Parcel source) {
            return new Rates(source);
        }

        @Override
        public Rates[] newArray(int size) {
            return new Rates[size];
        }
    };
}
