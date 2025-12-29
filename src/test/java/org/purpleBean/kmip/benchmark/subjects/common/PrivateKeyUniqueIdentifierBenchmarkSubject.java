package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<PrivateKeyUniqueIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public PrivateKeyUniqueIdentifierBenchmarkSubject() throws Exception {
        PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier = PrivateKeyUniqueIdentifier.builder().value("test-private-key-id").build();
        initialize(privateKeyUniqueIdentifier, PrivateKeyUniqueIdentifier.class);
    }

    @Override
    public String name() {
        return "PrivateKeyUniqueIdentifier";
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