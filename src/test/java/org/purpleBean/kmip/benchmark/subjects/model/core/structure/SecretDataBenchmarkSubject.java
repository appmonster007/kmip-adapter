package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SecretData;

public class SecretDataBenchmarkSubject extends KmipBenchmarkSubject<SecretData> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public SecretDataBenchmarkSubject() throws Exception {
        SecretData subject = SecretData.builder()
                .secretDataType(SecretDataType.Standard.PASSWORD.inst())
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
        initialize(subject, SecretData.class);
    }

    @Override
    public String name() {
        return "SecretData";
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