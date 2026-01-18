package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPrivateKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.X;

import java.math.BigInteger;

public class TransparentDsaPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentDsaPrivateKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentDsaPrivateKeyBenchmarkSubject() throws Exception {
        TransparentDsaPrivateKey transparentDsaPrivateKey = TransparentDsaPrivateKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                X.of(BigInteger.valueOf(4))
        );
        initialize(transparentDsaPrivateKey, TransparentDsaPrivateKey.class);
    }

    @Override
    public String name() {
        return "TransparentDsaPrivateKey";
    }

}