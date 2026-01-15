package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ApplicationSpecificInformation;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

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