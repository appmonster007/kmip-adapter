package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;

/**
 * Benchmark subject for {@link PublicKeyUniqueIdentifier}.
 */
public class PublicKeyUniqueIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<PublicKeyUniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link PublicKeyUniqueIdentifierBenchmarkSubject}.
   */
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