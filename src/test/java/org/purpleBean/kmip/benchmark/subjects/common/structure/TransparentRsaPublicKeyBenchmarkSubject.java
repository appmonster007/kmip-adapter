package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.common.structure.TransparentRsaPublicKey;

import java.math.BigInteger;

public class TransparentRsaPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentRsaPublicKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentRsaPublicKeyBenchmarkSubject() throws Exception {
        TransparentRsaPublicKey transparentRsaPublicKey = TransparentRsaPublicKey.of(
                Modulus.of(BigInteger.valueOf(1)),
                PublicExponent.of(BigInteger.valueOf(2))
        );
        initialize(transparentRsaPublicKey, TransparentRsaPublicKey.class);
    }

    @Override
    public String name() {
        return "TransparentRsaPublicKey";
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