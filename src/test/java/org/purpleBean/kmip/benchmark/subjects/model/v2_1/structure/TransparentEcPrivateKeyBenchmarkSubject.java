package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPrivateKey;

public class TransparentEcPrivateKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcPrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public TransparentEcPrivateKeyBenchmarkSubject() throws Exception {
    KmipContext.setSpec(spec);
    TransparentEcPrivateKey subject = TransparentEcPrivateKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
        .d(D.of(BigInteger.ONE))
        .build();
    KmipContext.clear();
    initialize(subject, TransparentEcPrivateKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcPrivateKey";
  }
}