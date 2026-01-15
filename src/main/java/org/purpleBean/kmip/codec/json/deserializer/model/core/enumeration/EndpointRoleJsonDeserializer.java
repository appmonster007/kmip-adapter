package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

public class EndpointRoleJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<EndpointRole, String> {

    public EndpointRoleJsonDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, String.class, value -> new EndpointRole(EndpointRole.fromName(value)));
    }
}