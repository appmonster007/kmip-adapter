package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdhPrivateKey;

import java.math.BigInteger;

public class TransparentEcdhPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcdhPrivateKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcdhPrivateKeyBenchmarkSubject() throws Exception {
        TransparentEcdhPrivateKey transparentEcdhPrivateKey = TransparentEcdhPrivateKey.of(
                RecommendedCurve.Standard.P_192.inst(),
                D.of(BigInteger.valueOf(1))
        );
        initialize(transparentEcdhPrivateKey, TransparentEcdhPrivateKey.class);
    }

    @Override
    public String name() {
        return "TransparentEcdhPrivateKey";
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