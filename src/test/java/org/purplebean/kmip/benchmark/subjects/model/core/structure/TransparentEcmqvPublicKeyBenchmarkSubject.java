package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcmqvPublicKey;
import org.purplebean.kmip.model.core.type.QString;

/**
 * Benchmark subject for {@link TransparentEcmqvPublicKey}.
 */
public class TransparentEcmqvPublicKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcmqvPublicKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentEcmqvPublicKeyBenchmarkSubject}.
   */
  public TransparentEcmqvPublicKeyBenchmarkSubject() throws Exception {
    TransparentEcmqvPublicKey transparentEcmqvPublicKey = TransparentEcmqvPublicKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        QString.of("test".getBytes())
    );
    initialize(transparentEcmqvPublicKey, TransparentEcmqvPublicKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcmqvPublicKey";
  }

}