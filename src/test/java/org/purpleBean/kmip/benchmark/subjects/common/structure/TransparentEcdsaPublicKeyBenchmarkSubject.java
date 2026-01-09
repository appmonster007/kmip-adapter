package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPublicKey;

public class TransparentEcdsaPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcdsaPublicKey> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcdsaPublicKeyBenchmarkSubject() throws Exception {
        TransparentEcdsaPublicKey transparentEcdsaPublicKey = TransparentEcdsaPublicKey.of(
                new RecommendedCurve(RecommendedCurve.Standard.P_192),
                QString.of("test".getBytes())
        );
        initialize(transparentEcdsaPublicKey, TransparentEcdsaPublicKey.class);
    }

    @Override
    public String name() {
        return "TransparentEcdsaPublicKey";
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