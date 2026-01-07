package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.structure.CertificateIdentifier;

public class CertificateIdentifierBenchmarkSubject extends KmipBenchmarkSubject<CertificateIdentifier> {

    public CertificateIdentifierBenchmarkSubject() throws Exception {
        CertificateIdentifier certificateIdentifier = CertificateIdentifier.builder()
                .issuer(Issuer.of("CN=Test Issuer"))
                .serialNumber(SerialNumber.of("12345"))
                .build();
        initialize(certificateIdentifier, CertificateIdentifier.class);
    }

    @Override
    public String name() {
        return "CertificateIdentifier";
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