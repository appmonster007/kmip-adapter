package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TransparentDsaPublicKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.Y;

/**
 * Benchmark subject for {@link TransparentDsaPublicKey}.
 */
public class TransparentDsaPublicKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentDsaPublicKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentDsaPublicKeyBenchmarkSubject}.
   */
  public TransparentDsaPublicKeyBenchmarkSubject() throws Exception {
    TransparentDsaPublicKey transparentDsaPublicKey = TransparentDsaPublicKey.of(
        P.of(BigInteger.valueOf(1)),
        Q.of(BigInteger.valueOf(2)),
        G.of(BigInteger.valueOf(3)),
        Y.of(BigInteger.valueOf(4))
    );
    initialize(transparentDsaPublicKey, TransparentDsaPublicKey.class);
  }

  @Override
  public String name() {
    return "TransparentDsaPublicKey";
  }

}