package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;

import java.io.IOException;

public class EndpointRoleJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<EndpointRole, EndpointRole.EndpointRoleBuilder> {

    public EndpointRoleJsonDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType);
    }

    @Override
    protected EndpointRole.EndpointRoleBuilder createBuilder() {
        return EndpointRole.builder();
    }

    @Override
    protected void setValue(EndpointRole.EndpointRoleBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(EndpointRole.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected EndpointRole build(EndpointRole.EndpointRoleBuilder builder) {
        return builder.build();
    }
}
