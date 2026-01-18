package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;

public class SymmetricKeyBenchmarkSubject extends KmipBenchmarkSubject<SymmetricKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public SymmetricKeyBenchmarkSubject() throws Exception {
        SymmetricKey subject = SymmetricKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
        initialize(subject, SymmetricKey.class);
    }

    @Override
    public String name() {
        return "SymmetricKey";
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