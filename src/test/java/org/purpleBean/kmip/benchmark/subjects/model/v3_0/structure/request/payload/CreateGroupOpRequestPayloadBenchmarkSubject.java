package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateGroupOpRequestPayload;

public class CreateGroupOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<CreateGroupOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V3_0;

    public CreateGroupOpRequestPayloadBenchmarkSubject() throws Exception {
        CreateGroupOpRequestPayload subject = CreateGroupOpRequestPayload.builder().attributes(Attributes.of(java.util.List.of())).build();
        initialize(subject, CreateGroupOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "CreateGroupOpRequestPayload";
    }
}