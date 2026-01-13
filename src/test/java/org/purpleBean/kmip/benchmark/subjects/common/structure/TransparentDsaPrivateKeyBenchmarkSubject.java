package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.common.structure.TransparentDsaPrivateKey;

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

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}