package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TransparentRsaPrivateKey;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.PrivateExponent;

/**
 * Benchmark subject for {@link TransparentRsaPrivateKey}.
 */
public class TransparentRsaPrivateKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentRsaPrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentRsaPrivateKeyBenchmarkSubject}.
   */
  public TransparentRsaPrivateKeyBenchmarkSubject() throws Exception {
    TransparentRsaPrivateKey transparentRsaPrivateKey = TransparentRsaPrivateKey.of(
        Modulus.of(BigInteger.valueOf(1)),
        PrivateExponent.of(BigInteger.valueOf(2)),
        null,
        null,
        null,
        null,
        null,
        null
    );
    initialize(transparentRsaPrivateKey, TransparentRsaPrivateKey.class);
  }

  @Override
  public String name() {
    return "TransparentRsaPrivateKey";
  }

}