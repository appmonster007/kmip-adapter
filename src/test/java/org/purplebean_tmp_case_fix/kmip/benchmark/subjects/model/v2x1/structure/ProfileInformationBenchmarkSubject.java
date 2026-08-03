package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ProfileName;
import org.purplebean.kmip.model.v2x1.structure.ProfileInformation;

public class ProfileInformationBenchmarkSubject extends KmipBenchmarkSubject<ProfileInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ProfileInformationBenchmarkSubject() throws Exception {
    ProfileInformation subject =
        ProfileInformation.of(ProfileName.Standard.COMPLETE_SERVER_BASIC.inst());
    initialize(subject, ProfileInformation.class);
  }

  @Override
  public String name() {
    return "ProfileInformation";
  }
}