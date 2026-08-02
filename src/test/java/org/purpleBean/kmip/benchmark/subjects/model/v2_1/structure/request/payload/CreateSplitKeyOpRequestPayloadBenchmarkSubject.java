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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateSplitKeyOpRequestPayload;

public class CreateSplitKeyOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<CreateSplitKeyOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public CreateSplitKeyOpRequestPayloadBenchmarkSubject() throws Exception {
        CreateSplitKeyOpRequestPayload subject = CreateSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("source-key-id").build())
                .splitKeyParts(SplitKeyParts.of(3))
                .splitKeyThreshold(SplitKeyThreshold.of(2))
                .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
                .build();
        initialize(subject, CreateSplitKeyOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "CreateSplitKeyOpRequestPayload";
    }
}