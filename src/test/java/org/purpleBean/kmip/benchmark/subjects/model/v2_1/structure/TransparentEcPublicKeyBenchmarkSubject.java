package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPublicKey;

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