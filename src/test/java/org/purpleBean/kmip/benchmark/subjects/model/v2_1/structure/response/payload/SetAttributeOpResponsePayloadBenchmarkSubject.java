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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetAttributeOpResponsePayload;

public class SetAttributeOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<SetAttributeOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public SetAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
        SetAttributeOpResponsePayload subject = SetAttributeOpResponsePayload.of(UniqueIdentifier.builder().value("test-uid-1").build());
        initialize(subject, SetAttributeOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "SetAttributeOpResponsePayload";
    }
}