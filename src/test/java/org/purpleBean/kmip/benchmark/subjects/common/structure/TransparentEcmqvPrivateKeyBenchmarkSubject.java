package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcmqvPrivateKey;

import java.math.BigInteger;

public class TransparentEcmqvPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcmqvPrivateKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcmqvPrivateKeyBenchmarkSubject() throws Exception {
        TransparentEcmqvPrivateKey transparentEcmqvPrivateKey = TransparentEcmqvPrivateKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
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