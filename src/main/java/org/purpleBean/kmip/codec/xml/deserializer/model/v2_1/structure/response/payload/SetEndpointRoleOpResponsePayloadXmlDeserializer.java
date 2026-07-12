package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetEndpointRoleOpResponsePayload;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;

public class SetEndpointRoleOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SetEndpointRoleOpResponsePayload, SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder> {

    public SetEndpointRoleOpResponsePayloadXmlDeserializer() {
        super(SetEndpointRoleOpResponsePayload.kmipTag, SetEndpointRoleOpResponsePayload.encodingType);
    }

    @Override
    protected SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder createBuilder() {
        return SetEndpointRoleOpResponsePayload.builder();
    }

    @Override
    protected void setValue(SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ENDPOINT_ROLE -> builder.endpointRole(ctxt.readValue(p, EndpointRole.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SetEndpointRoleOpResponsePayload build(SetEndpointRoleOpResponsePayload.SetEndpointRoleOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}