package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.D;
import org.purplebean.kmip.model.v2x1.structure.TransparentEcPrivateKey;

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