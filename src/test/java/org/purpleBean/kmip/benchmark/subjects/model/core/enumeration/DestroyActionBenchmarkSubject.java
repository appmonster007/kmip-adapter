package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionBenchmarkSubject extends KmipBenchmarkSubject<DestroyAction> {

    public DestroyActionBenchmarkSubject() throws Exception {
        DestroyAction destroyAction = DestroyAction.Standard.UNSPECIFIED.inst();
        initialize(destroyAction, DestroyAction.class);
    }

    @Override
    public String name() {
        return "DestroyAction";
    }

}
