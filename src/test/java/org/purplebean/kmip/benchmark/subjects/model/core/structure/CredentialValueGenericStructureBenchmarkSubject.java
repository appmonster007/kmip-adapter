package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.CredentialValueGenericStructure;
import org.purplebean.kmip.model.core.type.Username;

/**
 * Benchmark subject for {@link CredentialValueGenericStructure}.
 */
public class CredentialValueGenericStructureBenchmarkSubject
    extends KmipBenchmarkSubject<CredentialValueGenericStructure> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CredentialValueGenericStructureBenchmarkSubject}.
   */
  public CredentialValueGenericStructureBenchmarkSubject() throws Exception {
    CredentialValueGenericStructure subject = CredentialValueGenericStructure
        .builder()
        .value(Username.of("test-value"))
        .build();
    initialize(subject, CredentialValueGenericStructure.class);
  }

  @Override
  public String name() {
    return "CredentialValueGenericStructure";
  }

}