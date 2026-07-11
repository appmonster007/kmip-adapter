package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.structure.HashedPasswordCredential;

public class HashedPasswordCredentialBenchmarkSubject extends KmipBenchmarkSubject<HashedPasswordCredential> {

    @Getter
    private KmipSpec spec = KmipSpec.V3_0;

    public HashedPasswordCredentialBenchmarkSubject() throws Exception {
        HashedPasswordCredential subject = HashedPasswordCredential.builder()
                .hashedUsernamePassword(HashedUsernamePassword.of(new byte[]{0x01, 0x02, 0x03}))
                .hashedPasswordUsername(HashedPasswordUsername.of(new byte[]{0x04, 0x05, 0x06}))
                .build();
        initialize(subject, HashedPasswordCredential.class);
    }

    @Override
    public String name() {
        return "HashedPasswordCredential";
    }
}