package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMinor;

public class ProfileVersionMinorBenchmarkSubject extends KmipBenchmarkSubject<ProfileVersionMinor> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ProfileVersionMinorBenchmarkSubject() throws Exception {
    ProfileVersionMinor subject = ProfileVersionMinor.of(123);
    initialize(subject, ProfileVersionMinor.class);
  }

  @Override
  public String name() {
    return "ProfileVersionMinor";
  }
}