package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PrivateKey;

public class PrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<PrivateKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public PrivateKeyBenchmarkSubject() throws Exception {
        PrivateKey subject = PrivateKey.builder()
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
        initialize(subject, PrivateKey.class);
    }

    @Override
    public String name() {
        return "PrivateKey";
    }

}