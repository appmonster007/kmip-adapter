package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DelegatedLoginOpRequestPayload;

public class DelegatedLoginOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<DelegatedLoginOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public DelegatedLoginOpRequestPayloadBenchmarkSubject() throws Exception {
        DelegatedLoginOpRequestPayload subject = DelegatedLoginOpRequestPayload.builder().credential(Credential.of(CredentialType.Standard.USERNAME_AND_PASSWORD.inst(), org.purpleBean.kmip.model.core.structure.UsernameAndPassword.of(Username.of("test"), Password.of("pass")))).build();
        initialize(subject, DelegatedLoginOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "DelegatedLoginOpRequestPayload";
    }
}