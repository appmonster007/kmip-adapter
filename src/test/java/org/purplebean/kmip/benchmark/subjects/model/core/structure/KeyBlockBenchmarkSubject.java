package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;

/**
 * Benchmark subject for {@link KeyBlock}.
 */
public class KeyBlockBenchmarkSubject extends KmipBenchmarkSubject<KeyBlock> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link KeyBlockBenchmarkSubject}.
   */
  public KeyBlockBenchmarkSubject() throws Exception {
    KeyBlock subject = KeyBlock
        .builder()
        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
        .build();
    initialize(subject, KeyBlock.class);
  }

  @Override
  public String name() {
    return "KeyBlock";
  }

}