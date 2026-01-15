package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPublicKey;

public class TransparentEcmqvPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcmqvPublicKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcmqvPublicKeyBenchmarkSubject() throws Exception {
        TransparentEcmqvPublicKey transparentEcmqvPublicKey = TransparentEcmqvPublicKey.of(
                RecommendedCurve.Standard.P_192.inst(),
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