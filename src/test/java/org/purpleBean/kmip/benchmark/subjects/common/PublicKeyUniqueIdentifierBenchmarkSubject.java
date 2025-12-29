package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<PublicKeyUniqueIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public PublicKeyUniqueIdentifierBenchmarkSubject() throws Exception {
        PublicKeyUniqueIdentifier publicKeyUniqueIdentifier = PublicKeyUniqueIdentifier.builder().value("test-key-id").build();
        initialize(publicKeyUniqueIdentifier, PublicKeyUniqueIdentifier.class);
    }

    @Override
    public String name() {
        return "PublicKeyUniqueIdentifier";
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