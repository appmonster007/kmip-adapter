package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Username;

public class UsernameBenchmarkSubject extends KmipBenchmarkSubject<Username> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public UsernameBenchmarkSubject() throws Exception {
        Username username = Username.builder().value("test-user").build();
        initialize(username, Username.class);
    }

    @Override
    public String name() {
        return "Username";
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