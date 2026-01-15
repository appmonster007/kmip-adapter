package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdhPublicKey;
import org.purpleBean.kmip.model.core.type.QString;

public class TransparentEcdhPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcdhPublicKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcdhPublicKeyBenchmarkSubject() throws Exception {
        TransparentEcdhPublicKey transparentEcdhPublicKey = TransparentEcdhPublicKey.of(
                RecommendedCurve.Standard.P_192.inst(),
                QString.of("test".getBytes())
        );
        initialize(transparentEcdhPublicKey, TransparentEcdhPublicKey.class);
    }

    @Override
    public String name() {
        return "TransparentEcdhPublicKey";
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