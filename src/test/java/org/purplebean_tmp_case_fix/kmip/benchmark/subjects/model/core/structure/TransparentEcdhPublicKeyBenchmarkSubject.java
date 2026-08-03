package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdhPublicKey;
import org.purplebean.kmip.model.core.type.QString;

public class TransparentEcdhPublicKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcdhPublicKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public TransparentEcdhPublicKeyBenchmarkSubject() throws Exception {
    TransparentEcdhPublicKey transparentEcdhPublicKey = TransparentEcdhPublicKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        QString.of("test".getBytes())
    );
    initialize(transparentEcdhPublicKey, TransparentEcdhPublicKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcdhPublicKey";
  }

}