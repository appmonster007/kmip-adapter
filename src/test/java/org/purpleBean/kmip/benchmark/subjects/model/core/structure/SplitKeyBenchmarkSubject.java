package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SplitKey;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

public class SplitKeyBenchmarkSubject extends KmipBenchmarkSubject<SplitKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public SplitKeyBenchmarkSubject() throws Exception {
        SplitKey subject = SplitKey.builder()
                .splitKeyParts(SplitKeyParts.of(1))
                .keyPartIdentifier(KeyPartIdentifier.of(1))
                .splitKeyThreshold(SplitKeyThreshold.of(1))
                .splitKeyMethod(SplitKeyMethod.Standard.XOR.inst())
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
        initialize(subject, SplitKey.class);
    }

    @Override
    public String name() {
        return "SplitKey";
    }

}