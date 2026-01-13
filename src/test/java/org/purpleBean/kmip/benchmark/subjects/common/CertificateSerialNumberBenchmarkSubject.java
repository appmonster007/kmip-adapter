package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateSerialNumber;

public class CertificateSerialNumberBenchmarkSubject extends KmipBenchmarkSubject<CertificateSerialNumber> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CertificateSerialNumberBenchmarkSubject() throws Exception {
        CertificateSerialNumber certificateSerialNumber = CertificateSerialNumber.of("12345".getBytes());
        initialize(certificateSerialNumber, CertificateSerialNumber.class);
    }

    @Override
    public String name() {
        return "CertificateSerialNumber";
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
