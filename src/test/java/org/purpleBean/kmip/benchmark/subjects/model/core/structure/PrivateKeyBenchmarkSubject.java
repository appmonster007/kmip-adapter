package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PrivateKey;

public class PrivateKeyBenchmarkSubject extends KmipBenchmarkSubject<PrivateKey> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public PrivateKeyBenchmarkSubject() throws Exception {
    PrivateKey subject = PrivateKey
        .builder()
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
    initialize(subject, PrivateKey.class);
  }

  @Override
  public String name() {
    return "PrivateKey";
  }

}