package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdhPrivateKey;
import org.purpleBean.kmip.model.core.type.D;

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

}