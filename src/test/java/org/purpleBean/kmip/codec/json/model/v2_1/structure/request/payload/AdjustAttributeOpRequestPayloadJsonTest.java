package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
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
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.AdjustAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AdjustAttributeOpRequestPayload Json Serialization Tests")
class AdjustAttributeOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<AdjustAttributeOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<AdjustAttributeOpRequestPayload> type() {
        return AdjustAttributeOpRequestPayload.class;
    }

    @Override
    public AdjustAttributeOpRequestPayload createDefault() {
        return AdjustAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("adj-attr-uid-1").build())
                .currentAttribute(CurrentAttribute.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
                .build();
    }

    @Override
    public AdjustAttributeOpRequestPayload createVariant() {
        return AdjustAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("adj-attr-uid-2").build())
                .currentAttribute(CurrentAttribute.builder().attribute(CryptographicAlgorithm.Standard.RSA.inst()).build())
                .adjustmentType(AdjustmentType.Standard.DECREMENT.inst())
                .build();
    }
}
