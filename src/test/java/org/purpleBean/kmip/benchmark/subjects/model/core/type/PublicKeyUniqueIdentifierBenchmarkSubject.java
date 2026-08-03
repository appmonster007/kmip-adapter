package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<PublicKeyUniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PublicKeyUniqueIdentifierBenchmarkSubject() throws Exception {
    PublicKeyUniqueIdentifier publicKeyUniqueIdentifier = PublicKeyUniqueIdentifier
        .builder()
        .value("test-key-id")
        .build();
    initialize(publicKeyUniqueIdentifier, PublicKeyUniqueIdentifier.class);
  }

  @Override
  public String name() {
    return "PublicKeyUniqueIdentifier";
  }

}