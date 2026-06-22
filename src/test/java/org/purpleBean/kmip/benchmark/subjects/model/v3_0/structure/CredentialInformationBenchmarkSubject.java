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
import org.purpleBean.kmip.model.v3_0.structure.CredentialInformation;

public class CredentialInformationBenchmarkSubject extends KmipBenchmarkSubject<CredentialInformation> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public CredentialInformationBenchmarkSubject() throws Exception {
        CredentialInformation subject = CredentialInformation.builder()
                .credentialType(CredentialType.of(CredentialType.Standard.USERNAME_AND_PASSWORD))
                .build();
        initialize(subject, CredentialInformation.class);
    }

    @Override
    public String name() {
        return "CredentialInformation";
    }
}