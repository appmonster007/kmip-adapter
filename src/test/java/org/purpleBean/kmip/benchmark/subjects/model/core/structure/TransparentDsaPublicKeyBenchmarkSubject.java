package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPublicKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;

import java.math.BigInteger;

public class TransparentDsaPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentDsaPublicKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentDsaPublicKeyBenchmarkSubject() throws Exception {
        TransparentDsaPublicKey transparentDsaPublicKey = TransparentDsaPublicKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                Y.of(BigInteger.valueOf(4))
        );
        initialize(transparentDsaPublicKey, TransparentDsaPublicKey.class);
    }

    @Override
    public String name() {
        return "TransparentDsaPublicKey";
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