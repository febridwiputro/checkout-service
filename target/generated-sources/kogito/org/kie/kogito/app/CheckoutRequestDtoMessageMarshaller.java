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

public class CheckoutRequestDtoMessageMarshaller implements MessageMarshaller<app.checkout.dto.CheckoutRequestDto> {

    public java.lang.Class<app.checkout.dto.CheckoutRequestDto> getJavaClass() {
        return app.checkout.dto.CheckoutRequestDto.class;
    }

    public String getTypeName() {
        return "org.kie.kogito.app.CheckoutRequestDto";
    }

    public app.checkout.dto.CheckoutRequestDto readFrom(ProtoStreamReader reader) throws IOException {
        app.checkout.dto.CheckoutRequestDto value = new app.checkout.dto.CheckoutRequestDto();
        value.setCustomerEmail(reader.readString("customerEmail"));
        value.setCustomerId(reader.readString("customerId"));
        value.setItems(reader.readCollection("items", new java.util.ArrayList(), app.checkout.dto.CartItemDto.class));
        value.setPaymentMethod(reader.readString("paymentMethod"));
        value.setShippingAddress(reader.readObject("shippingAddress", app.checkout.dto.ShippingAddressDto.class));
        value.setTotalAmount((java.math.BigDecimal) (reader.readObject("totalAmount", java.io.Serializable.class)));
        return value;
    }

    public void writeTo(ProtoStreamWriter writer, app.checkout.dto.CheckoutRequestDto t) throws IOException {
        writer.writeString("customerEmail", t.getCustomerEmail());
        writer.writeString("customerId", t.getCustomerId());
        writer.writeCollection("items", t.getItems(), app.checkout.dto.CartItemDto.class);
        writer.writeString("paymentMethod", t.getPaymentMethod());
        writer.writeObject("shippingAddress", t.getShippingAddress(), app.checkout.dto.ShippingAddressDto.class);
        writer.writeObject("totalAmount", (java.math.BigDecimal) (t.getTotalAmount()), java.io.Serializable.class);
    }
}
