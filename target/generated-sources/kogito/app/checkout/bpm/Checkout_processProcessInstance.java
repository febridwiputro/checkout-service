package app.checkout.bpm;

import app.checkout.bpm.Checkout_processModel;

public class Checkout_processProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<Checkout_processModel> {

    public Checkout_processProcessInstance(app.checkout.bpm.Checkout_processProcess process, Checkout_processModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public Checkout_processProcessInstance(app.checkout.bpm.Checkout_processProcess process, Checkout_processModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    public Checkout_processProcessInstance(app.checkout.bpm.Checkout_processProcess process, Checkout_processModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, processRuntime, wpi);
    }

    public Checkout_processProcessInstance(app.checkout.bpm.Checkout_processProcess process, Checkout_processModel value, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, wpi);
    }

    public Checkout_processProcessInstance(app.checkout.bpm.Checkout_processProcess process, Checkout_processModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.kogito.correlation.CompositeCorrelation correlation) {
        super(process, value, businessKey, processRuntime, correlation);
    }

    protected java.util.Map<String, Object> bind(Checkout_processModel variables) {
        if (null != variables)
            return variables.toMap();
        else
            return new java.util.HashMap();
    }

    protected void unbind(Checkout_processModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
