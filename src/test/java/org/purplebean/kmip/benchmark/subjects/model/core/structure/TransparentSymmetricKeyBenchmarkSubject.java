package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TransparentSymmetricKey;
import org.purplebean.kmip.model.core.type.Key;

/**
 * Benchmark subject for {@link TransparentSymmetricKey}.
 */
public class TransparentSymmetricKeyBenchmarkSubject
    extends KmipBenchmarkSubject<TransparentSymmetricKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TransparentSymmetricKeyBenchmarkSubject}.
   */
  public TransparentSymmetricKeyBenchmarkSubject() throws Exception {
    Key key = Key.of(new byte[] {0x01, 0x02, 0x03});
    TransparentSymmetricKey transparentSymmetricKey = TransparentSymmetricKey.of(key);
    initialize(transparentSymmetricKey, TransparentSymmetricKey.class);
  }

  @Override
  public String name() {
    return "TransparentSymmetricKey";
  }

}