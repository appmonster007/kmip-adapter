package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.TransparentDhPrivateKey;

import java.math.BigInteger;

public class TransparentDhPrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<TransparentDhPrivateKey> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public TransparentDhPrivateKeyBenchmarkSubject() throws Exception {
        TransparentDhPrivateKey transparentDhPrivateKey = TransparentDhPrivateKey.of(
                P.of(BigInteger.valueOf(1)),
                Q.of(BigInteger.valueOf(2)),
                G.of(BigInteger.valueOf(3)),
                J.of(BigInteger.valueOf(4)),
                X.of(BigInteger.valueOf(5))
        );
        initialize(transparentDhPrivateKey, TransparentDhPrivateKey.class);
    }

    @Override
    public String name() {
        return "TransparentDhPrivateKey";
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