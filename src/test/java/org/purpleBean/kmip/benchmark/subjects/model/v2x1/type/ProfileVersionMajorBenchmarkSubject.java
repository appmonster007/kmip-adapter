package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.ProfileVersionMajor;

public class ProfileVersionMajorBenchmarkSubject extends KmipBenchmarkSubject<ProfileVersionMajor> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ProfileVersionMajorBenchmarkSubject() throws Exception {
    ProfileVersionMajor subject = ProfileVersionMajor.of(123);
    initialize(subject, ProfileVersionMajor.class);
  }

  @Override
  public String name() {
    return "ProfileVersionMajor";
  }
}