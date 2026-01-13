package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Password;

public class PasswordBenchmarkSubject extends KmipBenchmarkSubject<Password> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public PasswordBenchmarkSubject() throws Exception {
        Password password = Password.builder().value("test-password").build();
        initialize(password, Password.class);
    }

    @Override
    public String name() {
        return "Password";
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