package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPublicKey;

public class TransparentEcmqvPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcmqvPublicKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcmqvPublicKeyBenchmarkSubject() throws Exception {
        TransparentEcmqvPublicKey transparentEcmqvPublicKey = TransparentEcmqvPublicKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                QString.of("test".getBytes())
        );
        initialize(transparentEcmqvPublicKey, TransparentEcmqvPublicKey.class);
    }

    @Override
    public String name() {
        return "TransparentEcmqvPublicKey";
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