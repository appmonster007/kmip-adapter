package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdhPrivateKey;
import org.purplebean.kmip.model.core.type.D;

/**
 * Benchmark subject for {@link TransparentEcdhPrivateKey}.
 */
public class TransparentEcdhPrivateKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcdhPrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentEcdhPrivateKeyBenchmarkSubject}.
   */
  public TransparentEcdhPrivateKeyBenchmarkSubject() throws Exception {
    TransparentEcdhPrivateKey transparentEcdhPrivateKey = TransparentEcdhPrivateKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        D.of(BigInteger.valueOf(1))
    );
    initialize(transparentEcdhPrivateKey, TransparentEcdhPrivateKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcdhPrivateKey";
  }

}