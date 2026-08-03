package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.QString;
import org.purplebean.kmip.model.v2x1.structure.TransparentEcPublicKey;

public class TransparentEcPublicKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcPublicKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public TransparentEcPublicKeyBenchmarkSubject() throws Exception {
    KmipContext.setSpec(spec);
    TransparentEcPublicKey subject = TransparentEcPublicKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
        .qString(QString.of(new byte[] {0x01, 0x02, 0x03}))
        .build();
    KmipContext.clear();
    initialize(subject, TransparentEcPublicKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcPublicKey";
  }
}