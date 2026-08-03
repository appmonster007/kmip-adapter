package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SymmetricKey;

public class SymmetricKeyBenchmarkSubject extends KmipBenchmarkSubject<SymmetricKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public SymmetricKeyBenchmarkSubject() throws Exception {
    SymmetricKey subject = SymmetricKey
        .builder()
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
    initialize(subject, SymmetricKey.class);
  }

  @Override
  public String name() {
    return "SymmetricKey";
  }

}