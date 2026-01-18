package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.ApplicationData;

public class ApplicationDataBenchmarkSubject extends KmipBenchmarkSubject<ApplicationData> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ApplicationDataBenchmarkSubject() throws Exception {
        ApplicationData applicationData = ApplicationData.builder().value("test-data").build();
        initialize(applicationData, ApplicationData.class);
    }

    @Override
    public String name() {
        return "ApplicationData";
    }

}