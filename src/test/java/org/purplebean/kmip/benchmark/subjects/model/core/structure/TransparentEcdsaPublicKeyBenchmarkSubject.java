package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdsaPublicKey;
import org.purplebean.kmip.model.core.type.QString;

/**
 * Benchmark subject for {@link TransparentEcdsaPublicKey}.
 */
public class TransparentEcdsaPublicKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentEcdsaPublicKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentEcdsaPublicKeyBenchmarkSubject}.
   */
  public TransparentEcdsaPublicKeyBenchmarkSubject() throws Exception {
    TransparentEcdsaPublicKey transparentEcdsaPublicKey = TransparentEcdsaPublicKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        QString.of("test".getBytes())
    );
    initialize(transparentEcdsaPublicKey, TransparentEcdsaPublicKey.class);
  }

  @Override
  public String name() {
    return "TransparentEcdsaPublicKey";
  }

}