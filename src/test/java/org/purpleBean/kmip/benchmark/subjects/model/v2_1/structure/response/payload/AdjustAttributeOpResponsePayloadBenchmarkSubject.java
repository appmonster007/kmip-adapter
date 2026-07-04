package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.AdjustAttributeOpResponsePayload;

public class AdjustAttributeOpResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<AdjustAttributeOpResponsePayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public AdjustAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
        AdjustAttributeOpResponsePayload subject = AdjustAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("adj-attr-resp-uid-1").build())
                .newAttribute(NewAttribute.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .build();
        initialize(subject, AdjustAttributeOpResponsePayload.class);
    }

    @Override
    public String name() {
        return "AdjustAttributeOpResponsePayload";
    }
}
