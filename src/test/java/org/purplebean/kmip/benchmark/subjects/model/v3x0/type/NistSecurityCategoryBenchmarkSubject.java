package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.NistSecurityCategory;

/**
 * Benchmark subject for {@link NistSecurityCategory}.
 */
public class NistSecurityCategoryBenchmarkSubject
    extends KmipBenchmarkSubject<NistSecurityCategory> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link NistSecurityCategoryBenchmarkSubject}.
   */
  public NistSecurityCategoryBenchmarkSubject() throws Exception {
    NistSecurityCategory subject = NistSecurityCategory.of(123);
    initialize(subject, NistSecurityCategory.class);
  }

  @Override
  public String name() {
    return "NistSecurityCategory";
  }
}