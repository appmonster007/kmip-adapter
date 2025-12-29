package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierBenchmarkSubject extends KmipBenchmarkSubject<KeyPartIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public KeyPartIdentifierBenchmarkSubject() throws Exception {
        KeyPartIdentifier keyPartIdentifier = KeyPartIdentifier.builder().value(1).build();
        initialize(keyPartIdentifier, KeyPartIdentifier.class);
    }

    @Override
    public String name() {
        return "KeyPartIdentifier";
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