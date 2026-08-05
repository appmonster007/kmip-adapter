package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TransparentDsaPrivateKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.X;

/**
 * Benchmark subject for {@link TransparentDsaPrivateKey}.
 */
public class TransparentDsaPrivateKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentDsaPrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentDsaPrivateKeyBenchmarkSubject}.
   */
  public TransparentDsaPrivateKeyBenchmarkSubject() throws Exception {
    TransparentDsaPrivateKey transparentDsaPrivateKey = TransparentDsaPrivateKey.of(
        P.of(BigInteger.valueOf(1)),
        Q.of(BigInteger.valueOf(2)),
        G.of(BigInteger.valueOf(3)),
        X.of(BigInteger.valueOf(4))
    );
    initialize(transparentDsaPrivateKey, TransparentDsaPrivateKey.class);
  }

  @Override
  public String name() {
    return "TransparentDsaPrivateKey";
  }

}