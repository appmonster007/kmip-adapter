package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.ProfileVersion;

/**
 * Benchmark subject for {@link ProfileVersion}.
 */
public class ProfileVersionBenchmarkSubject extends KmipBenchmarkSubject<ProfileVersion> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ProfileVersionBenchmarkSubject}.
   */
  public ProfileVersionBenchmarkSubject() throws Exception {
    ProfileVersion subject = ProfileVersion.of(2, 1);
    initialize(subject, ProfileVersion.class);
  }

  @Override
  public String name() {
    return "ProfileVersion";
  }
}