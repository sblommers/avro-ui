/*
 * Copyright 2014-2015 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.avro.ui.gwt.client.util;

import org.kaaproject.avro.ui.gwt.client.widget.BusyPopup;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class BusyAsyncCallback<T> implements AsyncCallback<T> {
    
    public BusyAsyncCallback() {
        BusyPopup.showPopup();
    }

    @Override
    public void onFailure(Throwable caught) {
        BusyPopup.hidePopup();
        onFailureImpl(caught);
    }

    @Override
    public void onSuccess(T result) {
        BusyPopup.hidePopup();
        onSuccessImpl(result);
    }
    
    public abstract void onFailureImpl(Throwable caught);

    public abstract void onSuccessImpl(T result);
}
