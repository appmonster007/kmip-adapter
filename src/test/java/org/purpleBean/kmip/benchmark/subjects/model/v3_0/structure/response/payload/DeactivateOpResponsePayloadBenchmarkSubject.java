package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.DeactivateOpResponsePayload;

public class DeactivateOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<DeactivateOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V3_0;

    public DeactivateOpResponsePayloadBenchmarkSubject() throws Exception {
        DeactivateOpResponsePayload subject = DeactivateOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build())
                .build();
        initialize(subject, DeactivateOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "DeactivateOpResponsePayload";
    }
}