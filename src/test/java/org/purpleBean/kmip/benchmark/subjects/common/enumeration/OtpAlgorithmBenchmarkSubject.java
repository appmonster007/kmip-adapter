package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<OtpAlgorithm> {

    public OtpAlgorithmBenchmarkSubject() throws Exception {
        OtpAlgorithm otpAlgorithm = new OtpAlgorithm(OtpAlgorithm.Standard.HOTP);
        initialize(otpAlgorithm, OtpAlgorithm.class);
    }

    @Override
    public String name() {
        return "OtpAlgorithm";
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
