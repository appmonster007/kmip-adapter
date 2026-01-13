package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ApplicationData;

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

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}