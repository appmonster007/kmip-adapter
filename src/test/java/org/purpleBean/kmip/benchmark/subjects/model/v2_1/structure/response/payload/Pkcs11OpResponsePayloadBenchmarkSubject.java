package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.Pkcs11OpResponsePayload;

public class Pkcs11OpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11OpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public Pkcs11OpResponsePayloadBenchmarkSubject() throws Exception {
        Pkcs11OpResponsePayload subject = Pkcs11OpResponsePayload.builder().build();  // TODO: Create a default instance
        initialize(subject, Pkcs11OpResponsePayload.class);
    }

    @Override
    public String name() {
        return "Pkcs11OpResponsePayload";
    }
}