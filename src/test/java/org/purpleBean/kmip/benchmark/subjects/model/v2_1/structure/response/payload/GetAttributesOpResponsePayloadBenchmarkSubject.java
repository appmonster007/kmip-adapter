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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetAttributesOpResponsePayload;

public class GetAttributesOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<GetAttributesOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public GetAttributesOpResponsePayloadBenchmarkSubject() throws Exception {
        GetAttributesOpResponsePayload subject = GetAttributesOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
                .build();
        initialize(subject, GetAttributesOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "GetAttributesOpResponsePayload";
    }
}