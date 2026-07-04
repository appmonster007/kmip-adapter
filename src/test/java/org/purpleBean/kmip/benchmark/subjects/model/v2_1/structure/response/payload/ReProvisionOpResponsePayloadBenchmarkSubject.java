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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ReProvisionOpResponsePayload;

public class ReProvisionOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<ReProvisionOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public ReProvisionOpResponsePayloadBenchmarkSubject() throws Exception {
        ReProvisionOpResponsePayload subject = ReProvisionOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("reprovision-uid-1").build())
                .build();
        initialize(subject, ReProvisionOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "ReProvisionOpResponsePayload";
    }
}