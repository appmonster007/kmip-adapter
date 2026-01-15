package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeBenchmarkSubject extends KmipBenchmarkSubject<CredentialType> {

    public CredentialTypeBenchmarkSubject() throws Exception {
        CredentialType credentialType = CredentialType.Standard.USERNAME_AND_PASSWORD.inst();
        initialize(credentialType, CredentialType.class);
    }

    @Override
    public String name() {
        return "CredentialType";
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
