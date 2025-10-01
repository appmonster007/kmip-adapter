package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeBenchmarkSubject extends KmipBenchmarkSubject<AttestationType> {

    public AttestationTypeBenchmarkSubject() throws Exception {
        AttestationType attestationType = new AttestationType(AttestationType.Standard.TPM_QUOTE);
        initialize(attestationType, AttestationType.class);
    }

    @Override
    public String name() {
        return "AttestationType";
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
