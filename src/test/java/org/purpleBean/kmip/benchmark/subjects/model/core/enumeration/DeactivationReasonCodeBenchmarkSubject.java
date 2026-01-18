package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeBenchmarkSubject extends KmipBenchmarkSubject<DeactivationReasonCode> {

    public DeactivationReasonCodeBenchmarkSubject() throws Exception {
        DeactivationReasonCode deactivationReasonCode = DeactivationReasonCode.Standard.UNSPECIFIED.inst();
        initialize(deactivationReasonCode, DeactivationReasonCode.class);
    }

    @Override
    public String name() {
        return "DeactivationReasonCode";
    }

}
