package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetEndpointRoleOpRequestPayload;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;

public class SetEndpointRoleOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SetEndpointRoleOpRequestPayload, SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder> {

    public SetEndpointRoleOpRequestPayloadXmlDeserializer() {
        super(SetEndpointRoleOpRequestPayload.kmipTag, SetEndpointRoleOpRequestPayload.encodingType);
    }

    @Override
    protected SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder createBuilder() {
        return SetEndpointRoleOpRequestPayload.builder();
    }

    @Override
    protected void setValue(SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ENDPOINT_ROLE -> builder.endpointRole(ctxt.readValue(p, EndpointRole.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SetEndpointRoleOpRequestPayload build(SetEndpointRoleOpRequestPayload.SetEndpointRoleOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}