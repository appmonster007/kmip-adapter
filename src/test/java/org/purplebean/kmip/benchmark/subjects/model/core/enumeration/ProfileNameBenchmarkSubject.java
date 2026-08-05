package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ProfileName;

/**
 * Benchmark subject for {@link ProfileName}.
 */
public class ProfileNameBenchmarkSubject extends KmipBenchmarkSubject<ProfileName> {

  /**
   * Constructs a new {@link ProfileNameBenchmarkSubject}.
   */
  public ProfileNameBenchmarkSubject() throws Exception {
    ProfileName profileName = ProfileName.Standard.COMPLETE_SERVER_BASIC.inst();
    initialize(profileName, ProfileName.class);
  }

  @Override
  public String name() {
    return "ProfileName";
  }

}
