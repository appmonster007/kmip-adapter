package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

public class IssuerDistinguishedNameBenchmarkSubject extends KmipBenchmarkSubject<IssuerDistinguishedName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public IssuerDistinguishedNameBenchmarkSubject() throws Exception {
        IssuerDistinguishedName issuerDistinguishedName = IssuerDistinguishedName.of("test-issuer".getBytes());
        initialize(issuerDistinguishedName, IssuerDistinguishedName.class);
    }

    @Override
    public String name() {
        return "IssuerDistinguishedName";
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
