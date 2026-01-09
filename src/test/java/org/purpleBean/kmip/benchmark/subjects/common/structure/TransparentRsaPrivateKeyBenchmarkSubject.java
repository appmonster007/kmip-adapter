package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PrivateExponent;
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;

import java.math.BigInteger;

public class TransparentRsaPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentRsaPrivateKey> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

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