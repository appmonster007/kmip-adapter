package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeBenchmarkSubject extends KmipBenchmarkSubject<RevocationReasonCode> {

    public RevocationReasonCodeBenchmarkSubject() throws Exception {
        RevocationReasonCode revocationReasonCode = new RevocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE);
        initialize(revocationReasonCode, RevocationReasonCode.class);
    }

    @Override
    public String name() {
        return "RevocationReasonCode";
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
