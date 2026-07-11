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
import org.purpleBean.kmip.model.v3_0.structure.PasswordCredential;

public class PasswordCredentialBenchmarkSubject extends KmipBenchmarkSubject<PasswordCredential> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion;

    public PasswordCredentialBenchmarkSubject() throws Exception {
        PasswordCredential subject = PasswordCredential.builder()
                .password(Password.of("s3cr3t"))
                .build();
        initialize(subject, PasswordCredential.class);
    }

    @Override
    public String name() {
        return "PasswordCredential";
    }
}