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

public class OrderItemDtoMessageMarshaller implements MessageMarshaller<app.checkout.dto.OrderItemDto> {

    public java.lang.Class<app.checkout.dto.OrderItemDto> getJavaClass() {
        return app.checkout.dto.OrderItemDto.class;
    }

    public String getTypeName() {
        return "org.kie.kogito.app.OrderItemDto";
    }

    public app.checkout.dto.OrderItemDto readFrom(ProtoStreamReader reader) throws IOException {
        app.checkout.dto.OrderItemDto value = new app.checkout.dto.OrderItemDto();
        value.setId(reader.readString("id"));
        value.setPrice((java.math.BigDecimal) (reader.readObject("price", java.io.Serializable.class)));
        value.setProductId(reader.readString("productId"));
        value.setProductName(reader.readString("productName"));
        value.setQuantity(reader.readInt("quantity"));
        value.setSubtotal((java.math.BigDecimal) (reader.readObject("subtotal", java.io.Serializable.class)));
        return value;
    }

    public void writeTo(ProtoStreamWriter writer, app.checkout.dto.OrderItemDto t) throws IOException {
        writer.writeString("id", t.getId());
        writer.writeObject("price", (java.math.BigDecimal) (t.getPrice()), java.io.Serializable.class);
        writer.writeString("productId", t.getProductId());
        writer.writeString("productName", t.getProductName());
        writer.writeInt("quantity", t.getQuantity());
        writer.writeObject("subtotal", (java.math.BigDecimal) (t.getSubtotal()), java.io.Serializable.class);
    }
}
