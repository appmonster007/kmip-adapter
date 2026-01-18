package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.Credential;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;

public class AuthenticationBenchmarkSubject extends KmipBenchmarkSubject<Authentication> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public AuthenticationBenchmarkSubject() throws Exception {
        Credential credential = Credential.builder()
                .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
                .credentialValue(UsernameAndPassword.builder()
                        .username(Username.of("test-user"))
                        .password(Password.of("test-password"))
                        .build())
                .build();
        Authentication subject = Authentication.builder()
                .credential(credential)
                .credential(credential)
                .build();
        initialize(subject, Authentication.class);
    }

    @Override
    public String name() {
        return "Authentication";
    }

}