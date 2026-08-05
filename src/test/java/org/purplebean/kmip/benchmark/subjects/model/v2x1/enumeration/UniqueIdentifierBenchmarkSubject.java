package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier;

/**
 * Benchmark subject for {@link UniqueIdentifier}.
 */
public class UniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<UniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  /**
   * Constructs a new {@link UniqueIdentifierBenchmarkSubject}.
   */
  public UniqueIdentifierBenchmarkSubject() throws Exception {
    UniqueIdentifier subject =
        UniqueIdentifier.Standard.values()[0].inst();  // TODO: Create a default instance
    initialize(subject, UniqueIdentifier.class);
  }

  @Override
  public String name() {
    return "UniqueIdentifier";
  }
}