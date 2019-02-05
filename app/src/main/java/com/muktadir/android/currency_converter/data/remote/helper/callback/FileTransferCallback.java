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

package com.muktadir.android.currency_converter.data.remote.helper.callback;

import java.util.HashMap;
import java.util.Map;

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

public interface FileTransferCallback {
    void onFileUploadSuccess(String filePath);

    void onFileUploadProgress(String filePath, int percentage);

    void onFileUploadFailed(String filePath, int reason);

    /*
    void onStateChanged(int id, TransferState state);

    void onProgressChanged(int id, long bytesCurrent, long bytesTotal);

    void onError(int id, Exception ex);
    */

    enum TransferState {

        /**
         * This state represents a transfer that has been queued, but has not yet
         * started
         */
        WAITING,
        /**
         * This state represents a transfer that is currently uploading or
         * downloading data
         */
        IN_PROGRESS,
        /**
         * This state represents a transfer that is paused
         */
        PAUSED,
        /**
         * This state represents a transfer that has been resumed and queued for
         * execution, but has not started to actively transfer data
         */
        RESUMED_WAITING,
        /**
         * This state represents a transfer that is completed
         */
        COMPLETED,
        /**
         * This state represents a transfer that is canceled
         */
        CANCELED,
        /**
         * This state represents a transfer that has failed
         */
        FAILED,

        /**
         * This state represents a transfer that is currently on hold, waiting for
         * the network to become available
         */
        WAITING_FOR_NETWORK,
        /**
         * This state represents a transfer that is a completed part of a multi-part
         * upload. This state is primarily used internally and there should be no
         * need to use this state.
         */
        PART_COMPLETED,
        /**
         * This state represents a transfer that has been requested to cancel, but
         * the service processing transfers has not yet fulfilled this request. This
         * state is primarily used internally and there should be no need to use
         * this state.
         */
        PENDING_CANCEL,
        /**
         * This state represents a transfer that has been requested to pause by the
         * client, but the service processing transfers has not yet fulfilled this
         * request. This state is primarily used internally and there should be no
         * need to use this state.
         */
        PENDING_PAUSE,
        /**
         * This state represents a transfer that has been requested to pause by the
         * client because the network has been loss, but the service processing
         * transfers has not yet fulfilled this request. This state is primarily
         * used internally and there should be no need to use this state.
         */
        PENDING_NETWORK_DISCONNECT,
        /**
         * This is an internal value used to detect if the current transfer is in an
         * unknown state
         */
        UNKNOWN;

        private static final Map<String, TransferState> MAP;

        static {
            MAP = new HashMap<>();
            for (final TransferState state : TransferState.values()) {
                MAP.put(state.toString(), state);
            }
        }


        /**
         * Returns the transfer state from string
         *
         * @param stateAsString state of the transfer represented as string.
         * @return the {@link TransferState}
         */
        public static TransferState getState(String stateAsString) {
            if (MAP.containsKey(stateAsString)) {
                return MAP.get(stateAsString);
            }

            return UNKNOWN;
        }
    }
}