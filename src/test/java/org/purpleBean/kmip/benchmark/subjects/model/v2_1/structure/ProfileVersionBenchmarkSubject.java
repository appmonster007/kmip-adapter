package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.ProfileVersion;

public class ProfileVersionBenchmarkSubject extends KmipBenchmarkSubject<ProfileVersion> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ProfileVersionBenchmarkSubject() throws Exception {
    ProfileVersion subject = ProfileVersion.of(2, 1);
    initialize(subject, ProfileVersion.class);
  }

  @Override
  public String name() {
    return "ProfileVersion";
  }
}