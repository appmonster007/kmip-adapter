package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentDhPublicKey;

import java.math.BigInteger;

public class TransparentDhPublicKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentDhPublicKey> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TransparentDhPublicKeyBenchmarkSubject() throws Exception {
        TransparentDhPublicKey transparentDhPublicKey = TransparentDhPublicKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                J.of(BigInteger.valueOf(4)),
                Y.of(BigInteger.valueOf(5))
        );
        initialize(transparentDhPublicKey, TransparentDhPublicKey.class);
    }

    @Override
    public String name() {
        return "TransparentDhPublicKey";
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