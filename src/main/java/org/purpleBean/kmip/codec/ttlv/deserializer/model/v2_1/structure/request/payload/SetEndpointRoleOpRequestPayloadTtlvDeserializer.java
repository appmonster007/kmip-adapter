package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetEndpointRoleOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;

public class SetEndpointRoleOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SetEndpointRoleOpRequestPayload, SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder> {

    public SetEndpointRoleOpRequestPayloadTtlvDeserializer() {
        super(SetEndpointRoleOpRequestPayload.kmipTag, SetEndpointRoleOpRequestPayload.encodingType);
    }

    @Override
    protected SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder createBuilder() {
        return SetEndpointRoleOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ENDPOINT_ROLE -> builder.endpointRole(mapper.readValue(p, EndpointRole.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SetEndpointRoleOpRequestPayload build(SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}