package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

/**
 * Benchmark subject for {@link PrivateKeyUniqueIdentifier}.
 */
public class PrivateKeyUniqueIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<PrivateKeyUniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link PrivateKeyUniqueIdentifierBenchmarkSubject}.
   */
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