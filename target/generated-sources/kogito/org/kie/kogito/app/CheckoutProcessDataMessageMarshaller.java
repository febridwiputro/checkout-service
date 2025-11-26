/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.app;

import java.io.IOException;
import org.infinispan.protostream.MessageMarshaller;

public class CheckoutProcessDataMessageMarshaller implements MessageMarshaller<app.checkout.bpm.CheckoutProcessData> {

    public java.lang.Class<app.checkout.bpm.CheckoutProcessData> getJavaClass() {
        return app.checkout.bpm.CheckoutProcessData.class;
    }

    public String getTypeName() {
        return "org.kie.kogito.app.CheckoutProcessData";
    }

    public app.checkout.bpm.CheckoutProcessData readFrom(ProtoStreamReader reader) throws IOException {
        app.checkout.bpm.CheckoutProcessData value = new app.checkout.bpm.CheckoutProcessData();
        value.setErrorMessage(reader.readString("errorMessage"));
        value.setOrder(reader.readObject("order", app.checkout.dto.OrderResponseDto.class));
        value.setOrderId(reader.readString("orderId"));
        value.setPaymentSuccess(reader.readBoolean("paymentSuccess"));
        value.setRequest(reader.readObject("request", app.checkout.dto.CheckoutRequestDto.class));
        value.setStockAvailable(reader.readBoolean("stockAvailable"));
        return value;
    }

    public void writeTo(ProtoStreamWriter writer, app.checkout.bpm.CheckoutProcessData t) throws IOException {
        writer.writeString("errorMessage", t.getErrorMessage());
        writer.writeObject("order", t.getOrder(), app.checkout.dto.OrderResponseDto.class);
        writer.writeString("orderId", t.getOrderId());
        writer.writeBoolean("paymentSuccess", t.isPaymentSuccess());
        writer.writeObject("request", t.getRequest(), app.checkout.dto.CheckoutRequestDto.class);
        writer.writeBoolean("stockAvailable", t.isStockAvailable());
    }
}
