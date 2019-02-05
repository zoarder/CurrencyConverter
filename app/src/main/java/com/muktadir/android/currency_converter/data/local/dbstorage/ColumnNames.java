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

package com.muktadir.android.currency_converter.data.local.dbstorage;

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

public interface ColumnNames {
    //User
    String USER_ID = "mesh_id";
    String USER_NAME = "user_name";
    String USER_PASSWORD = "user_password";
    String USER_EMAIL = "user_email";
    String ID = "id";


    //installed app
    String APP_NAME = "app_name";
    String APPLICATION_ID = "application_id";
    String SHORT_DESCRIPTION = "short_description";
    String SHORT_DESCRIPTION_WITHOUT_PREPOSITION = "short_description_without_preposition";
    String DETAILED_DESCRIPTION = "detailed_description";
    String LOGO_URL = "logo_url";
    String LOGO_IMAGE = "logo_image";
    String CONTENT_RATING = "content_rating";
    String INSTALLED_COUNT = "install_count";
    String STORE_NAME = "store_name";
    String STORE_ID = "store_id";
    String STORE_LOGO_URL = "store_logo_url";
    String STORE_LOGO = "store_logo";
    String PACKAGE_NAME = "packagae_name";
    String TAGS = "tags";
    String REQUESTED_VIA_MESH = "requested_via_mesh";
    String IS_SHARED_ON_MESH = "is_shared_on_mesh";
    String IS_SYNCED = "is_synced";
    String IS_RM_APPS = "is_rm_apps";
    String APK_SIZE = "apk_size";


    // Mesh store app

    String MESH_STORE_ID = "mesh_store_id";
    String MESH_STORE_APP_NAME = "ms_appname";
    String MESH_STORE_PACKAGE_NAME = "ms_package_name";
    String MESH_SHORT_DESCRIPTION = "ms_short_des";
    String MESH_DETAILED_DESCRIPTION = "ms_detail_Des";
    String MESH_LOGO_URL = "ms_logo_url";
    String MESH_SCREEN_SHOTS = "ms_screen_shots";
    String MESH_PRICE = "ms_price";
    String MESH_INSTALL_COUNT = "ms_install_count";
    String MESH_CONTENT_RATING = "ms_content_rating";
    String MESH_TAGS = "ms_tags";
    String MESH_IS_RM_ENABLES = "is_rm_enabled";
    String MESH_APP_RATING = "app_ratings";
    String MESH_PACKAGE_INFO = "package_info";
    String MESH_DEVELOPER_INFO = "developer_info";
    String MESH_APP_INSTALLED = "app_installed_time";
    String MESH_APP_UPDATED = "app_updated_time";
    String MESH_SHARE_LINK = "shareLink";

    // Mesh store download quee

    String DOWNLOAD_APPID = "mesh_download_app_id";
    String DOWNLOAD_FILENAME = "mesh_download_file_name";
    String DOWNLOAD_ADDED_TIME = "mesh_download_added_time";
    String DOWNLOAD_APK = "mesh_download_apk_link";
    String DOWNLOAD_COMPLETED = "mesh_download_is_completed";
}