package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;

@DisplayName("SetEndpointRoleOpRequestPayload Xml Serialization Tests")
class SetEndpointRoleOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<SetEndpointRoleOpRequestPayload> {

    @Override
    public Class<SetEndpointRoleOpRequestPayload> type() {
        return SetEndpointRoleOpRequestPayload.class;
    }

    @Override
    public SetEndpointRoleOpRequestPayload createDefault() {
        return SetEndpointRoleOpRequestPayload.builder()
                .endpointRole(EndpointRole.Standard.CLIENT.inst())
                .build();
    }

    @Override
    public SetEndpointRoleOpRequestPayload createVariant() {
        return SetEndpointRoleOpRequestPayload.builder()
                .endpointRole(EndpointRole.Standard.SERVER.inst())
                .build();
    }
}