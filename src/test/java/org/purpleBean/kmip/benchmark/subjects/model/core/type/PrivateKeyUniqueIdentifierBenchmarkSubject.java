package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<PrivateKeyUniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PrivateKeyUniqueIdentifierBenchmarkSubject() throws Exception {
    PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier = PrivateKeyUniqueIdentifier
        .builder()
        .value("test-private-key-id")
        .build();
    initialize(privateKeyUniqueIdentifier, PrivateKeyUniqueIdentifier.class);
  }

  @Override
  public String name() {
    return "PrivateKeyUniqueIdentifier";
  }

}