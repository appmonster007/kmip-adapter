package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;

public class ProfileNameBenchmarkSubject extends KmipBenchmarkSubject<ProfileName> {

  public ProfileNameBenchmarkSubject() throws Exception {
    ProfileName profileName = ProfileName.Standard.COMPLETE_SERVER_BASIC.inst();
    initialize(profileName, ProfileName.class);
  }

  @Override
  public String name() {
    return "ProfileName";
  }

}
