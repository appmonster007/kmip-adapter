package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;

public class UsernameAndPasswordBenchmarkSubject extends KmipBenchmarkSubject<UsernameAndPassword> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public UsernameAndPasswordBenchmarkSubject() throws Exception {
        UsernameAndPassword subject = UsernameAndPassword.builder()
                .username(Username.of("test-user"))
                .password(Password.of("test-password"))
                .build();
        initialize(subject, UsernameAndPassword.class);
    }

    @Override
    public String name() {
        return "UsernameAndPassword";
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