package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdsaPrivateKey;
import org.purpleBean.kmip.model.core.type.D;

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