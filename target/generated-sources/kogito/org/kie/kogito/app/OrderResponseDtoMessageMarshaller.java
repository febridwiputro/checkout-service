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

public class OrderResponseDtoMessageMarshaller implements MessageMarshaller<app.checkout.dto.OrderResponseDto> {

    public java.lang.Class<app.checkout.dto.OrderResponseDto> getJavaClass() {
        return app.checkout.dto.OrderResponseDto.class;
    }

    public String getTypeName() {
        return "org.kie.kogito.app.OrderResponseDto";
    }

    public app.checkout.dto.OrderResponseDto readFrom(ProtoStreamReader reader) throws IOException {
        app.checkout.dto.OrderResponseDto value = new app.checkout.dto.OrderResponseDto();
        value.setCreatedAt((java.time.LocalDateTime) (reader.readObject("createdAt", java.io.Serializable.class)));
        value.setCustomerEmail(reader.readString("customerEmail"));
        value.setCustomerId(reader.readString("customerId"));
        value.setId(reader.readString("id"));
        value.setItems(reader.readCollection("items", new java.util.ArrayList(), app.checkout.dto.OrderItemDto.class));
        value.setOrderNumber(reader.readString("orderNumber"));
        value.setOrderStatus(reader.readString("orderStatus"));
        value.setPaymentMethod(reader.readString("paymentMethod"));
        value.setPaymentStatus(reader.readString("paymentStatus"));
        value.setProcessInstanceId(reader.readString("processInstanceId"));
        value.setShippingAddress(reader.readString("shippingAddress"));
        value.setTotalAmount((java.math.BigDecimal) (reader.readObject("totalAmount", java.io.Serializable.class)));
        value.setUpdatedAt((java.time.LocalDateTime) (reader.readObject("updatedAt", java.io.Serializable.class)));
        return value;
    }

    public void writeTo(ProtoStreamWriter writer, app.checkout.dto.OrderResponseDto t) throws IOException {
        writer.writeObject("createdAt", (java.time.LocalDateTime) (t.getCreatedAt()), java.io.Serializable.class);
        writer.writeString("customerEmail", t.getCustomerEmail());
        writer.writeString("customerId", t.getCustomerId());
        writer.writeString("id", t.getId());
        writer.writeCollection("items", t.getItems(), app.checkout.dto.OrderItemDto.class);
        writer.writeString("orderNumber", t.getOrderNumber());
        writer.writeString("orderStatus", t.getOrderStatus());
        writer.writeString("paymentMethod", t.getPaymentMethod());
        writer.writeString("paymentStatus", t.getPaymentStatus());
        writer.writeString("processInstanceId", t.getProcessInstanceId());
        writer.writeString("shippingAddress", t.getShippingAddress());
        writer.writeObject("totalAmount", (java.math.BigDecimal) (t.getTotalAmount()), java.io.Serializable.class);
        writer.writeObject("updatedAt", (java.time.LocalDateTime) (t.getUpdatedAt()), java.io.Serializable.class);
    }
}
