package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

public class CertificateIssuerDistinguishedNameBenchmarkSubject extends KmipBenchmarkSubject<CertificateIssuerDistinguishedName> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public CertificateIssuerDistinguishedNameBenchmarkSubject() throws Exception {
        CertificateIssuerDistinguishedName certificateIssuerDistinguishedName = CertificateIssuerDistinguishedName.of("CN=Test Issuer");
        initialize(certificateIssuerDistinguishedName, CertificateIssuerDistinguishedName.class);
    }

    @Override
    public String name() {
        return "CertificateIssuerDistinguishedName";
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