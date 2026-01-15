package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPrivateKey;

import java.math.BigInteger;

public class TransparentEcdsaPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcdsaPrivateKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcdsaPrivateKeyBenchmarkSubject() throws Exception {
        TransparentEcdsaPrivateKey transparentEcdsaPrivateKey = TransparentEcdsaPrivateKey.of(
                RecommendedCurve.Standard.P_192.inst(),
                D.of(BigInteger.valueOf(1))
        );
        initialize(transparentEcdsaPrivateKey, TransparentEcdsaPrivateKey.class);
    }

    @Override
    public String name() {
        return "TransparentEcdsaPrivateKey";
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