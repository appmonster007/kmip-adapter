package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMajor;

/**
 * Benchmark subject for {@link ProfileVersionMajor}.
 */
public class ProfileVersionMajorBenchmarkSubject extends KmipBenchmarkSubject<ProfileVersionMajor> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ProfileVersionMajorBenchmarkSubject}.
   */
  public ProfileVersionMajorBenchmarkSubject() throws Exception {
    ProfileVersionMajor subject = ProfileVersionMajor.of(123);
    initialize(subject, ProfileVersionMajor.class);
  }

  @Override
  public String name() {
    return "ProfileVersionMajor";
  }
}