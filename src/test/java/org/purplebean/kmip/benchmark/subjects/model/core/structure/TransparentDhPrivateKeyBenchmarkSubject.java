package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TransparentDhPrivateKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.J;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.X;

/**
 * Benchmark subject for {@link TransparentDhPrivateKey}.
 */
public class TransparentDhPrivateKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentDhPrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentDhPrivateKeyBenchmarkSubject}.
   */
  public TransparentDhPrivateKeyBenchmarkSubject() throws Exception {
    TransparentDhPrivateKey transparentDhPrivateKey = TransparentDhPrivateKey.of(
        P.of(BigInteger.valueOf(1)),
        Q.of(BigInteger.valueOf(2)),
        G.of(BigInteger.valueOf(3)),
        J.of(BigInteger.valueOf(4)),
        X.of(BigInteger.valueOf(5))
    );
    initialize(transparentDhPrivateKey, TransparentDhPrivateKey.class);
  }

  @Override
  public String name() {
    return "TransparentDhPrivateKey";
  }

}