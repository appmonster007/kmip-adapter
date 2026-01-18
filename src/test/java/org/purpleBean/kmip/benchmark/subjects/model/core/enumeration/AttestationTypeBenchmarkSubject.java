package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

public class AttestationTypeBenchmarkSubject extends KmipBenchmarkSubject<AttestationType> {

    public AttestationTypeBenchmarkSubject() throws Exception {
        AttestationType attestationType = AttestationType.Standard.TPM_QUOTE.inst();
        initialize(attestationType, AttestationType.class);
    }

    @Override
    public String name() {
        return "AttestationType";
    }

}
