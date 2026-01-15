package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdhPublicKey;

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