package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;

public class KeyPartIdentifierBenchmarkSubject extends KmipBenchmarkSubject<KeyPartIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public KeyPartIdentifierBenchmarkSubject() throws Exception {
    KeyPartIdentifier keyPartIdentifier = KeyPartIdentifier
        .builder()
        .value(1)
        .build();
    initialize(keyPartIdentifier, KeyPartIdentifier.class);
  }

  @Override
  public String name() {
    return "KeyPartIdentifier";
  }

}