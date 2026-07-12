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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetDefaultsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetDefaultsOpRequestPayload Json Serialization Tests")
class SetDefaultsOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<SetDefaultsOpRequestPayload> {

    @Override
    public Class<SetDefaultsOpRequestPayload> type() {
        return SetDefaultsOpRequestPayload.class;
    }

    @Override
    public SetDefaultsOpRequestPayload createDefault() {
        return SetDefaultsOpRequestPayload.builder().defaultsInformation(DefaultsInformation.of(java.util.List.of(ObjectDefaults.of(ObjectType.Standard.CERTIFICATE.inst(), Attributes.of(java.util.List.of()))))).build();
    }

    @Override
    public SetDefaultsOpRequestPayload createVariant() {
        return SetDefaultsOpRequestPayload.builder().defaultsInformation(DefaultsInformation.of(java.util.List.of(ObjectDefaults.of(ObjectType.Standard.SYMMETRIC_KEY.inst(), Attributes.of(java.util.List.of()))))).build();
    }
}