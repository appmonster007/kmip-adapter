package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;

public class CertificateRequestTypeBenchmarkSubject extends KmipBenchmarkSubject<CertificateRequestType> {

    public CertificateRequestTypeBenchmarkSubject() throws Exception {
        CertificateRequestType certificateRequestType = new CertificateRequestType(CertificateRequestType.Standard.CRMF);
        initialize(certificateRequestType, CertificateRequestType.class);
    }

    @Override
    public String name() {
        return "CertificateRequestType";
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
