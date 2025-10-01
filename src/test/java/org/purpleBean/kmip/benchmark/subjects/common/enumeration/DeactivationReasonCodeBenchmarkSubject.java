package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeBenchmarkSubject extends KmipBenchmarkSubject<DeactivationReasonCode> {

    public DeactivationReasonCodeBenchmarkSubject() throws Exception {
        DeactivationReasonCode deactivationReasonCode = new DeactivationReasonCode(DeactivationReasonCode.Standard.UNSPECIFIED);
        initialize(deactivationReasonCode, DeactivationReasonCode.class);
    }

    @Override
    public String name() {
        return "DeactivationReasonCode";
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
