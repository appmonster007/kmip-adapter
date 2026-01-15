package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PrivateExponent;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPrivateKey;

import java.math.BigInteger;

public class TransparentRsaPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentRsaPrivateKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentRsaPrivateKeyBenchmarkSubject() throws Exception {
        TransparentRsaPrivateKey transparentRsaPrivateKey = TransparentRsaPrivateKey.of(
                Modulus.of(BigInteger.valueOf(1)),
                PrivateExponent.of(BigInteger.valueOf(2)),
                null,
                null,
                null,
                null,
                null,
                null
        );
        initialize(transparentRsaPrivateKey, TransparentRsaPrivateKey.class);
    }

    @Override
    public String name() {
        return "TransparentRsaPrivateKey";
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