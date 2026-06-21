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
import org.purpleBean.kmip.model.v3_0.structure.OtpCredential;

public class OtpCredentialBenchmarkSubject extends KmipBenchmarkSubject<OtpCredential> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public OtpCredentialBenchmarkSubject() throws Exception {
        OtpCredential subject = OtpCredential.builder().build();  // TODO: Create a default instance
        initialize(subject, OtpCredential.class);
    }

    @Override
    public String name() {
        return "OtpCredential";
    }
}