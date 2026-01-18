package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

public class CertificateRequestTypeBenchmarkSubject extends KmipBenchmarkSubject<CertificateRequestType> {

    public CertificateRequestTypeBenchmarkSubject() throws Exception {
        CertificateRequestType certificateRequestType = CertificateRequestType.Standard.CRMF.inst();
        initialize(certificateRequestType, CertificateRequestType.class);
    }

    @Override
    public String name() {
        return "CertificateRequestType";
    }

}
