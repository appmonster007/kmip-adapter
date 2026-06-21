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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetEndpointRoleOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetEndpointRoleOpRequestPayload Json Serialization Tests")
class SetEndpointRoleOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<SetEndpointRoleOpRequestPayload> {

    @Override
    public Class<SetEndpointRoleOpRequestPayload> type() {
        return SetEndpointRoleOpRequestPayload.class;
    }

    @Override
    public SetEndpointRoleOpRequestPayload createDefault() {
        return SetEndpointRoleOpRequestPayload.builder().build();
    }

    @Override
    public SetEndpointRoleOpRequestPayload createVariant() {
        return SetEndpointRoleOpRequestPayload.builder().build();
    }
}