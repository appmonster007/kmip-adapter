package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PublicKey;

/**
 * Benchmark subject for {@link PublicKey}.
 */
public class PublicKeyBenchmarkSubject extends KmipBenchmarkSubject<PublicKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link PublicKeyBenchmarkSubject}.
   */
  public PublicKeyBenchmarkSubject() throws Exception {
    PublicKey subject = PublicKey
        .builder()
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
    initialize(subject, PublicKey.class);
  }

  @Override
  public String name() {
    return "PublicKey";
  }

}