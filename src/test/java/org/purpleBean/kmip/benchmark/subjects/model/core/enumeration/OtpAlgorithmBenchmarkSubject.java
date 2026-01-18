package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;

public class OtpAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<OtpAlgorithm> {

    public OtpAlgorithmBenchmarkSubject() throws Exception {
        OtpAlgorithm otpAlgorithm = OtpAlgorithm.Standard.HOTP.inst();
        initialize(otpAlgorithm, OtpAlgorithm.class);
    }

    @Override
    public String name() {
        return "OtpAlgorithm";
    }

}
