package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.CertificateType;

public class CertificateTypeBenchmarkSubject extends KmipBenchmarkSubject<CertificateType> {

    public CertificateTypeBenchmarkSubject() throws Exception {
        CertificateType certificateType = CertificateType.Standard.X_509.inst();
        initialize(certificateType, CertificateType.class);
    }

    @Override
    public String name() {
        return "CertificateType";
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
