package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.RegisterOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RegisterOpRequestPayload Json Serialization Tests")
class RegisterOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<RegisterOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
    }

    @Override
    public Class<RegisterOpRequestPayload> type() {
        return RegisterOpRequestPayload.class;
    }

    @Override
    public RegisterOpRequestPayload createDefault() {
        return RegisterOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .attributes(Attributes.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build())
                .object(SymmetricKey.builder()
                        .keyBlock(KeyBlock.builder()
                                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                                .build())
                        .build())
                .build();
    }

    @Override
    public RegisterOpRequestPayload createVariant() {
        return RegisterOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .attributes(Attributes.builder().attribute(CryptographicAlgorithm.Standard.TRIPLE_DES.inst()).build())
                .object(SymmetricKey.builder()
                        .keyBlock(KeyBlock.builder()
                                .keyFormatType(KeyFormatType.Standard.RAW.inst())
                                .build())
                        .build())
                .build();
    }
}