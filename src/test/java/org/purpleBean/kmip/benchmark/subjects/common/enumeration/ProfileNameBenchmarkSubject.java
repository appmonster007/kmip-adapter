package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ProfileName;

public class ProfileNameBenchmarkSubject extends KmipBenchmarkSubject<ProfileName> {

    public ProfileNameBenchmarkSubject() throws Exception {
        ProfileName profileName = new ProfileName(ProfileName.Standard.COMPLETE_SERVER_BASIC);
        initialize(profileName, ProfileName.class);
    }

    @Override
    public String name() {
        return "ProfileName";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
