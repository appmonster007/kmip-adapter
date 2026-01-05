package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.common.structure.ApplicationSpecificInformation;

public class ApplicationSpecificInformationBenchmarkSubject extends KmipBenchmarkSubject<ApplicationSpecificInformation> {

    public ApplicationSpecificInformationBenchmarkSubject() throws Exception {
        ApplicationSpecificInformation applicationspecificinformation = ApplicationSpecificInformation.builder()
                .applicationNamespace(ApplicationNamespace.of("namespace"))
                .applicationData(ApplicationData.of("data"))
                .build();
        initialize(applicationspecificinformation, ApplicationSpecificInformation.class);
    }

    @Override
    public String name() {
        return "ApplicationSpecificInformation";
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