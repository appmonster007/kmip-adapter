package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdsaPublicKey;
import org.purpleBean.kmip.model.core.type.QString;

public class TransparentEcdsaPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentEcdsaPublicKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentEcdsaPublicKeyBenchmarkSubject() throws Exception {
        TransparentEcdsaPublicKey transparentEcdsaPublicKey = TransparentEcdsaPublicKey.of(
                RecommendedCurve.Standard.P_192.inst(),
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