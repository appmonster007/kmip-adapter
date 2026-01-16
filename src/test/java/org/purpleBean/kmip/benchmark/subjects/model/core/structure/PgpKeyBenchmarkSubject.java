package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PgpKey;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyBenchmarkSubject extends KmipBenchmarkSubject<PgpKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public PgpKeyBenchmarkSubject() throws Exception {
        PgpKey subject = PgpKey.builder()
                .pgpKeyVersion(PgpKeyVersion.of(4))
                .keyBlock(KeyBlock.builder()
                        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                        .build())
                .build();
        initialize(subject, PgpKey.class);
    }

    @Override
    public String name() {
        return "PgpKey";
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