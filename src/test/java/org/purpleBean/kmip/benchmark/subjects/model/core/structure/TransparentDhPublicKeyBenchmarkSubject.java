package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.TransparentDhPublicKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.J;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;

public class TransparentDhPublicKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentDhPublicKey> {

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

}