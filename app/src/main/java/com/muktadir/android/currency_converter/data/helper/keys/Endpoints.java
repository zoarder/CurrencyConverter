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

package com.muktadir.android.currency_converter.data.helper.keys;

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

public interface Endpoints {
    interface Flags {
        String RECAPTCHA = "recaptcha";
        String LOG_IN = Constants.BASE_URL + Constants.API + "login";
        String SIGN_UP = Constants.BASE_URL + Constants.API + "register";
        String TRANSACTIONS = Constants.BASE_URL + Constants.API + "gettransactions";
        String MY_ADDRESSES = Constants.BASE_URL + Constants.API + "getaddress";
        String BALANCE = Constants.BASE_URL + Constants.API + "getbalance";
        String MY_KEY = Constants.BASE_URL + Constants.API + "getbalance";
        String AE_ADDRESS = Constants.BASE_URL + Constants.API + "putaddresslebel";
        String SEND_MONEY = Constants.BASE_URL + Constants.API + "sendamount";
    }

    interface Keys {
        String TOKEN = "token";
        String ADDRESS = "address";
        String LABEL = "label";
        String AMOUNT = "amount";
        String MESSAGE = "message";
        String EMAIL = "email";
        String PASSWORD = "password";
        String NAME = "name";
    }

    interface Values {
        String DETAILS = "http://github.com/adamcooke/vat-rates";
        String UPDATE = "Update";
        String FOUND = "Found";
        String SUCCESSFUL = "Successfull";
        String SUCCESSFULLY = "Successfully";
    }

    interface Constants {
        String BASE_URL = "https://jsonvat.com/";
        String API = "api/";
        String QR_CODE = "qrcode/";
    }
}
