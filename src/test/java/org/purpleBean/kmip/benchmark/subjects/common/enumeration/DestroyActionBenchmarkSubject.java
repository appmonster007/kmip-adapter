package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

public class DestroyActionBenchmarkSubject extends KmipBenchmarkSubject<DestroyAction> {

    public DestroyActionBenchmarkSubject() throws Exception {
        DestroyAction destroyAction = new DestroyAction(DestroyAction.Standard.UNSPECIFIED);
        initialize(destroyAction, DestroyAction.class);
    }

    @Override
    public String name() {
        return "DestroyAction";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
