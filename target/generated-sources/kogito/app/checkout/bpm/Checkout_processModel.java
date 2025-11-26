/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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
package app.checkout.bpm;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import java.util.Map;
import java.util.HashMap;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;

@org.kie.kogito.codegen.Generated(value = "kogito-codegen", reference = "checkout_process", name = "Checkout_process", hidden = false)
public class Checkout_processModel implements org.kie.kogito.Model, MapInput, MapInputId, MapOutput, MappableToModel<Checkout_processModelOutput> {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "request")
    @javax.validation.Valid()
    private app.checkout.dto.CheckoutRequestDto request;

    public app.checkout.dto.CheckoutRequestDto getRequest() {
        return request;
    }

    public void setRequest(app.checkout.dto.CheckoutRequestDto request) {
        this.request = request;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "paymentSuccess")
    @javax.validation.Valid()
    private java.lang.Boolean paymentSuccess;

    public java.lang.Boolean getPaymentSuccess() {
        return paymentSuccess;
    }

    public void setPaymentSuccess(java.lang.Boolean paymentSuccess) {
        this.paymentSuccess = paymentSuccess;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "orderId")
    @javax.validation.Valid()
    private java.lang.String orderId;

    public java.lang.String getOrderId() {
        return orderId;
    }

    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "vars")
    @javax.validation.Valid()
    private app.checkout.bpm.CheckoutProcessData vars;

    public app.checkout.bpm.CheckoutProcessData getVars() {
        return vars;
    }

    public void setVars(app.checkout.bpm.CheckoutProcessData vars) {
        this.vars = vars;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "stockAvailable")
    @javax.validation.Valid()
    private java.lang.Boolean stockAvailable;

    public java.lang.Boolean getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(java.lang.Boolean stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    @Override()
    public Checkout_processModelOutput toModel() {
        Checkout_processModelOutput result = new Checkout_processModelOutput();
        result.setId(getId());
        result.setRequest(getRequest());
        result.setPaymentSuccess(getPaymentSuccess());
        result.setOrderId(getOrderId());
        result.setVars(getVars());
        result.setStockAvailable(getStockAvailable());
        return result;
    }
}
