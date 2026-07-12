package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetEndpointRoleOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;

@DisplayName("SetEndpointRoleOpResponsePayload Ttlv Serialization Tests")
class SetEndpointRoleOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<SetEndpointRoleOpResponsePayload> {

    @Override
    public Class<SetEndpointRoleOpResponsePayload> type() {
        return SetEndpointRoleOpResponsePayload.class;
    }

    @Override
    public SetEndpointRoleOpResponsePayload createDefault() {
        return SetEndpointRoleOpResponsePayload.builder()
                .endpointRole(EndpointRole.Standard.SERVER.inst())
                .build();
    }

    @Override
    public SetEndpointRoleOpResponsePayload createVariant() {
        return SetEndpointRoleOpResponsePayload.builder()
                .endpointRole(EndpointRole.Standard.CLIENT.inst())
                .build();
    }
}