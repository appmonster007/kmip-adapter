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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetAttributeOpResponsePayload;

public class SetAttributeOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<SetAttributeOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public SetAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
        SetAttributeOpResponsePayload subject = SetAttributeOpResponsePayload.builder().build();  // TODO: Create a default instance
        initialize(subject, SetAttributeOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "SetAttributeOpResponsePayload";
    }
}