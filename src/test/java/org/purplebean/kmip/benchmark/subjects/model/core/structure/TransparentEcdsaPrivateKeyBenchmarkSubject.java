package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdsaPrivateKey;
import org.purplebean.kmip.model.core.type.D;

/**
 * Benchmark subject for {@link TransparentEcdsaPrivateKey}.
 */
public class TransparentEcdsaPrivateKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcdsaPrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentEcdsaPrivateKeyBenchmarkSubject}.
   */
  public TransparentEcdsaPrivateKeyBenchmarkSubject() throws Exception {
    TransparentEcdsaPrivateKey transparentEcdsaPrivateKey = TransparentEcdsaPrivateKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        D.of(BigInteger.valueOf(1))
    );
    initialize(transparentEcdsaPrivateKey, TransparentEcdsaPrivateKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcdsaPrivateKey";
  }

}