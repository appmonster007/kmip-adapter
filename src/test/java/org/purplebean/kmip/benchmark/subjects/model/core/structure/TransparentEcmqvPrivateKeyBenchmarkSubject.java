package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcmqvPrivateKey;
import org.purplebean.kmip.model.core.type.D;

/**
 * Benchmark subject for {@link TransparentEcmqvPrivateKey}.
 */
public class TransparentEcmqvPrivateKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcmqvPrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentEcmqvPrivateKeyBenchmarkSubject}.
   */
  public TransparentEcmqvPrivateKeyBenchmarkSubject() throws Exception {
    TransparentEcmqvPrivateKey transparentEcmqvPrivateKey = TransparentEcmqvPrivateKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        D.of(BigInteger.valueOf(1))
    );
    initialize(transparentEcmqvPrivateKey, TransparentEcmqvPrivateKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcmqvPrivateKey";
  }

}