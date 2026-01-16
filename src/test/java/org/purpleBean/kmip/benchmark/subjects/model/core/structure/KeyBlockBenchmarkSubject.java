package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;

public class KeyBlockBenchmarkSubject extends KmipBenchmarkSubject<KeyBlock> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public KeyBlockBenchmarkSubject() throws Exception {
        KeyBlock subject = KeyBlock.builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build();
        initialize(subject, KeyBlock.class);
    }

    @Override
    public String name() {
        return "KeyBlock";
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