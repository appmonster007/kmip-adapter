package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.AdjustAttributeOpRequestPayload;

public class AdjustAttributeOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<AdjustAttributeOpRequestPayload> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public AdjustAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
        AdjustAttributeOpRequestPayload subject = AdjustAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("adj-attr-uid-1").build())
                .currentAttribute(CurrentAttribute.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
                .build();
        initialize(subject, AdjustAttributeOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "AdjustAttributeOpRequestPayload";
    }
}
