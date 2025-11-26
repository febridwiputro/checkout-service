package app.checkout.bpm;

import app.checkout.bpm.Checkout_processModel;
import org.kie.api.definition.process.Process;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.drools.core.util.KieFunctions;
import org.jbpm.process.core.datatype.impl.type.StringDataType;
import org.jbpm.process.core.datatype.impl.type.BooleanDataType;

@javax.enterprise.context.ApplicationScoped()
@javax.inject.Named("checkout_process")
@io.quarkus.runtime.Startup()
public class Checkout_processProcess extends org.kie.kogito.process.impl.AbstractProcess<app.checkout.bpm.Checkout_processModel> {

    @javax.inject.Inject()
    public Checkout_processProcess(org.kie.kogito.app.Application app, org.kie.kogito.correlation.CorrelationService correlations, org.kie.kogito.process.ProcessInstancesFactory factory) {
        super(app, java.util.Arrays.asList(), correlations, factory);
        activate();
    }

    public Checkout_processProcess() {
    }

    @Override()
    public app.checkout.bpm.Checkout_processProcessInstance createInstance(app.checkout.bpm.Checkout_processModel value) {
        return new app.checkout.bpm.Checkout_processProcessInstance(this, value, this.createProcessRuntime());
    }

    public app.checkout.bpm.Checkout_processProcessInstance createInstance(java.lang.String businessKey, app.checkout.bpm.Checkout_processModel value) {
        return new app.checkout.bpm.Checkout_processProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    public app.checkout.bpm.Checkout_processProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.correlation.CompositeCorrelation correlation, app.checkout.bpm.Checkout_processModel value) {
        return new app.checkout.bpm.Checkout_processProcessInstance(this, value, businessKey, this.createProcessRuntime(), correlation);
    }

    @Override()
    public app.checkout.bpm.Checkout_processModel createModel() {
        return new app.checkout.bpm.Checkout_processModel();
    }

    public app.checkout.bpm.Checkout_processProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((app.checkout.bpm.Checkout_processModel) value);
    }

    public app.checkout.bpm.Checkout_processProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (app.checkout.bpm.Checkout_processModel) value);
    }

    public app.checkout.bpm.Checkout_processProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new app.checkout.bpm.Checkout_processProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public app.checkout.bpm.Checkout_processProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new app.checkout.bpm.Checkout_processProcessInstance(this, this.createModel(), wpi);
    }

    protected org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("checkout_process", true);
        factory.variable("vars", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(app.checkout.bpm.CheckoutProcessData.class), null, "customTags", null);
        factory.variable("request", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(app.checkout.dto.CheckoutRequestDto.class), null, "customTags", null);
        factory.variable("orderId", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), null, "customTags", null);
        factory.variable("stockAvailable", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.Boolean.class), null, "customTags", null);
        factory.variable("paymentSuccess", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.Boolean.class), null, "customTags", null);
        factory.name("checkout-process");
        factory.packageName("app.checkout.bpm");
        factory.dynamic(false);
        factory.version("1.0");
        factory.type("BPMN");
        factory.visibility("Public");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        org.jbpm.ruleflow.core.factory.StartNodeFactory<?> startNode1 = factory.startNode(1);
        startNode1.name("Start");
        startNode1.interrupting(true);
        startNode1.metaData("UniqueId", "StartEvent_1");
        startNode1.metaData("elementname", "Start");
        startNode1.metaData("x", 94);
        startNode1.metaData("width", 56);
        startNode1.metaData("y", 82);
        startNode1.metaData("height", 56);
        startNode1.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode2 = factory.actionNode(2);
        actionNode2.name("Validate Cart");
        actionNode2.action(kcontext -> {
            app.checkout.dto.CheckoutRequestDto request = (app.checkout.dto.CheckoutRequestDto) kcontext.getVariable("request");
            System.out.println("Step 1: Validating cart...");
            if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
                throw new IllegalArgumentException("Cart is empty or invalid");
            }
            System.out.println("Cart is valid");
        });
        actionNode2.metaData("UniqueId", "Task_ValidateCart");
        actionNode2.metaData("elementname", "Validate Cart");
        actionNode2.metaData("NodeType", "ScriptTask");
        actionNode2.metaData("x", 180);
        actionNode2.metaData("width", 100);
        actionNode2.metaData("y", 85);
        actionNode2.metaData("height", 50);
        actionNode2.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode3 = factory.actionNode(3);
        actionNode3.name("Check Stock");
        actionNode3.action(kcontext -> {
            java.lang.Boolean stockAvailable = (java.lang.Boolean) kcontext.getVariable("stockAvailable");
            System.out.println("Step 2: Checking stock availability...");
            kcontext.setVariable("stockAvailable", true);
            System.out.println("Stock is available");
        });
        actionNode3.metaData("UniqueId", "Task_CheckStock");
        actionNode3.metaData("elementname", "Check Stock");
        actionNode3.metaData("NodeType", "ScriptTask");
        actionNode3.metaData("x", 320);
        actionNode3.metaData("width", 100);
        actionNode3.metaData("y", 85);
        actionNode3.metaData("height", 50);
        actionNode3.done();
        org.jbpm.ruleflow.core.factory.SplitFactory<?> splitNode4 = factory.splitNode(4);
        splitNode4.name("Stock Available?");
        splitNode4.type(2);
        splitNode4.metaData("UniqueId", "Gateway_StockAvailable");
        splitNode4.metaData("elementname", "Stock Available?");
        splitNode4.metaData("x", 452);
        splitNode4.metaData("width", 56);
        splitNode4.metaData("y", 85);
        splitNode4.metaData("Default", null);
        splitNode4.metaData("height", 56);
        splitNode4.constraint(13, "SequenceFlow_StockNo", "DROOLS_DEFAULT", "java", kcontext -> {
            app.checkout.bpm.CheckoutProcessData vars = (app.checkout.bpm.CheckoutProcessData) kcontext.getVariable("vars");
            app.checkout.dto.CheckoutRequestDto request = (app.checkout.dto.CheckoutRequestDto) kcontext.getVariable("request");
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            java.lang.Boolean stockAvailable = (java.lang.Boolean) kcontext.getVariable("stockAvailable");
            java.lang.Boolean paymentSuccess = (java.lang.Boolean) kcontext.getVariable("paymentSuccess");
            return stockAvailable == false;
        }, 0, false);
        splitNode4.constraint(5, "SequenceFlow_StockYes", "DROOLS_DEFAULT", "java", kcontext -> {
            app.checkout.bpm.CheckoutProcessData vars = (app.checkout.bpm.CheckoutProcessData) kcontext.getVariable("vars");
            app.checkout.dto.CheckoutRequestDto request = (app.checkout.dto.CheckoutRequestDto) kcontext.getVariable("request");
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            java.lang.Boolean stockAvailable = (java.lang.Boolean) kcontext.getVariable("stockAvailable");
            java.lang.Boolean paymentSuccess = (java.lang.Boolean) kcontext.getVariable("paymentSuccess");
            return stockAvailable == true;
        }, 0, false);
        splitNode4.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode5 = factory.actionNode(5);
        actionNode5.name("Reserve Stock");
        actionNode5.action(kcontext -> {
            System.out.println("Step 3: Reserving stock...");
            System.out.println("Stock reserved successfully");
        });
        actionNode5.metaData("UniqueId", "Task_ReserveStock");
        actionNode5.metaData("elementname", "Reserve Stock");
        actionNode5.metaData("NodeType", "ScriptTask");
        actionNode5.metaData("x", 540);
        actionNode5.metaData("width", 100);
        actionNode5.metaData("y", 85);
        actionNode5.metaData("height", 50);
        actionNode5.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode6 = factory.actionNode(6);
        actionNode6.name("Create Order");
        actionNode6.action(kcontext -> {
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            System.out.println("Step 4: Creating order...");
            kcontext.setVariable("orderId", java.util.UUID.randomUUID().toString());
            System.out.println("Order created with ID: " + orderId);
        });
        actionNode6.metaData("UniqueId", "Task_CreateOrder");
        actionNode6.metaData("elementname", "Create Order");
        actionNode6.metaData("NodeType", "ScriptTask");
        actionNode6.metaData("x", 680);
        actionNode6.metaData("width", 100);
        actionNode6.metaData("y", 85);
        actionNode6.metaData("height", 50);
        actionNode6.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode7 = factory.actionNode(7);
        actionNode7.name("Process Payment");
        actionNode7.action(kcontext -> {
            java.lang.Boolean paymentSuccess = (java.lang.Boolean) kcontext.getVariable("paymentSuccess");
            System.out.println("Step 5: Processing payment...");
            kcontext.setVariable("paymentSuccess", true);
            System.out.println("Payment processed successfully");
        });
        actionNode7.metaData("UniqueId", "Task_ProcessPayment");
        actionNode7.metaData("elementname", "Process Payment");
        actionNode7.metaData("NodeType", "ScriptTask");
        actionNode7.metaData("x", 820);
        actionNode7.metaData("width", 100);
        actionNode7.metaData("y", 85);
        actionNode7.metaData("height", 50);
        actionNode7.done();
        org.jbpm.ruleflow.core.factory.SplitFactory<?> splitNode8 = factory.splitNode(8);
        splitNode8.name("Payment Success?");
        splitNode8.type(2);
        splitNode8.metaData("UniqueId", "Gateway_PaymentSuccess");
        splitNode8.metaData("elementname", "Payment Success?");
        splitNode8.metaData("x", 960);
        splitNode8.metaData("width", 56);
        splitNode8.metaData("y", 85);
        splitNode8.metaData("Default", null);
        splitNode8.metaData("height", 56);
        splitNode8.constraint(9, "SequenceFlow_PaymentYes", "DROOLS_DEFAULT", "java", kcontext -> {
            app.checkout.bpm.CheckoutProcessData vars = (app.checkout.bpm.CheckoutProcessData) kcontext.getVariable("vars");
            app.checkout.dto.CheckoutRequestDto request = (app.checkout.dto.CheckoutRequestDto) kcontext.getVariable("request");
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            java.lang.Boolean stockAvailable = (java.lang.Boolean) kcontext.getVariable("stockAvailable");
            java.lang.Boolean paymentSuccess = (java.lang.Boolean) kcontext.getVariable("paymentSuccess");
            return paymentSuccess == true;
        }, 0, false);
        splitNode8.constraint(12, "SequenceFlow_PaymentNo", "DROOLS_DEFAULT", "java", kcontext -> {
            app.checkout.bpm.CheckoutProcessData vars = (app.checkout.bpm.CheckoutProcessData) kcontext.getVariable("vars");
            app.checkout.dto.CheckoutRequestDto request = (app.checkout.dto.CheckoutRequestDto) kcontext.getVariable("request");
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            java.lang.Boolean stockAvailable = (java.lang.Boolean) kcontext.getVariable("stockAvailable");
            java.lang.Boolean paymentSuccess = (java.lang.Boolean) kcontext.getVariable("paymentSuccess");
            return paymentSuccess == false;
        }, 0, false);
        splitNode8.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode9 = factory.actionNode(9);
        actionNode9.name("Confirm Deduction");
        actionNode9.action(kcontext -> {
            System.out.println("Step 6: Confirming stock deduction...");
            System.out.println("Stock deduction confirmed");
        });
        actionNode9.metaData("UniqueId", "Task_ConfirmDeduction");
        actionNode9.metaData("elementname", "Confirm Deduction");
        actionNode9.metaData("NodeType", "ScriptTask");
        actionNode9.metaData("x", 1040);
        actionNode9.metaData("width", 100);
        actionNode9.metaData("y", 85);
        actionNode9.metaData("height", 50);
        actionNode9.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode10 = factory.actionNode(10);
        actionNode10.name("Confirm Order");
        actionNode10.action(kcontext -> {
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            System.out.println("Step 7: Confirming order...");
            System.out.println("Order confirmed: " + orderId);
        });
        actionNode10.metaData("UniqueId", "Task_ConfirmOrder");
        actionNode10.metaData("elementname", "Confirm Order");
        actionNode10.metaData("NodeType", "ScriptTask");
        actionNode10.metaData("x", 1180);
        actionNode10.metaData("width", 100);
        actionNode10.metaData("y", 85);
        actionNode10.metaData("height", 50);
        actionNode10.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode11 = factory.actionNode(11);
        actionNode11.name("Send Notification");
        actionNode11.action(kcontext -> {
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            System.out.println("Step 8: Sending confirmation email...");
            System.out.println("Notification sent for order: " + orderId);
        });
        actionNode11.metaData("UniqueId", "Task_SendEmail");
        actionNode11.metaData("elementname", "Send Notification");
        actionNode11.metaData("NodeType", "ScriptTask");
        actionNode11.metaData("x", 1320);
        actionNode11.metaData("width", 100);
        actionNode11.metaData("y", 85);
        actionNode11.metaData("height", 50);
        actionNode11.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode12 = factory.actionNode(12);
        actionNode12.name("Release Stock");
        actionNode12.action(kcontext -> {
            System.out.println("Rollback: Releasing reserved stock...");
            System.out.println("Stock released");
        });
        actionNode12.metaData("UniqueId", "Task_ReleaseStock");
        actionNode12.metaData("elementname", "Release Stock");
        actionNode12.metaData("NodeType", "ScriptTask");
        actionNode12.metaData("x", 960);
        actionNode12.metaData("width", 100);
        actionNode12.metaData("y", 200);
        actionNode12.metaData("height", 50);
        actionNode12.done();
        org.jbpm.ruleflow.core.factory.JoinFactory<?> joinNode13 = factory.joinNode(13);
        joinNode13.name("Merge Cancel Paths");
        joinNode13.type(2);
        joinNode13.metaData("UniqueId", "Gateway_MergeCancel");
        joinNode13.metaData("elementname", "Merge Cancel Paths");
        joinNode13.metaData("x", 700);
        joinNode13.metaData("width", 56);
        joinNode13.metaData("y", 200);
        joinNode13.metaData("height", 56);
        joinNode13.done();
        org.jbpm.ruleflow.core.factory.ActionNodeFactory<?> actionNode14 = factory.actionNode(14);
        actionNode14.name("Cancel Order");
        actionNode14.action(kcontext -> {
            java.lang.String orderId = (java.lang.String) kcontext.getVariable("orderId");
            System.out.println("Cancelling order...");
            if (orderId != null) {
                System.out.println("Order cancelled: " + orderId);
            } else {
                System.out.println("Checkout cancelled - order not created");
            }
        });
        actionNode14.metaData("UniqueId", "Task_CancelOrder");
        actionNode14.metaData("elementname", "Cancel Order");
        actionNode14.metaData("NodeType", "ScriptTask");
        actionNode14.metaData("x", 680);
        actionNode14.metaData("width", 100);
        actionNode14.metaData("y", 294);
        actionNode14.metaData("height", 50);
        actionNode14.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNode15 = factory.endNode(15);
        endNode15.name("Success");
        endNode15.terminate(true);
        endNode15.metaData("UniqueId", "EndEvent_Success");
        endNode15.metaData("elementname", "Success");
        endNode15.metaData("x", 1468);
        endNode15.metaData("width", 56);
        endNode15.metaData("y", 85);
        endNode15.metaData("height", 56);
        endNode15.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNode16 = factory.endNode(16);
        endNode16.name("Failed");
        endNode16.terminate(true);
        endNode16.metaData("UniqueId", "EndEvent_Failed");
        endNode16.metaData("elementname", "Failed");
        endNode16.metaData("x", 431);
        endNode16.metaData("width", 56);
        endNode16.metaData("y", 288);
        endNode16.metaData("height", 56);
        endNode16.done();
        factory.connection(1, 2, "SequenceFlow_1");
        factory.connection(2, 3, "SequenceFlow_2");
        factory.connection(3, 4, "SequenceFlow_3");
        factory.connection(4, 5, "SequenceFlow_StockYes");
        factory.connection(5, 6, "SequenceFlow_4");
        factory.connection(6, 7, "SequenceFlow_5");
        factory.connection(7, 8, "SequenceFlow_6");
        factory.connection(8, 9, "SequenceFlow_PaymentYes");
        factory.connection(9, 10, "SequenceFlow_7");
        factory.connection(10, 11, "SequenceFlow_8");
        factory.connection(8, 12, "SequenceFlow_PaymentNo");
        factory.connection(12, 13, "SequenceFlow_9");
        factory.connection(4, 13, "SequenceFlow_StockNo");
        factory.connection(13, 14, "SequenceFlow_10");
        factory.connection(11, 15, "SequenceFlow_Success");
        factory.connection(14, 16, "SequenceFlow_Failed");
        factory.validate();
        return factory.getProcess();
    }
}
