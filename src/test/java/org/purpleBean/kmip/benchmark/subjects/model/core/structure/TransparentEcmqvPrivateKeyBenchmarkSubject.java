package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPrivateKey;
import org.purpleBean.kmip.model.core.type.D;

import java.math.BigInteger;

public class TransparentEcmqvPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcmqvPrivateKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcmqvPrivateKeyBenchmarkSubject() throws Exception {
        TransparentEcmqvPrivateKey transparentEcmqvPrivateKey = TransparentEcmqvPrivateKey.of(
                RecommendedCurve.Standard.P_192.inst(),
                D.of(BigInteger.valueOf(1))
        );
        initialize(transparentEcmqvPrivateKey, TransparentEcmqvPrivateKey.class);
    }

    @Override
    public String name() {
        return "TransparentEcmqvPrivateKey";
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