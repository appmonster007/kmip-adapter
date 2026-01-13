package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CertificateRequest;

public class CertificateRequestBenchmarkSubject extends KmipBenchmarkSubject<CertificateRequest> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CertificateRequestBenchmarkSubject() throws Exception {
        CertificateRequest certificateRequest = CertificateRequest.of(new byte[]{0x01, 0x02, 0x03});
        initialize(certificateRequest, CertificateRequest.class);
    }

    @Override
    public String name() {
        return "CertificateRequest";
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