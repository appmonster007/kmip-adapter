package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SplitKey;
import org.purplebean.kmip.model.core.type.KeyPartIdentifier;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;

/**
 * Benchmark subject for {@link SplitKey}.
 */
public class SplitKeyBenchmarkSubject extends KmipBenchmarkSubject<SplitKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link SplitKeyBenchmarkSubject}.
   */
  public SplitKeyBenchmarkSubject() throws Exception {
    SplitKey subject = SplitKey
        .builder()
        .splitKeyParts(SplitKeyParts.of(1))
        .keyPartIdentifier(KeyPartIdentifier.of(1))
        .splitKeyThreshold(SplitKeyThreshold.of(1))
        .splitKeyMethod(SplitKeyMethod.Standard.XOR.inst())
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
    initialize(subject, SplitKey.class);
  }

  @Override
  public String name() {
    return "SplitKey";
  }

}