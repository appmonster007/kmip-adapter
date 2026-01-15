package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

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
